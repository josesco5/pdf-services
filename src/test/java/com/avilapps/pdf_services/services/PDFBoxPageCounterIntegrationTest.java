package com.avilapps.pdf_services.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class PDFBoxPageCounterIntegrationTest {

    @TestConfiguration
    static class PDFBoxPageCounterTestContextConfiguration {

        @Bean
        public PageCounter pageCounter() {
            return new PDFBoxPageCounter();
        }
    }

    @Autowired
    private PageCounter pageCounter;

    @Test
    public void countPDFWithOnePage() throws IOException {
        String fileUrl = "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf";
        assertThat(pageCounter.count(fileUrl)).isEqualTo(1);
    }

    @Test
    public void countPagesForPDFWithTwoPages() throws IOException {
        String fileUrl = "http://www.africau.edu/images/default/sample.pdf";
        assertThat(pageCounter.count(fileUrl)).isEqualTo(2);
    }
}
