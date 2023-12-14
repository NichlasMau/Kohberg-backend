package com.example.kohbergbackend.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class DataController {
    @GetMapping("/data")
    public Map<String, Object> getJsonData() {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> responseData = new HashMap<>();

        try {
            Resource resource = new ClassPathResource("data.json");
            InputStream inputStream = resource.getInputStream();

            // Read the JSON data from the InputStream
            Map<?, ?> jsonData = objectMapper.readValue(inputStream, Map.class);
            responseData.put("data", jsonData);

            // Close the InputStream after reading
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            responseData.put("error", "Error occurred while reading JSON file");
        }

        return responseData;
    }
}