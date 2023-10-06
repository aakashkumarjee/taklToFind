package com.hackathon.Magic.dtos;

import lombok.Data;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import java.util.List;

@Data
@Component
public class MagicRequest {
    String textInput;
    String labels;
    String base64EncodedVoiceInput = "";


    public JSONObject generateLabels(MagicRequest request) {
        return null;
    }
}
