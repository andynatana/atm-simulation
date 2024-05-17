package com.example.demo.pojo;

public record WSResponse(int statusCode, String message, Object body) {
}
