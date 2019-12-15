package com.arun.ssecurty.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicSecurityController {

    @GetMapping("/v1/basic")
    public ResponseEntity<String> getBasic() {
        return ResponseEntity.ok("Basic");
    }

    @GetMapping("/v2/basic")
    public ResponseEntity<String> getBasicV2() {
        return ResponseEntity.ok("BasicV2");
    }

    @GetMapping("/v3/basic")
    public ResponseEntity<String> getBasicV3(){
        return ResponseEntity.ok("Basic V3");
    }
}
