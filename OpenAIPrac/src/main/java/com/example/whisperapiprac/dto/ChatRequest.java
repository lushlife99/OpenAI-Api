package com.example.whisperapiprac.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ChatRequest implements Serializable {
    private String system;
    private String question;
}