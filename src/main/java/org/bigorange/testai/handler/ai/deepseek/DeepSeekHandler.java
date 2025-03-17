package org.bigorange.testai.handler.ai.deepseek;

import org.bigorange.testai.constants.RoleType;
import org.bigorange.testai.handler.ai.ChatAIHandler;
import org.bigorange.testai.handler.ai.dto.AiRequest;
import org.bigorange.testai.handler.ai.dto.DeepSeekRequest;
import org.bigorange.testai.handler.ai.dto.DeepSeekResponse;
import org.bigorange.testai.utils.GetCodeUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class DeepSeekHandler extends ChatAIHandler {

    @Value("${ai.deepseek.api.url}")
    private String apiUrl = "https://api.deepseek.com/chat/completions";

    @Value("${ai.deepseek.api.key}")
    private String apiKey = "test";
    private final RestTemplate restTemplate = new RestTemplate();

    //todo 实现DeepSeek 接口的请求
    // 可能需要format 返回内容,基于这个生成脚本文件
    // 后续开始测试脚本运行
    @Override
    public String getScriptCode(String prompt) {
        return GetCodeUtils.extractFirstCodeBlock(generateCodeText(prompt));
    }

    private String generateCodeText(String prompt) {
        // 1. 构建请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey); // JDK8 需手动设置

        // 2. 构建请求体
        DeepSeekRequest requestBody = new DeepSeekRequest();
        requestBody.setModel("deepseek-chat");
        List<DeepSeekRequest.Message> messages = new ArrayList<>();
        messages.add(new DeepSeekRequest.Message("user", prompt));
        requestBody.setMessages(messages);

        // 3. 发送请求
        HttpEntity<DeepSeekRequest> request = new HttpEntity<>(requestBody, headers);
        ResponseEntity<DeepSeekResponse> response = restTemplate.exchange(
                apiUrl,
                HttpMethod.POST,
                request,
                DeepSeekResponse.class
        );

        // 4. 处理响应
        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return response.getBody().getChoices().get(0).getMessage().getContent();
        } else {
            throw new RuntimeException("API 请求失败: " + response.getStatusCode());
        }
    }


}
