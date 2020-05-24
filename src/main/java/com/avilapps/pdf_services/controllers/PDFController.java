package com.avilapps.pdf_services.controllers;

import com.avilapps.pdf_services.dto.PageCountResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pdf")
public class PDFController {

    @GetMapping("/pages-count")
    public PageCountResponse countPDFPages(@RequestParam(value="url") String fileUrl) {
        return new PageCountResponse(fileUrl, 1);
    }
}
