package com.mf.crypto.utility.json;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import software.amazon.awssdk.services.servicequotas.model.IllegalArgumentException;

@Component
public class JsonUtil {

    private final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    private final ObjectMapper objectmapper = getDefaultObjectMapper();

    private ObjectMapper getDefaultObjectMapper() {
        ObjectMapper defaultObjectMapper = new ObjectMapper();
        defaultObjectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        defaultObjectMapper.registerModule(new Jdk8Module()).registerModule(new JavaTimeModule());
        defaultObjectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return defaultObjectMapper;
    }

    public JsonNode parseJson(String jsonString) {
        try {
            return objectmapper.readTree(jsonString);
        } catch (JsonMappingException e) {
            logger.error("Failed to parse JSON string: " + e.getMessage());
            return null;
        } catch (JsonProcessingException e) {
            logger.error("Failed to parse JSON string: " + e.getMessage());
            return null;
        }

    }

    public <T> T parseJson(String jsonStr, Class<T> outputType) {
        try {
            ObjectMapper om = new ObjectMapper();
            T result = om.readValue(jsonStr, outputType);
            return result;
        } catch (JsonProcessingException e) {
            logger.error("Failed to parse JSON string: " + e.getMessage());
            return null;
        }
    }

    public <T> T parseJson(JsonNode node, Class<T> outputType) {
        try {
            ObjectMapper om = new ObjectMapper();
            T result = om.treeToValue(node, outputType);
            return result;
        } catch (JsonProcessingException e) {
            logger.error("Failed to parse JSON string: " + e.getMessage());
            return null;
        }
    }

    public <T> T toObject(JsonNode node, Class<T> javaClass) {
        try {
            return objectmapper.treeToValue(node, javaClass);
        } catch (JsonProcessingException e) {
            logger.error("Failed to convert JSON into specified class: " + e.getMessage());
            return null;
        } catch (IllegalArgumentException e) {
            logger.error("Illegal arguments, failed to convert JSON: " + e.getMessage());
            return null;
        }
    }

    public JsonNode toJson(Object object) {
        return objectmapper.valueToTree(object);
    }

    public String jsonString(JsonNode node) {
        ObjectWriter objectwriter = objectmapper.writer();
        objectwriter = objectwriter.with(SerializationFeature.INDENT_OUTPUT);
        try {
            return objectwriter.writeValueAsString(node);
        } catch (JsonProcessingException e) {
            logger.error("Failed to convert to JSON string: " + e.getMessage());
            return null;
        }
    }

    public String jsonStringNotPretty(JsonNode node) {
        ObjectWriter objectwriter = objectmapper.writer();
        try {
            return objectwriter.writeValueAsString(node);
        } catch (JsonProcessingException e) {
            logger.error("Failed to convert to JSON string: " + e.getMessage());
            return null;
        }
    }

    public String jsonStringNotPretty(Object object) {
        return jsonStringNotPretty(toJson(object));
    }

    public JsonNode createJsonNode(String keyValue, JsonNode nodeValue) {
        JsonNode jsonNode = objectmapper.createObjectNode().set(keyValue, nodeValue);
        return jsonNode;
    }
}
