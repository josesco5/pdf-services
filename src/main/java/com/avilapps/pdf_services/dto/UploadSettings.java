package com.avilapps.pdf_services.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UploadSettings {
    private final String key;
    private final String directFogUrl;
    private final String successActionRedirect;
    private final String awsAccessKeyId;
    private final String acl;
    private final String policy;
    private final String signature;


    public UploadSettings(String key, String directFogUrl, String successActionRedirect, String awsAccessKeyId, String acl, String policy, String signature) {
        this.key = key;
        this.directFogUrl = directFogUrl;
        this.successActionRedirect = successActionRedirect;
        this.awsAccessKeyId = awsAccessKeyId;
        this.acl = acl;
        this.policy = policy;
        this.signature = signature;
    }

    public String getKey() {
        return key;
    }

    public String getDirectFogUrl() {
        return directFogUrl;
    }

    public String getSuccessActionRedirect() {
        return successActionRedirect;
    }

    public String getAwsAccessKeyId() {
        return awsAccessKeyId;
    }

    public String getAcl() {
        return acl;
    }

    public String getPolicy() {
        return policy;
    }

    public String getSignature() {
        return signature;
    }
}
