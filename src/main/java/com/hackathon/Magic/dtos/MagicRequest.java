package com.hackathon.Magic.dtos;

import lombok.Data;
import org.json.JSONObject;

import java.util.List;

@Data
public class MagicRequest {
    String textInput;
    List<String> labels;
    String base64EncodedVoiceInput = "";

    public JSONObject generateLabels(MagicRequest request) {

        return null;
    }
}
