package org.bigorange.testai.handler.ai.deepseek;

import org.apache.commons.compress.utils.Lists;
import org.bigorange.testai.constants.RoleType;
import org.bigorange.testai.handler.ai.ChatAIHandler;
import org.bigorange.testai.handler.ai.dto.AiRequest;
import org.bigorange.testai.handler.ai.dto.AiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import org.springframework.http.HttpStatus;

import java.util.Collections;


public class DeepSeekHandler extends ChatAIHandler {

    @Value("${ai.deepseek.api.url}")
    private String apiUrl="https://api.deepseek.com/chat/completions";

    @Value("${ai.deepseek.api.key}")
    private String apiKey="og23$SFR5@!#2Db%x&!fMz2bj6m0iO";

    //todo 实现DeepSeek 接口的请求
    // 可能需要format 返回内容,基于这个生成脚本文件
    // 后续开始测试脚本运行
    @Override
    public String getScriptCode(String prompt) {
        AiRequest request = new AiRequest();
        AiRequest.Message message = new AiRequest.Message();
        message.setRole(RoleType.USER);
        message.setContent(prompt);
        request.setMessages(Collections.singletonList(message));
        String s = generateText(request);
        return "";
    }

    private String generateText(AiRequest request) {
        WebClient client = WebClient.builder()
                .defaultHeader("Authorization", "Bearer " + apiKey)
                .build();
        return client.post()
                .uri(apiUrl)
                .bodyValue(request)
                .retrieve()
                .onStatus(HttpStatus::isError, response ->
                        Mono.error(new RuntimeException("API Error: " + response.statusCode())))
                .bodyToMono(AiResponse.class)
                .map(res -> res.getChoices().get(0).getText())
                .block();
    }
}
