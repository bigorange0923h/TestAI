package org.bigorange.testai.handler.ai.dto;

import lombok.Data;

import java.util.List;

@Data
public class AiResponse {
    private String id;
    private List<Choice> choices;
    // 嵌套对象
    @Data
    public static class Choice {
        private String text;
        // Getters & Setters
    }
}
