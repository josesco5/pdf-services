package com.avilapps.pdf_services.services;

import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;

@Service
public class PDFBoxPageCounter implements PageCounter {
    @Override
    public int count(String fileUrl) throws IOException {
        BufferedInputStream inputStream = new BufferedInputStream(new URL(fileUrl).openStream());
        PDDocument document = PDDocument.load(inputStream, MemoryUsageSetting.setupTempFileOnly());
        int result = document.getNumberOfPages();
        document.close();
        return result;
    }
}
