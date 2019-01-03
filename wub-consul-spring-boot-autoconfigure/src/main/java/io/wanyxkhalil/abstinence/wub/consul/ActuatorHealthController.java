package io.wanyxkhalil.abstinence.wub.consul;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActuatorHealthController {

    @GetMapping("/actuator/health")
    public ResponseEntity<String> healthCheck() {
        return new ResponseEntity<>("check", HttpStatus.OK);
    }
}
