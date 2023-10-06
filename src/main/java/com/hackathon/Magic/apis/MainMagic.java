package com.hackathon.Magic.apis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.hackathon.Magic.dtos.MagicRequest;
import com.hackathon.Magic.services.HttpChannel;
import com.hackathon.Magic.services.LabelService;
import com.hackathon.Magic.services.MagicService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class MainMagic {

    @Autowired
    MagicService magicService;

    @Autowired
    HttpChannel httpChannel;

    @Autowired
    LabelService labelService;

    ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/magic-api/generate-keywords")
    public HashMap<String, Object> generateKeywords(@RequestBody MagicRequest request) throws IOException {
        log.error("Input: {}",request.getTextInput());
        log.error("Input: {}",request);
        HashMap<String, String> map = magicService.generateLabels(request);


        log.error("Old Map: {}", map);
        Map<String, String> newMap = new HashMap<>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if(value ==  null){
                continue;
            }
            String stringValue = String.valueOf(value); // Convert Integer to String
            newMap.put(key, stringValue);
        }

        log.error("New Map: {}", newMap);


        JSONObject data = new JSONObject();

        for (Map.Entry<String, String> entry : newMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            data.put(key, value);
        }

        String out = new Gson().toJson(data);
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("KUCHBHI",out);
        log.error("output: {}",resultMap);
        return resultMap;
//        return formatOutput(output).toString();
    }
    @PostMapping("/magic-api/generate-keywords-1")
    public HashMap<String, String> generateKeywordsTest(@RequestBody MagicRequest request) {
        log.error("Input: {}",request.getTextInput());
        log.error("Input: {}",request);
        JSONObject jsonObject = magicService.generateLabelsTest(request);
        JSONObject output = labelService.mapResponse(jsonObject);
        String out = new Gson().toJson(output);
        HashMap<String, String> resultMap = new HashMap<>();
        resultMap.put("KUCHBHI",out);
        log.error("output: {}",resultMap);
        return resultMap;
//        return formatOutput(output).toString();
    }
    private HashMap<String,Object> formatOutput(JSONObject jsonObject){
        HashMap<String, Object> resultMap = new HashMap<>();
        HashMap<String, Object> map = new HashMap<>();

        // Assuming you have keys and values in your JSONObject
        for (String key : jsonObject.keySet()) {
            resultMap.put(key, jsonObject.get(key));
        }


        map.put("data",resultMap);
        return map;
    }
    private HashMap<String,Object> formatOutputAgain(HashMap<String, Object> map){

        return map;
    }
    @PostMapping("/magic-api/gen-api")
    public String getResponse(String prompt) throws IOException {
//        return httpChannel.genAiApi(prompt);
        String text =  httpChannel.genAiApiTest(prompt);

        System.out.println(text);
        return convertToHashmap(text).toString();
    }

    public HashMap<String, Object> convertToHashmap(String jsonString) throws JsonProcessingException {
        int firstBraceIndex = jsonString.indexOf("{");
        int lastBraceIndex = jsonString.lastIndexOf("}");


// Extract the JSON content between the first and last braces
        String trimmedJson = jsonString.substring(firstBraceIndex, lastBraceIndex + 1);

        HashMap<String, Object> hashMap = objectMapper.readValue(trimmedJson, HashMap.class);
        return hashMap;
    }
}
