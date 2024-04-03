package com.example.whisperapiprac.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ModerationResponse {
    private String id;
    private String model;
    private Result result;

    @Data
    public class Result {
        private boolean flagged;
        private Categories categories;
        private CategoryScores category_scores;


    }

    @Data
    public class Categories {
        private boolean sexual;
        private boolean hate;
        private boolean harassment;
        @JsonProperty("self-harm")
        private boolean selfHarm;
        @JsonProperty("sexual/minors")
        private boolean sexualMinors;
        @JsonProperty("hate/threatening")
        private boolean hateThreatening;
        @JsonProperty("violence/graphic")
        private boolean violenceGraphic;
        @JsonProperty("self-harm/intent")
        private boolean selfHarmIntent;
        @JsonProperty("self-harm/instructions")
        private boolean selfHarmInstructions;
        @JsonProperty("harassment/threatening")
        private boolean harassmentThreatening;
        private boolean violence;
    }

    @Data
    public class CategoryScores {
        private double sexual;
        private double hate;
        private double harassment;
        @JsonProperty("self-harm")
        private double selfHarm;
        @JsonProperty("sexual/minors")
        private double sexualMinors;
        @JsonProperty("hate/threatening")
        private double hateThreatening;
        @JsonProperty("violence/graphic")
        private double violenceGraphic;
        @JsonProperty("self-harm/intent")
        private double selfHarmIntent;
        @JsonProperty("self-harm/instructions")
        private double selfHarmInstructions;
        @JsonProperty("harassment/threatening")
        private double harassmentThreatening;
        private double violence;
    }
}
