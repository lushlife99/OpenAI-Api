package com.example.whisperapiprac.service;

import com.example.whisperapiprac.dto.*;
import com.example.whisperapiprac.openaiclient.OpenAIClient;
import com.example.whisperapiprac.openaiclient.OpenAIClientConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;

@RequiredArgsConstructor
@Service
public class OpenAIClientService {

    private final OpenAIClient openAIClient;
    private final OpenAIClientConfig openAIClientConfig;

    private final static String ROLE_USER = "user";
    public ChatGPTResponse chat(ChatRequest chatRequest){
        Message message = Message.builder()
                .role(ROLE_USER)
                .content(chatRequest.getQuestion())
                .build();
        ChatGPTRequest chatGPTRequest = ChatGPTRequest.builder()
                .model(openAIClientConfig.getModel())
                .messages(Collections.singletonList(message))
                .build();
        return openAIClient.chat(chatGPTRequest);
    }
    public WhisperTranscriptionResponse createTranscription(TranscriptionRequest transcriptionRequest){
        WhisperTranscriptionRequest whisperTranscriptionRequest = WhisperTranscriptionRequest.builder()
                .model(openAIClientConfig.getAudioModel())
                .file(transcriptionRequest.getFile())
                .build();

        return openAIClient.createTranscription(whisperTranscriptionRequest);
    }

}