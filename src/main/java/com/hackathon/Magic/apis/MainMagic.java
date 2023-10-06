package com.hackathon.Magic.apis;

import com.hackathon.Magic.dtos.MagicRequest;
import com.hackathon.Magic.services.LabelService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

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
        public HashMap<String, Object> generateKeywords(@RequestBody MagicRequest request) {
            JSONObject jsonObject = magicRequest.generateLabels(request);
            JSONObject output = labelService.mapResponse(jsonObject);
            return formatOutput(output);
        }
        private HashMap<String,Object> formatOutput(JSONObject jsonObject){
            HashMap<String, Object> resultMap = new HashMap<>();

            // Assuming you have keys and values in your JSONObject
            for (String key : jsonObject.keySet()) {
                resultMap.put(key, jsonObject.get(key));
            }

            return resultMap;
        }

}
