package com.avilapps.pdf_services.presentation.controllers

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(controllers = [DocumentsController::class])
class DocumentsControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `should return OK response`() {
        mockMvc.perform(post("/upload")
            .contentType(MediaType.APPLICATION_JSON)
            .content(""))
            .andExpect(status().isOk)
    }
}