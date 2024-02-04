package com.chethiya.visa.helloworld.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Chethiya
 *
 * JSON Util Class
 */
@Slf4j
public class JsonUtil {

    private JsonUtil(){}

    /**
     * Converts object into JSON
     * @return JSON String
     */
    public static String toJson(Object object) {
        try {
            ObjectWriter ow = new ObjectMapper().writer();
            return ow.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("Error in converting object to json");
            // Return empty json in-case of an error
            return "{}";
        }
    }
}
