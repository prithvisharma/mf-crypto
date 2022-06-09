package com.mf.crypto.dto;

public class ServiceResponseDataDto extends ServiceResponseDto {
    private String response;

    public ServiceResponseDataDto() {
    }

    public ServiceResponseDataDto(String response) {
        this.response = response;
    }

    public String getResponse() {
        return this.response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

}
