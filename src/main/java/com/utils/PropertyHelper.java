package com.utils;

import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class PropertyHelper {
    private static final String PROPERTY_PATH = "src/main/resources/";
    private String propertyFileName;

    public PropertyHelper(String propertyFileName){
        this.propertyFileName = propertyFileName;
    }

    public static String getPropertyValue(String name, String propertyFileName){
        return new PropertyHelper(propertyFileName).getProperty(name);
    }

    public String getProperty(String name){
        Properties property = load(PROPERTY_PATH + propertyFileName);
        return property.getProperty(name);
    }

    public List<PropertyInfo> getAllProperties(){
        Properties property = load(PROPERTY_PATH + propertyFileName);
        List<PropertyInfo> properties = new LinkedList<>();
        property.stringPropertyNames().forEach(name -> properties.add(new PropertyInfo(name, property.getProperty(name))));
        return properties;
    }

    public String getProperty(String name, String defaultValue){
        String result = getProperty(name);
        if(result == null){
            return result;
        }
        return defaultValue;
    }

    public int getProperty(String name, int defaultValue){
        return Integer.parseInt(getProperty(name, String.valueOf(defaultValue)));
    }

    private Properties load(String propertyPath){
        Properties property = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream(propertyPath);
            property.load(fileInputStream);
            return property;
        }catch (Exception e){
            return null;
        }
    }

    public class PropertyInfo {
        private String key;
        private String value;

        public PropertyInfo(String key, String value){
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
