package com.platzi_play.web.controller;


import com.platzi_play.domain.service.PlatziPlayAiServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    private  final PlatziPlayAiServices platziPlayAiServices;

    public HelloController(PlatziPlayAiServices platziPlayAiServices) {
        this.platziPlayAiServices = platziPlayAiServices;
    }


    @GetMapping("/hello")
    public String hello(){
        return platziPlayAiServices.generateGreeting();
    }
}
