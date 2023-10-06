package com.hackathon.Magic.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

@Slf4j
public class UtilityFunctions {

	@SuppressWarnings("unchecked")
	public static Map<String, Object> getMasterDataFromJson(String jsonFile) {
		try {
			String calsString = convertToString(jsonFile);
			ObjectMapper jsonMapper = new ObjectMapper();
			return (Map<String, Object>) jsonMapper.readValue(calsString, Map.class);
		} catch (IOException ex) {
//				log.error("Unsupported Encoding Exception : ", ex);
			ex.printStackTrace();
		}
		return null;
	}

	private static String convertToString(String inputFile) throws IOException {
		InputStream inputStream = UtilityFunctions.class.getResourceAsStream(inputFile);
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		StringBuffer stringBuffer = new StringBuffer();
		String line;
		while ((line = bufferedReader.readLine()) != null)
		{
			stringBuffer.append(line);
		}
		bufferedReader.close();
		inputStreamReader.close();
		inputStream.close();
		return stringBuffer.toString();
	}

}
