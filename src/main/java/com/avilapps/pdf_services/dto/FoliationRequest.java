package com.avilapps.pdf_services.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class FoliationRequest {
  private final FoliationSettings foliationSettings;
  private final UploadSettings uploadSettings;

  @JsonCreator
  public FoliationRequest(@JsonProperty("file") FoliationSettings foliationSettings, @JsonProperty("s3") UploadSettings uploadSettings) {
    this.foliationSettings = foliationSettings;
    this.uploadSettings = uploadSettings;
  }

  public FoliationSettings getFoliationSettings() {
    return foliationSettings;
  }

  public UploadSettings getUploadSettings() {
    return uploadSettings;
  }
}