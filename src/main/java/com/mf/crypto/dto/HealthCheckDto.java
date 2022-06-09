package com.mf.crypto.dto;

import com.mf.crypto.config.service.ServiceConfigEndpoint;

public class HealthCheckDto extends ServiceResponseDto {

    private String name;
    private String status;

    public HealthCheckDto() {
        this.name = ServiceConfigEndpoint.MF.getEndpoint().substring(1);
        this.status = "HEALTHY";
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }
}