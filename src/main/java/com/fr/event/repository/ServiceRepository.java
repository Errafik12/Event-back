package com.fr.event.repository;

import com.fr.event.domain.ServiceObj;

import java.util.List;

public interface ServiceRepository {

    int createService(ServiceObj serviceObj);

    int updateService(String title, String percentage);

    List<ServiceObj> getAllServiceByEventId(Integer eventId);

    List<ServiceObj> getAllService();
}
