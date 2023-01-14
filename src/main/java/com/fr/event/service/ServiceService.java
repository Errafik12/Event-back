package com.fr.event.service;

import com.fr.event.domain.ServiceObj;
import com.fr.event.util.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface ServiceService {

    ResponseEntity<ApiResponse> createService(ServiceObj serviceObj);

    ResponseEntity<ApiResponse> updateService(String title, String percentage);

    ResponseEntity<ApiResponse> getAllServiceByEventId(Integer eventId);

    ResponseEntity<ApiResponse> getAllService();
}
