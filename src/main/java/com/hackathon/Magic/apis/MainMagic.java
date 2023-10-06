package com.hackathon.Magic.apis;

import com.google.gson.Gson;
import com.hackathon.Magic.dtos.MagicRequest;
import com.hackathon.Magic.services.HttpChannel;
import com.hackathon.Magic.services.LabelService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;

@RestController
@Slf4j
public class MainMagic {

    @Autowired
    MagicRequest magicRequest;

    @Autowired
    HttpChannel httpChannel;

    @Autowired
    LabelService labelService;

    @PostMapping("/magic-api/generate-keywords")
    public HashMap<String, String> generateKeywords(@RequestBody MagicRequest request) {
        JSONObject jsonObject = magicRequest.generateLabels(request);
        log.error("Input: {}",request.getTextInput());
        log.error("Input: {}",request);
        JSONObject output = labelService.mapResponse(jsonObject);
        String out = new Gson().toJson(output);
        HashMap<String, String> resultMap = new HashMap<>();
        resultMap.put("KUCHBHI",out);
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
        return httpChannel.genAiApiTest(prompt);
    }



}
