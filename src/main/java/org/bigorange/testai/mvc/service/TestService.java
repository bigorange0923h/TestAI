package org.bigorange.testai.mvc.service;

import org.bigorange.testai.handler.ai.deepseek.DeepSeekHandler;
import org.bigorange.testai.mvc.model.dto.RunTestDTO;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    public void run(RunTestDTO runTestDTO) {
        DeepSeekHandler deepSeekHandler = new DeepSeekHandler();
        String s = deepSeekHandler.generateTestScript("");
        System.err.println(s);
    }
}
