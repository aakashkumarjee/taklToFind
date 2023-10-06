package com.hackathon.Magic.apis;

import com.hackathon.Magic.dtos.MagicRequest;
import com.hackathon.Magic.services.LabelService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainMagic {

        @Autowired
        MagicRequest magicRequest;

        @Autowired
    LabelService labelService;


        // Define a GET endpoint
        @GetMapping("/")
        public String sayHello() {
            return "Hello, World!";
        }

        @PostMapping("/magic-api/generate-keywords")
        public JSONObject generateKeywords(@RequestBody MagicRequest request) {
            JSONObject jsonObject = magicRequest.generateLabels(request);
            JSONObject output = labelService.mapResponse(jsonObject);
            return output;
        }

}
