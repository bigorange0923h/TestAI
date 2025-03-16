package org.bigorange.testai.mvc.controller;

import org.bigorange.testai.mvc.model.dto.RunTestDTO;
import org.bigorange.testai.mvc.service.TestService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("api/test")
public class TestController {

    @Resource
    TestService testService;

    @PostMapping("run")
    public void runAutomationTest(@RequestBody RunTestDTO runTestDTO) {
        testService.run(runTestDTO);
    }
}
