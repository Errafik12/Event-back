package com.fr.event.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class ResponseBuilder {
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), data);
    }

    public static <T> ApiResponse<T> badRequest(T data) {
        return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), data);
    }

    public static <T> ApiResponse<T> created(T data) {
        return new ApiResponse<>(HttpStatus.CREATED.value(), HttpStatus.CREATED.getReasonPhrase(), data);
    }

    public static <T> ApiResponse<T> success(T data, Map<String, Object> additionalParams) {
        ApiResponse<T> response = success(data);
        response.setAdditionalParams(additionalParams);
        return response;
    }

    public static <T> ApiResponse<T> forbidden(T data) {
        return new ApiResponse<>(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.getReasonPhrase(), data);
    }

    public static <T> ApiResponse<T> failed(T data, Exception ex) {
        return new ApiResponse<>(HttpStatus.NO_CONTENT.value(), ex.getMessage(), data);
    }

    public static <T> ApiResponse<T> failed(T data, Exception ex, Map<String, Object> additionalParams) {
        ApiResponse<T> failedResponse = failed(data, ex);
        failedResponse.setAdditionalParams(additionalParams);
        return failedResponse;
    }

    public static ResponseEntity<ApiResponse> build(ApiResponse apiResponse) {
        if (apiResponse.getStatusCode() == HttpStatus.OK.value()) {
            return ResponseEntity.ok(apiResponse);
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(apiResponse);
    }
}
