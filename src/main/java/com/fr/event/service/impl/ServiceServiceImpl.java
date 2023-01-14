package com.fr.event.service.impl;

import com.fr.event.domain.ServiceObj;
import com.fr.event.domain.res.Response;
import com.fr.event.repository.ServiceRepository;
import com.fr.event.service.ServiceService;
import com.fr.event.util.ApiResponse;
import com.fr.event.util.ResponseBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceServiceImpl implements ServiceService {

    final
    ServiceRepository serviceRepository;

    public ServiceServiceImpl(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public ResponseEntity<ApiResponse> createService(ServiceObj serviceObj) {
        Response rs = new Response();
        try {
            if (serviceRepository.createService(serviceObj) > 0) {
                rs.setOutMsgCode(0);
                rs.setOutMsgDesc("SUCCESS");
                return ResponseBuilder.build(ResponseBuilder.created(rs));
            } else {
                rs.setOutMsgCode(1);
                rs.setOutMsgDesc("FAIL");
            }
        } catch (Exception ex) {
            rs.setOutMsgCode(1);
            rs.setOutMsgDesc(ex.getMessage());
        }
        return ResponseBuilder.build(ResponseBuilder.badRequest(rs));
    }

    @Override
    public ResponseEntity<ApiResponse> updateService(String title, String percentage) {
        Response rs = new Response();
        if (serviceRepository.updateService(title, percentage) > 0) {
            rs.setOutMsgCode(0);
            rs.setOutMsgDesc("SUCCESS");
            return ResponseBuilder.build(ResponseBuilder.created(rs));
        } else {
            rs.setOutMsgCode(1);
            rs.setOutMsgDesc("FAIL");
            return ResponseBuilder.build(ResponseBuilder.badRequest(rs));
        }
    }

    @Override
    public ResponseEntity<ApiResponse> getAllServiceByEventId(Integer eventId) {
        List<ServiceObj> allServiceByEventId = serviceRepository.getAllServiceByEventId(eventId);
        if (allServiceByEventId != null && allServiceByEventId.size() > 0) {
            return ResponseBuilder.build(ResponseBuilder.success(allServiceByEventId));
        } else {
            Response rs = new Response();
            rs.setOutMsgCode(1);
            rs.setOutMsgDesc("NO Data");
            return ResponseBuilder.build(ResponseBuilder.success(rs));
        }
    }

    @Override
    public ResponseEntity<ApiResponse> getAllService() {
        List<ServiceObj> allService = serviceRepository.getAllService();
        if (allService != null && allService.size() > 0) {
            return ResponseBuilder.build(ResponseBuilder.success(allService));
        } else {
            Response rs = new Response();
            rs.setOutMsgCode(1);
            rs.setOutMsgDesc("NO Data");
            return ResponseBuilder.build(ResponseBuilder.success(rs));
        }
    }
}
