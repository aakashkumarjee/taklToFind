package com.hackathon.Magic.services;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class LabelService {
    public JSONObject mapResponse(JSONObject jsonObject) {
        JSONObject json = new JSONObject();
        JSONObject data = new JSONObject();
        data.put("maritalStatus", "N");
        data.put("income", "2");
        data.put("occupation", "29");
        json.put("data", data);
        return json;
    }
}
