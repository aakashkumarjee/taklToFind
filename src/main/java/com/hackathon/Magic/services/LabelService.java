package com.hackathon.Magic.services;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class LabelService {
    public JSONObject mapResponse(JSONObject jsonObject) {
        JSONObject data = new JSONObject();
        data.put("maritalStatus", "N");
        data.put("income", "2");
        data.put("lIncome", "2");
        data.put("hIncome", "2");
        data.put("occupation", "29");
        return data;
    }
}
