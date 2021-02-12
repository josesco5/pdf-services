package com.avilapps.pdf_services.api.controllers;

import com.avilapps.pdf_services.api.delegate.DocumentDelegate;
import com.avilapps.pdf_services.api.model.FoliateDocumentRequest;
import com.avilapps.pdf_services.api.model.FoliateDocumentResponse;
import com.avilapps.pdf_services.common.exceptions.DelegateException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DocumentController.class)
public class DocumentControllerIntegrationTest {

    @MockBean
    private DocumentDelegate documentFoliateDelegate;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnFoliateResponse() throws Exception {
        FoliateDocumentRequest request = new FoliateDocumentRequest();
        request.setInitialFolio("1");
        request.setOriginPath("");
        request.setDestinationPath("");

        when(documentFoliateDelegate.foliate(any(FoliateDocumentRequest.class)))
                .thenReturn(new FoliateDocumentResponse());

        mockMvc.perform(post("/documents/foliate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(request)))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldManageDelegateException() throws Exception {
        FoliateDocumentRequest request = new FoliateDocumentRequest();

        when(documentFoliateDelegate.foliate(any(FoliateDocumentRequest.class)))
                .thenThrow(DelegateException.class);

        mockMvc.perform(post("/documents/foliate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(request)))
                .andExpect(status().isInternalServerError());
    }

    private String asJsonString(Object object) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(object);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
}
