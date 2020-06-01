package com.avilapps.pdf_services.services;

import com.avilapps.pdf_services.dto.FoliationSettings;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;

@SpringBootTest
public class PDFBoxFoliatorUnitTest {
    private static Logger LOG = LoggerFactory.getLogger(PDFBoxFoliatorUnitTest.class);

    @Autowired
    private Foliator foliator;

    @Test
    public void outputFolderIsCreated() {
        File outputDirectory = new File(System.getProperty("java.io.tmpdir"), "output");
        assertThat(outputDirectory.exists()).isTrue();
    }

    @Test
    public void loadRemoteFile() throws IOException {
        String fileUrl = "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf";
        FoliationSettings foliationSettings = new FoliationSettings(fileUrl, 1);
        File result = foliator.foliate(foliationSettings);
        LOG.info("File saved at " + result.getAbsolutePath());

        assertThat(result.exists()).isTrue();
        assertThat(result.getName()).isEqualTo("dummy.pdf");

        result.deleteOnExit();
    }
}
