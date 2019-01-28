package io.wanyxkhalil.abstinence.wub.sample.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/sample")
public class SampleController {

    @GetMapping("/test")
    public Object test() {
        return LocalDateTime.now();
    }
}
