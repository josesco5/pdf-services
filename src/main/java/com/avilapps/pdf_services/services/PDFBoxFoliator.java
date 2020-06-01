package com.avilapps.pdf_services.services;

import com.avilapps.pdf_services.dto.FoliationSettings;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

@Service
public class PDFBoxFoliator implements Foliator {
    private static final File TEMP_DIRECTORY = new File(System.getProperty("java.io.tmpdir"));
    private static final File OUTPUT_DIRECTORY = new File(TEMP_DIRECTORY, "output");
    private static Logger LOG = LoggerFactory.getLogger(PDFBoxFoliator.class);


    public PDFBoxFoliator() {
        if (!OUTPUT_DIRECTORY.exists()) {
            OUTPUT_DIRECTORY.mkdir();
        }
    }

    public File foliate(FoliationSettings foliationSettings) throws IOException {
        PDDocument document = loadDocument(foliationSettings.getFileUrl());
        String filename = Paths.get(foliationSettings.getFileUrl()).getFileName().toString();
        File outputFile = new File(OUTPUT_DIRECTORY, filename);
        document.save(outputFile);

        return outputFile;
    }

    private PDDocument loadDocument(String fileUrl) throws IOException {
        BufferedInputStream inputStream = new BufferedInputStream(new URL(fileUrl).openStream());
        return PDDocument.load(inputStream, MemoryUsageSetting.setupTempFileOnly());
    }
}
