package com.hackathon.Magic.services;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class LabelService {
    public JSONObject mapResponse(JSONObject jsonObject) {
        JSONObject json = new JSONObject();
        json.put("maritalStatus", "N");
        json.put("income", "2");
        json.put("occupation", "29");
        return json;
    }
}
