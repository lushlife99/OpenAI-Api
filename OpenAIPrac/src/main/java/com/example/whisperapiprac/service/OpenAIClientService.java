package com.example.whisperapiprac.service;

import com.example.whisperapiprac.dto.*;
import com.example.whisperapiprac.openaiclient.OpenAIClient;
import com.example.whisperapiprac.openaiclient.OpenAIClientConfig;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.boot.json.JsonParser;
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
        Message userMessage = Message.builder()
                .role(ROLE_USER)
                .content(chatRequest.getQuestion())
                .build();

        Message systemMessage = Message.builder()
                .role(ROLE_SYSTEM)
                .content(chatRequest.getSystem())
                .build();

        List<Message> messages = List.of(systemMessage, userMessage);
        ChatGPTRequest chatGPTRequest = ChatGPTRequest.builder()
                .model(openAIClientConfig.getModel())
                .max_tokens(4095)
                .messages(messages)
                .build();
        return openAIClient.chat(chatGPTRequest);
    }
    public WhisperTranscriptionResponse createTranscription(TranscriptionRequest transcriptionRequest){




        WhisperTranscriptionRequest whisperTranscriptionRequest = WhisperTranscriptionRequest.builder()
                .model(openAIClientConfig.getAudioModel())
                .file(transcriptionRequest.getFile())
//                .timestamp_granularities(Collections.singletonList("segment"))
                .language("en")
//                .response_format("verbose_json")
                .prompt("Line break the text according to the minutes, seconds, and ms format below..\n" +
                        "[00:13.35]\n" +
                        "[00:20.03]\n" +
                        "[00:26.57]\n" +
                        "[00:32.97]\n" +
                        "[00:37.66]\n" +
                        "[00:45.66]\n" +
                        "[00:50.56]\n" +
                        "[00:57.25]\n" +
                        "[01:03.86]\n" +
                        "[01:10.52]\n" +
                        "[01:22.59]\n" +
                        "[01:29.03]\n" +
                        "[01:34.65]\n" +
                        "[01:42.14]\n" +
                        "[01:46.88]\n" +
                        "[01:54.63]\n" +
                        "[01:59.52]\n" +
                        "[02:06.24]\n" +
                        "[02:12.91]\n" +
                        "[02:19.58]\n" +
                        "[02:26.98]\n" +
                        "[02:39.62]\n" +
                        "[02:43.23]\n" +
                        "[02:46.52]\n" +
                        "[02:52.01]\n" +
                        "[02:59.49]\n" +
                        "[03:06.21]\n" +
                        "[03:12.33]\n" +
                        "[03:19.27]")
                .build();
        WhisperTranscriptionResponse transcription = openAIClient.createTranscription(whisperTranscriptionRequest);
        System.out.println(transcription.getText());
        return transcription;
    }

    public ModerationResponse moderation(ModerationRequest moderationRequest) {
        return openAIClient.moderation(moderationRequest);
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

        List<Message> messages = List.of(systemMessage, userMessage);


        ChatGPTRequest chatGPTRequest = ChatGPTRequest.builder()
                .max_tokens(2047)
                .model(openAIClientConfig.getFineTuningModel())
                .messages(messages)
                .build();
        ChatGPTResponse chat = openAIClient.chat(chatGPTRequest);
        System.out.println(chat);
//        String content = chat.getChoices().get(0).getMessage().getContent();
//        ObjectMapper mapper = new ObjectMapper();
//
//        try {
//            JsonNode jsonNode = mapper.readTree(content);
//            System.out.println("question: " + jsonNode.get("question").asText());
//            System.out.println("selectionList: " + jsonNode.get("selectionList").asInt());
//            System.out.println("answer: " + jsonNode.get("answer").asText());
//            System.out.println("comment: " + jsonNode.get("comment").asText());
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//
//        System.out.println(chat);
        return chat;
    }

}