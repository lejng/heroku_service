package com.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;

public class JsonUtils {
    public static String mapToJson(Map<String, Object> map){
        try {
            return new ObjectMapper().writeValueAsString(map);
        }catch (Exception e){
            return null;
        }
    }

    public static Map convertJsonToMap(String json){
        try {
            return new ObjectMapper().readValue(json, Map.class);
        }catch (Exception e){
            return null;
        }
    }
}
