# JsonB vs Jackson JSON parsers

```java
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
        System.out.println("*********************************");
        System.out.println("* JSON conversion using Jackson *");
        System.out.println("*********************************");
        convertJsonUsingJacksonObjectMapper("{}");
        convertJsonUsingJacksonObjectMapper("[]");
        convertJsonUsingJacksonObjectMapper("123");
        convertJsonUsingJacksonObjectMapper("\"text\"");
        convertJsonUsingJacksonObjectMapper("true");
        
        System.out.println();
        System.out.println("*********************************");
        System.out.println("*  JSON conversion using JsonB  *");
        System.out.println("*********************************");
        convertJsonUsingJsonB("{}");
        convertJsonUsingJsonB("[]");
        convertJsonUsingJsonB("123");
        convertJsonUsingJsonB("\"text\"");
        convertJsonUsingJsonB("true");
    }

    private static void convertJsonUsingJsonB(String json) {
        try {
            JsonValue jsonValue = jsonb.fromJson(json, JsonValue.class);
            System.out.println(String.format("%-15s", "json: " + json) + "-> type: " + jsonValue.getClass());
        } catch (Exception e) {
            System.out.println("Error while converting '" + json + "' using JsonB. Exception: " + e.getClass() + ". message: " + e.getMessage());
        }
    }

    private static void convertJsonUsingJacksonObjectMapper(String json) {
        try {
            JsonNode jsonValue = jacksonObjectMapper.readValue(json, JsonNode.class);
            System.out.println(String.format("%-15s", "json: " + json) + "-> type: " + jsonValue.getClass());
        } catch (Exception e) {
            System.out.println("Error while converting '" + json + "' using Jackson ObjectMapper. Exception: " + e.getClass() + ". message: " + e.getMessage());
        }
    }
}
```

## Output

```
*********************************
* JSON conversion using Jackson *
*********************************
json: {}       -> type: class com.fasterxml.jackson.databind.node.ObjectNode
json: []       -> type: class com.fasterxml.jackson.databind.node.ArrayNode
json: 123      -> type: class com.fasterxml.jackson.databind.node.IntNode
json: "text"   -> type: class com.fasterxml.jackson.databind.node.TextNode
json: true     -> type: class com.fasterxml.jackson.databind.node.BooleanNode

*********************************
*  JSON conversion using JsonB  *
*********************************
json: {}       -> type: class org.glassfish.json.JsonObjectBuilderImpl$JsonObjectImpl
Error while converting '[]' using JsonB. Exception: class javax.json.bind.JsonbException. message: Can't deserialize JSON array into: interface javax.json.JsonValue
Error while converting '123' using JsonB. Exception: class java.util.NoSuchElementException. message: null
Error while converting '"text"' using JsonB. Exception: class java.util.NoSuchElementException. message: null
Error while converting 'true' using JsonB. Exception: class java.util.NoSuchElementException. message: null
```