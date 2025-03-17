package org.bigorange.testai.handler.ai.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class DeepSeekRequest {
    private String model;
    private List<Message> messages;

    @Getter
    @Setter
    // 嵌套的 Message 类
    public static class Message {
        private String role;
        private String content;
        public Message(String role, String content) {
            this.role = role;
            this.content = content;
        }
    }
}

