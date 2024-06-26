package com.example.whisperapiprac.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class WhisperTranscriptionResponse implements Serializable {
    private String text;
    private Object[] segments;
}