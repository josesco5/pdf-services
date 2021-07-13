package com.avilapps.pdf_services.domain.repository.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.avilapps.pdf_services.common.exceptions.RepositoryException;
import com.avilapps.pdf_services.domain.model.ContentFile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.File;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AWSFileRepositoryTest {

    @InjectMocks
    private AWSFileRepository fileRepository;

    @Mock
    private AmazonS3 s3Client;

    private ContentFile contentFile;

    @BeforeEach
    void setUp() {
        contentFile = new ContentFile();
        contentFile.setUrl("/uploads/1/file");
        contentFile.setContent(new File("src/test"));

        ReflectionTestUtils.setField(fileRepository, "bucket", "test-bucket");
    }

    @Test
    public void shouldReturnUrlAfterUploadingFile() throws Exception {
        String url = "http://localhost/file";
        when(s3Client.putObject(anyString(), anyString(), any(File.class)))
                .thenReturn(new PutObjectResult());
        when(s3Client.generatePresignedUrl(any(GeneratePresignedUrlRequest.class)))
                .thenReturn(new URL(url));

        String resultUrl = fileRepository.upload(contentFile);

        assertEquals(url, resultUrl);
    }

    @Test
    public void whenUploadingFileThrowsException() {
        when(s3Client.putObject(anyString(), anyString(), any(File.class)))
                .thenThrow(NullPointerException.class);

        assertThrows(RepositoryException.class, () -> fileRepository.upload(contentFile));
    }
}
