package com.JPA.demo.Controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class BaseController {

	protected <T> ResponseEntity<T> getResponseInJsonType(T entity, HttpStatus status) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<T>(entity, responseHeaders, status);
    }
}
