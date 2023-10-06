package com.hackathon.Magic.dtos;

import lombok.Data;

import java.util.Map;
@Data
public class GenAPIResponse {
    String statusCode;
    String message;
    Map<String, Object> data;
}
