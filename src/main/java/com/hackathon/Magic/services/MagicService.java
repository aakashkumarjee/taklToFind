package com.hackathon.Magic.services;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackathon.Magic.dtos.MagicRequest;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MagicService {

    ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    LabelService labelService;
    @Autowired
    HttpChannel httpChannel;

    public String getFinalResponse(Map<String, String> inputMap) throws IOException {
        Map<String, String> labelsData = labelService.getLabelsData();
//        inputMap.put("occupation", "Software Engineer");
//        inputMap.put("diet", "Egg");
//        inputMap.put("religion","Hindu");
//        inputMap.put("mtongue", "Hindi");
//        inputMap.put("maritalStatus","unmarried");

        String prompt = "Given the following JSON input and Label Map. I want JSON output where values in JSON input are to be mapped with to its corresponding values in Label Map:\\n\\n";
        prompt=prompt.concat("JSON Input:\\n");
        prompt=prompt.concat(inputMap.toString());
        prompt=prompt.concat("\\n\\nLabel Map:\\n");
        prompt=prompt.concat(labelsData.toString());
        prompt=prompt.concat(" Please dhang se map krde yaar, corresponding values chahiye mujhe, values values from Label Map, not keys. Give Values and also the response should be in JSON Format");
        prompt = escapeJsonQuotes(prompt);

        return httpChannel.genAiApiTest(prompt);
    }

    public static String escapeJsonQuotes(String input) {
        StringBuilder result = new StringBuilder();
        boolean insideString = false;

        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);

            if (currentChar == '"') {
                if (i > 0 && input.charAt(i - 1) != '\\') {
                    // This is an unescaped double quote, escape it
                    result.append("\\\"");
                } else {
                    // This is an escaped double quote, leave it as is
                    result.append('"');
                }
            } else {
                result.append(currentChar);
            }
        }

        return result.toString();
    }
    public JSONObject generateLabelsTest(MagicRequest request) {
        return null;
    }
    public HashMap<String,String> generateLabels(MagicRequest request) throws IOException {
        HashMap<String,String> text = new HashMap<>();
        try{
            String prompt = request.getTextInput() + "read the text Just Before & extract the data from this. label values can be - maritalStatus, religion, cast, mTongue,income, country, residencyStatus, diet, education, occupation, height  .  get me output in key value pair and in json, remove the labels without any value.  the output should be plain json and nothing else. Keep in mind.";
            text = getResponse(prompt);
        }catch (Exception e){
            log.error("Got Exception while extracting labels: {}", request, e);
        }
        String finalText =  getFinalResponse(text);
        return convertToHashmap(finalText);
    }
    public HashMap<String, String> getResponse(String prompt) throws IOException {
//        return httpChannel.genAiApi(prompt);
        String text =  httpChannel.genAiApiTest(prompt);

        System.out.println(text);
        return convertToHashmap(text);
    }

    public HashMap convertToHashmap(String jsonString) throws JsonProcessingException {
        log.error("\nstring: {}", jsonString);
        int firstBraceIndex = jsonString.indexOf("{");
        int lastBraceIndex = jsonString.lastIndexOf("}");


// Extract the JSON content between the first and last braces
        String trimmedJson = jsonString.substring(firstBraceIndex, lastBraceIndex + 1);

        log.error("\ntrimmed string: {}", trimmedJson);
        return objectMapper.readValue(trimmedJson, HashMap.class);
    }
}
