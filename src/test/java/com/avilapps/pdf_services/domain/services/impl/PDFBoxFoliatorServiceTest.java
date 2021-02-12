package com.avilapps.pdf_services.domain.services.impl;

import com.avilapps.pdf_services.common.exceptions.ServiceException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PDFBoxFoliatorServiceTest {
    private static Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @InjectMocks
    PDFBoxFoliatorService foliatorService;

    @Test
    public void outputFolderIsCreated() {
        File outputDirectory = new File(System.getProperty("java.io.tmpdir"), "output");
        assertThat(outputDirectory.exists()).isTrue();
    }

    @Test
    public void loadRemoteFile() {
        String fileUrl = "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf";
        File result = foliatorService.foliate(fileUrl, 1);
        LOG.info("File saved at " + result.getAbsolutePath());

        assertTrue(result.exists());
        assertEquals("dummy.pdf", result.getName());

        result.deleteOnExit();
    }

    @Test
    public void foliatePDFWithOnePage() throws IOException {
        String fileUrl = "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf";
        File result = foliatorService.foliate(fileUrl, 1);
        LOG.info("File saved at " + result.getAbsolutePath());

        PDDocument document = PDDocument.load(result);
        PDFTextStripper pdfStripper = new PDFTextStripper();
        String text = pdfStripper.getText(document);

        assertEquals(1, document.getNumberOfPages());
        assertTrue(text.contains("Fojas 1"));
        assertTrue(text.contains("uno"));

        document.close();
        result.deleteOnExit();
    }

    @Test
    public void foliatePDFWithTwoPages() throws IOException {
        String fileUrl = "http://www.africau.edu/images/default/sample.pdf";
        File result = foliatorService.foliate(fileUrl, 1);
        LOG.info("File saved at " + result.getAbsolutePath());

        PDDocument document = PDDocument.load(result);
        PDFTextStripper pdfStripper = new PDFTextStripper();
        String text = pdfStripper.getText(document);

        assertEquals(2, document.getNumberOfPages());
        assertTrue(text.contains("Fojas 1"));
        assertTrue(text.contains("uno"));
        assertTrue(text.contains("Fojas 2"));
        assertTrue(text.contains("dos"));

        document.close();
        result.deleteOnExit();
    }

    @Test
    public void cannotFoliateANonPdfFile() {
        String fileUrl = "http://www.sci.utah.edu/~macleod/docs/txt2html/sample.txt";
        assertThrows(ServiceException.class, () -> foliatorService.foliate(fileUrl, 1));
    }

    @Test
    public void cannotFoliateANonExistentFile() {
        String fileUrl = "http://www.sci.utah.edu/~macleod/docs/txt2html/non-existent.pdf";
        assertThrows(ServiceException.class, () -> foliatorService.foliate(fileUrl, 1));
    }
}
