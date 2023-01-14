package com.fr.event.controller;

import com.fr.event.domain.ServiceObj;
import com.fr.event.service.ServiceService;
import com.fr.event.util.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/service")
public class ServiceController {

    final
    ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createService(@RequestBody ServiceObj serviceObj) {
        return serviceService.createService(serviceObj);
    }

    @GetMapping("/getServiceByName/{eventId}")
    public ResponseEntity<ApiResponse> getAllServiceByEventId(@PathVariable("eventId") Integer eventId) {
        return serviceService.getAllServiceByEventId(eventId);
    }

    @GetMapping("/getAllService")
    public ResponseEntity<ApiResponse> getAllServiceByEventId() {
        return serviceService.getAllService();
    }

    @PostMapping("/updateService/{title}/{percentage}")
    public ResponseEntity<ApiResponse> updateService(@PathVariable("title") String title, @PathVariable("percentage") String percentage) {
        return serviceService.updateService(title, percentage);
    }
}
