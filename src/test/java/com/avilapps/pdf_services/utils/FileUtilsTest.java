package com.avilapps.pdf_services.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class FileUtilsTest {

    @InjectMocks
    private FileUtils fileUtils;

    @Test
    public void outputFolderIsCreated() {
        File outputDirectory = fileUtils.getOutputDirectory();
        assertThat(outputDirectory.exists()).isTrue();
    }

    @Test
    public void remoteFileIsReceived() {
        MockMultipartFile remoteFile = new MockMultipartFile(
                "file", "test.pdf", MediaType.APPLICATION_PDF_VALUE,
                "Test content".getBytes()
        );
        File receivedFile = fileUtils.receiveRemotePDF(remoteFile);
        assertThat(receivedFile).isNotNull();
    }

    @Test
    public void whenReceivingNullRemoteFileThenThrowException() {
        assertThrows(RuntimeException.class, () -> fileUtils.receiveRemotePDF(null));
    }
}