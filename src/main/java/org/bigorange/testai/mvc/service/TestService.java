package org.bigorange.testai.mvc.service;

import lombok.extern.slf4j.Slf4j;
import org.bigorange.testai.constants.LanguageType;
import org.bigorange.testai.handler.ScriptHandler;
import org.bigorange.testai.handler.SeleniumHandler;
import org.bigorange.testai.handler.ai.deepseek.DeepSeekHandler;
import org.bigorange.testai.mvc.model.dto.RunTestDTO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class TestService {
    
    @Resource
    SeleniumHandler seleniumHandler;
    public void run(RunTestDTO runTestDTO) {
        log.info("加载html");
        String html = seleniumHandler.fetchHtml(runTestDTO.getUrl());
        DeepSeekHandler deepSeekHandler = new DeepSeekHandler();
        ScriptHandler scriptHandler = new ScriptHandler();
        scriptHandler.execute(runTestDTO.getLanguageType(),deepSeekHandler.generateTestScript(""));
        System.err.println("languageType");
    }
}
