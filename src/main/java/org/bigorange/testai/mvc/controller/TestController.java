package org.bigorange.testai.mvc.controller;

import org.bigorange.testai.mvc.model.dto.RunTestDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("api/test")
public class TestController {

    @PostMapping
    public void runAutomationTest(@RequestBody RunTestDTO runTestDTO){

    }
}
