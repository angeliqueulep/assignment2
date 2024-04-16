package com.humber.edgeserver;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

    @GetMapping("/fallback")
    public String fallbackMethod(){
        return "This Service is taking too long to respond or is down. Please try again later";
    }
}
