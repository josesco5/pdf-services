package com.avilapps.pdf_services.presentation.model

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
class DocumentFoliateRequest (
    val originPath: String,
    val initialFolio: Int,
    val destinationPath: String
)
