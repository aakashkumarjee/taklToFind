package com.hackathon.Magic.services;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MagicService {

    @Autowired
    LabelService labelService;

    @Autowired
    HttpChannel httpChannel;

    public String getResponse(Map<String, Object> inputMap) throws IOException {
        Map<String, Object> labelsData = labelService.getLabelsData();
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
        prompt=prompt.concat(" Please dhang se map krde yaar, corresponding values chahiye mujhe, values values from Label Map, not keys");
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
}
