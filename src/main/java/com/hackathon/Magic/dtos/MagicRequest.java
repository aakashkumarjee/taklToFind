package com.hackathon.Magic.dtos;

import lombok.Data;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class MagicRequest {
    String textInput;
    List<String> labels;
    String base64EncodedVoiceInput = "";

    public JSONObject generateLabels(MagicRequest request) {

        return null;
    }
}
