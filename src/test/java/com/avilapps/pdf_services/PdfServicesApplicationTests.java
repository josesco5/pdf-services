package com.avilapps.pdf_services;

import com.avilapps.pdf_services.controllers.PDFController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PdfServicesApplicationTests {

    @Autowired
    private PDFController pdfController;

    @Test
    void contextLoads() {
        assertThat(pdfController).isNotNull();
    }

}
