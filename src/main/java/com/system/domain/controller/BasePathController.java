package com.system.domain.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/")
@CrossOrigin("https://contact-book-4e449.web.app")
@ApiIgnore
public class BasePathController {
    @GetMapping
    public ObjectNode sayApiDoc() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.put("documentação", "https://contact-book-api-heroku.herokuapp.com/swagger-ui.html");
        return objectNode;
    }
    
}
