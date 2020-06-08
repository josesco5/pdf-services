package com.avilapps.pdf_services.services;

import com.avilapps.pdf_services.dto.FoliationSettings;
import com.avilapps.pdf_services.utils.NumberToWords;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.util.Matrix;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Paths;

@Service
public class PDFBoxFoliator implements Foliator {
    private static final int FONT_SIZE = 10;
    private static final File TEMP_DIRECTORY = new File(System.getProperty("java.io.tmpdir"));
    private static final File OUTPUT_DIRECTORY = new File(TEMP_DIRECTORY, "output");
    private static final Logger LOG = LoggerFactory.getLogger(PDFBoxFoliator.class);


    public PDFBoxFoliator() {
        if (!OUTPUT_DIRECTORY.exists()) {
            OUTPUT_DIRECTORY.mkdir();
        }
    }

    public File foliate(FoliationSettings foliationSettings) throws IOException {
        PDDocument document = loadDocument(foliationSettings.getFileUrl());
        String filename = Paths.get(foliationSettings.getFileUrl()).getFileName().toString();
        int initialFolio = foliationSettings.getStartFolio();

        Resource resource = new ClassPathResource("static/fonts/Roboto-Regular.ttf");
        InputStream fontStream = resource.getInputStream();
        PDFont font = PDType0Font.load(document, fontStream);
        int totalPages = document.getNumberOfPages();

        for (int i = 0; i < totalPages; i++) {
            PDPage page = document.getPage(i);
            addFolioToPage(document, page, initialFolio + i, font);
            LOG.info("Foliated page " + i);
        }

        File outputFile = new File(OUTPUT_DIRECTORY, filename);
        document.save(outputFile);

        return outputFile;
    }

    private PDDocument loadDocument(String fileUrl) throws IOException {
        BufferedInputStream inputStream = new BufferedInputStream(new URL(fileUrl).openStream());
        return PDDocument.load(inputStream, MemoryUsageSetting.setupTempFileOnly());
    }

    private void addFolioToPage(PDDocument document, PDPage page, int folio, PDFont font) throws IOException {
        PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true, true);

        float firstLinePositionX, firstLinePositionY;
        float writtenFolioPositionX, writtenFolioPositionY;

        float pageWidth = page.getMediaBox().getWidth();
        float pageHeight = page.getMediaBox().getHeight();
        int rotation = page.getRotation();

        String firstLineText = "Fojas " + folio;
        String writtenFolio = NumberToWords.convert(folio);

        float firstLineTextWidth = getTextWidth(firstLineText, font, FONT_SIZE);
        float writtenFolioWidth = getTextWidth(writtenFolio, font, FONT_SIZE);

        LOG.info("Width: " + pageWidth);
        LOG.info("Height: " + pageHeight);
        LOG.info(firstLineText + ". Rotation: " + page.getRotation());

        switch(rotation) {
            case 90:
                firstLinePositionX = 40;
                firstLinePositionY = pageHeight - firstLineTextWidth - 40;

                writtenFolioPositionX = firstLinePositionX + 12.5f;
                writtenFolioPositionY = pageHeight - writtenFolioWidth - 40;
                break;
            case 180:
                firstLinePositionX = firstLineTextWidth + 40;
                firstLinePositionY = 40;

                writtenFolioPositionX = writtenFolioWidth + 40;
                writtenFolioPositionY = 40 + 12.5f;
                break;
            case 270:
                firstLinePositionX = pageWidth - 40;
                firstLinePositionY = firstLineTextWidth + 40;

                writtenFolioPositionX = firstLinePositionX - 12.5f;
                writtenFolioPositionY = writtenFolioWidth + 40;
                break;
            default:
                firstLinePositionX = pageWidth - firstLineTextWidth - 40;
                firstLinePositionY = pageHeight - 40;

                writtenFolioPositionX = pageWidth - writtenFolioWidth - 40;
                writtenFolioPositionY = pageHeight - 40 - 12.5f;
        }

        writeLine(contentStream, firstLineText, font, FONT_SIZE, firstLinePositionX, firstLinePositionY, rotation);
        writeLine(contentStream, writtenFolio, font, FONT_SIZE, writtenFolioPositionX, writtenFolioPositionY, rotation);

        contentStream.close();
    }

    private void writeLine(PDPageContentStream contentStream, String text, PDFont font, int fontSize, float positionX, float positionY, int rotation) throws IOException {
        contentStream.beginText();
        Matrix matrix = Matrix.getRotateInstance(Math.toRadians(rotation), positionX, positionY);
        contentStream.setTextMatrix(matrix);
        contentStream.setFont(font, fontSize);
        contentStream.showText(text);
        contentStream.endText();
    }

    private float getTextWidth(String text, PDFont font, int fontSize) throws IOException {
        return (font.getStringWidth(text) / 1000.0f) * fontSize;
    }
}
