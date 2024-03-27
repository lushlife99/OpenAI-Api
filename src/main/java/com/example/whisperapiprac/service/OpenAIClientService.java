package com.example.whisperapiprac.service;

import com.example.whisperapiprac.dto.TranscriptionRequest;
import com.example.whisperapiprac.dto.WhisperTranscriptionRequest;
import com.example.whisperapiprac.dto.WhisperTranscriptionResponse;
import com.example.whisperapiprac.openaiclient.OpenAIClient;
import com.example.whisperapiprac.openaiclient.OpenAIClientConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OpenAIClientService {

    private final OpenAIClient openAIClient;
    private final OpenAIClientConfig openAIClientConfig;

    public WhisperTranscriptionResponse createTranscription(TranscriptionRequest transcriptionRequest){
        WhisperTranscriptionRequest whisperTranscriptionRequest = WhisperTranscriptionRequest.builder()
                .model(openAIClientConfig.getAudioModel())
                .file(transcriptionRequest.getFile())
                .build();

        return openAIClient.createTranscription(whisperTranscriptionRequest);
    }

}