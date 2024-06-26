package com.example.whisperapiprac.controller;

import com.example.whisperapiprac.dto.*;
import com.example.whisperapiprac.service.OpenAIClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api")
public class OpenAIController {

    private final OpenAIClientService openAIClientService;

    @PostMapping(value = "/chat", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ChatGPTResponse chat(@RequestBody ChatRequest chatRequest){
        return openAIClientService.chat(chatRequest);
    }

    @PostMapping(value = "/transcription", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public WhisperTranscriptionResponse createTranscription(@ModelAttribute TranscriptionRequest transcriptionRequest){
        return openAIClientService.createTranscription(transcriptionRequest);
    }

    @PostMapping(value = "/fine-tuning-model", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ChatGPTResponse createTranscription(@RequestBody ChatRequest chatRequest){
        return openAIClientService.reqFineTuningModel(chatRequest);
    }

    @PostMapping(value = "/moderation", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ModerationResponse createModeration(@RequestBody ModerationRequest moderationRequest){
        return openAIClientService.moderation(moderationRequest);
    }

}