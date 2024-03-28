package com.example.whisperapiprac.service;

import com.example.whisperapiprac.dto.*;
import com.example.whisperapiprac.openaiclient.OpenAIClient;
import com.example.whisperapiprac.openaiclient.OpenAIClientConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OpenAIClientService {

    private final OpenAIClient openAIClient;
    private final OpenAIClientConfig openAIClientConfig;

    private final static String ROLE_USER = "user";
    private final static String ROLE_SYSTEM = "system";
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

    public ChatGPTResponse reqFineTuningModel(ChatRequest chatRequest) {
        Message userMessage = Message.builder()
                .role(ROLE_USER)
                .content(chatRequest.getQuestion())
                .build();

        Message systemMessage = Message.builder()
                .role(ROLE_SYSTEM)
                .content(chatRequest.getSystem())
                .build();

        List<Message> messages = List.of(userMessage, systemMessage);

        ChatGPTRequest chatGPTRequest = ChatGPTRequest.builder()
                .max_tokens(2048)
                .model(openAIClientConfig.getFineTuningModel())
                .messages(messages)
                .build();

        return openAIClient.chat(chatGPTRequest);
    }

}