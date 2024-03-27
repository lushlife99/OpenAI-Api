package com.example.whisperapiprac.controller;

import com.example.whisperapiprac.dto.ChatGPTResponse;
import com.example.whisperapiprac.dto.ChatRequest;
import com.example.whisperapiprac.dto.TranscriptionRequest;
import com.example.whisperapiprac.dto.WhisperTranscriptionResponse;
import com.example.whisperapiprac.service.OpenAIClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api")
public class WhisperController {

    private final OpenAIClientService openAIClientService;

    @PostMapping(value = "/chat", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ChatGPTResponse chat(@RequestBody ChatRequest chatRequest){
        return openAIClientService.chat(chatRequest);
    }

    @PostMapping(value = "/transcription", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public WhisperTranscriptionResponse createTranscription(@ModelAttribute TranscriptionRequest transcriptionRequest){
        return openAIClientService.createTranscription(transcriptionRequest);
    }

}