package com.melita.weborder.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonConverter {

    private static JsonConverter jsonConverter;
    private ObjectMapper mapper;

    private JsonConverter() {
	mapper = new ObjectMapper();
    }

    public static JsonConverter getInstance() {
	if (jsonConverter == null)
	    jsonConverter = new JsonConverter();
	return jsonConverter;
    }

    public String convertToJson(Object object) {
	try {
	    return mapper.writeValueAsString(object);
	} catch (JsonProcessingException e) {
	    log.error("An error is occurred while converting object to json ", e);
	}
	return "";
    }

}
