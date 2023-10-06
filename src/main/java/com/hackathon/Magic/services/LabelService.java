package com.hackathon.Magic.services;

import com.hackathon.Magic.common.UtilityFunctions;
import jakarta.annotation.PostConstruct;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LabelService {

    public static final String LABELS_JSON_FILE_NAME = "/labelsNew.json";
    public Map<String, Object> labelsData;
    public JSONObject mapResponse(JSONObject jsonObject) {
        JSONObject data = new JSONObject();
        data.put("maritalStatus", "N");
        data.put("income", "2");
        data.put("lIncome", "2");
        data.put("hIncome", "2");
        data.put("occupation", "29");
        return data;
    }

    @PostConstruct
    public void loadLabelsMData() {
        labelsData = UtilityFunctions.getMasterDataFromJson(LABELS_JSON_FILE_NAME);
    }

    public Map<String, Object> getLabelsData() {
        return labelsData;
    }
}
