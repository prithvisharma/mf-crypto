package com.mf.crypto.dto;

public class ServiceResponseDataDto extends ServiceResponseDto {
    private Object response;

    public ServiceResponseDataDto() {
    }

    public ServiceResponseDataDto(Object response) {
        setResponse(response);
    }

    public Object getResponse() {
        return this.response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

}
