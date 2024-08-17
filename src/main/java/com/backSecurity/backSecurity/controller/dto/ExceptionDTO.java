package com.backSecurity.backSecurity.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ExceptionDTO {
    private int status;
    private String error;
    private String message;
    private String path;

}