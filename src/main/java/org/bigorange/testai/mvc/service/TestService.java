package org.bigorange.testai.mvc.service;

import org.bigorange.testai.constants.LanguageType;
import org.bigorange.testai.handler.ScriptHandler;
import org.bigorange.testai.handler.ai.deepseek.DeepSeekHandler;
import org.bigorange.testai.mvc.model.dto.RunTestDTO;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    public void run(RunTestDTO runTestDTO) {

        DeepSeekHandler deepSeekHandler = new DeepSeekHandler();
        String code = deepSeekHandler.generateTestScript("");

        ScriptHandler scriptHandler = new ScriptHandler();
        scriptHandler.execute(runTestDTO.getLanguageType(),code);
        System.err.println("languageType");
    }
}
