package org.bigorange.testai.handler.ai.dto;

import lombok.Data;
import org.bigorange.testai.constants.RoleType;

import java.util.List;

@Data
public class AiRequest {
    private String model = "deepseek-chat";
    private List<Message> messages;
    private Boolean stream = false;

    @Data
    public static class Message{
        private RoleType role;
        private String content;
    }
}
