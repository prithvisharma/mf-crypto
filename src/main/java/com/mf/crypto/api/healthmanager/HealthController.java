package com.mf.crypto.api.healthmanager;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mf.crypto.dto.HealthCheckDto;

import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
public class HealthController {

    @GetMapping("/health")
    @ResponseStatus(value = HttpStatus.OK)
    public HealthCheckDto health() {
        return new HealthCheckDto();
    }

}