package org.bigorange.testai.handler.ai.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
// 响应体
public class DeepSeekResponse {
    private String id;
    private String object;
    private long created;
    private List<Choice> choices;

    @Getter
    @Setter
    // 嵌套的 Choice 类
    public static class Choice {
        private int index;
        private Message message;
        private String finish_reason;

        @Getter
        @Setter
        // 嵌套的 Message 类
        public static class Message {
            private String role;
            private String content;
        }
    }
}

