package com.hackathon.Magic.services;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.json.JSONObject;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;


@Service
public class HttpChannel {
    public String genAiApi(String prompt) throws JsonProcessingException {
        // Define the API URL
        String apiUrl = "http://services.test2.ff-services-test2.cluster.infoedge.com/prompt-execute-services/vertex/vertex-api/text/completions";

        // Create an instance of RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // Set the request headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("AppId", "1");
        headers.set("SystemId", "1");
        headers.set("templateCode", "HACKATHON_3");
        headers.set("key", "897f28c9c3ee9860a1a3f564193e632d86f2fb0709e6d58ee085089e0bd8fbed08b05ee52a8d9794");
        headers.set("Cookie", "_t_ds=271160863224221-1695813206-1695813206-1695813206; _t_ds=36932965681002601-1696490153-1696490153-1696490153");

        JSONObject requestBody = new JSONObject();
        requestBody.put("prompt", prompt);
        requestBody.put("model", "text-bison");
        JSONObject parameters = new JSONObject();
        parameters.put("temperature", 1);
        parameters.put("maxOutputTokens", 250);
        parameters.put("topK", 1);
        parameters.put("topP", 0.5);
        requestBody.put("parameters", parameters);
        ObjectMapper objectMapper = new ObjectMapper();

        // Create the request entity with headers
        HttpEntity<String> requestEntity = new HttpEntity<>(new Gson().toJson(requestBody), headers);
        // Send the POST request
        ResponseEntity<String> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.POST, (org.springframework.http.HttpEntity<?>) requestEntity, String.class);

        // Get the HTTP response code and response body
        int statusCode = responseEntity.getStatusCodeValue();
        String responseBody = responseEntity.getBody();

        // Print the response code and response body
        System.out.println("Response Code: " + statusCode);
        System.out.println("API Response: " + responseBody);
        return null;
    }

    public String genAiApiTest(String prompt) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();

        // Set request headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("AppId", "1");
        headers.set("SystemId", "1");
        headers.set("templateCode", "HACKATHON_3");
        headers.set("key", "897f28c9c3ee9860a1a3f564193e632d86f2fb0709e6d58ee085089e0bd8fbed08b05ee52a8d9794");
        headers.set("Cookie", "_t_ds=271160863224221-1695813206-1695813206-1695813206; _t_ds=36932965681002601-1696490153-1696490153-1696490153");

        // Request body in JSON format
        String requestBody = "{\n" +
                "    \"prompt\" : \"replace_me\",\n" +
                "    \"parameters\" : {\n" +
                "        \"temperature\" : 1,\n" +
                "        \"maxOutputTokens\" : 250,\n" +
                "        \"topK\" : 1,\n" +
                "        \"topP\" : 0.5\n" +
                "    },\n" +
                "    \"model\" : \"text-bison\"\n" +
                "}";

        requestBody = requestBody.replace("replace_me", prompt);

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        // Set character encoding to UTF-8 to handle special characters in the response
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));

        // Send the POST request
        ResponseEntity<String> response = restTemplate.exchange(
                "http://services.test2.ff-services-test2.cluster.infoedge.com/prompt-execute-services/vertex/vertex-api/text/completions",
                HttpMethod.POST,
                entity,
                String.class
        );

        // Handle the response
        if (response.getStatusCode() == HttpStatus.OK) {
            String responseBody = response.getBody();

            ObjectMapper objectMapper = new ObjectMapper();

            // Parse the JSON response body
            JsonNode rootNode = objectMapper.readTree(responseBody);

            // Get the "response" from the "data" object
            JsonNode responseData = rootNode.get("data");
            System.out.println("Response: " + responseData.get("response").asText());
            return  responseData.get("response").asText();
        } else {
            System.err.println("Request failed with status code: " + response.getStatusCode());
            return null;
        }
    }
}
