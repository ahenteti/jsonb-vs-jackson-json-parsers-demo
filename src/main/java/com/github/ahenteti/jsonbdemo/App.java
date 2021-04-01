package com.github.ahenteti.jsonbdemo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.json.JsonValue;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

public class App {

    private static Jsonb jsonb = JsonbBuilder.create();
    private static ObjectMapper jacksonObjectMapper = new ObjectMapper();

    public static void main(String[] args) {
        System.out.println("******************************");
        System.out.println("* JSON parsing using Jackson *");
        System.out.println("******************************");
        parseJsonUsingJacksonObjectMapper("{}");
        parseJsonUsingJacksonObjectMapper("[]");
        parseJsonUsingJacksonObjectMapper("123");
        parseJsonUsingJacksonObjectMapper("\"text\"");
        parseJsonUsingJacksonObjectMapper("true");
        
        System.out.println();
        System.out.println("******************************");
        System.out.println("*  JSON parsing using JsonB  *");
        System.out.println("******************************");
        parseJsonUsingJsonB("{}");
        parseJsonUsingJsonB("[]");
        parseJsonUsingJsonB("123");
        parseJsonUsingJsonB("\"text\"");
        parseJsonUsingJsonB("true");
    }

    private static void parseJsonUsingJsonB(String json) {
        try {
            JsonValue jsonValue = jsonb.fromJson(json, JsonValue.class);
            System.out.println(String.format("%-15s", "json: " + json) + "-> type: " + jsonValue.getClass());
        } catch (Exception e) {
            System.out.println("Error while parsing '" + json + "' using JsonB. Exception: " + e.getClass() + ". message: " + e.getMessage());
        }
    }

    private static void parseJsonUsingJacksonObjectMapper(String json) {
        try {
            JsonNode jsonValue = jacksonObjectMapper.readValue(json, JsonNode.class);
            System.out.println(String.format("%-15s", "json: " + json) + "-> type: " + jsonValue.getClass());
        } catch (Exception e) {
            System.out.println("Error while parsing '" + json + "' using Jackson ObjectMapper. Exception: " + e.getClass() + ". message: " + e.getMessage());
        }
    }
}
