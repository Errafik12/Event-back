package com.fr.event.repository.impl;

import com.fr.event.domain.ServiceObj;
import com.fr.event.repository.ServiceRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ServiceRepositoryImpl implements ServiceRepository {

    final
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ServiceRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public int createService(ServiceObj serviceObj) {
        Map<String, Object> param = new HashMap<>();
        String query = "insert into service (title, eventId, description, percentage) " +
                "VALUE (:title, :eventId, :description, :percentage)";
        param.put("title", StringUtils.upperCase(StringUtils.trim(serviceObj.getTitle())));
        param.put("eventId", serviceObj.getEventId());
        param.put("description", StringUtils.upperCase(StringUtils.trim(serviceObj.getDescription())));
        param.put("percentage", serviceObj.getPercentage());
        return namedParameterJdbcTemplate.update(query, param);
    }

    @Override
    public int updateService(String title, String percentage) {
        Map<String, Object> params = new HashMap<>();
        String query = "UPDATE service SET percentage = (:percentage) WHERE title = (:title)";
        params.put("title", StringUtils.upperCase(StringUtils.trim(title)));
        params.put("percentage", percentage);
        return namedParameterJdbcTemplate.update(query, params);
    }

    @Override
    public List<ServiceObj> getAllServiceByEventId(Integer eventId) {
        Map<String, Object> params = new HashMap<>();
        String query = "SELECT * FROM service WHERE eventId = (:eventId)";
        params.put("eventId", eventId);
        List<ServiceObj> list = namedParameterJdbcTemplate.query(query, params, (rs, i) -> mapEventDetails(rs));
        return list != null && list.size() != 0 ? list : null;
    }

    @Override
    public List<ServiceObj> getAllService() {
        String query = "SELECT * FROM service ORDER BY eventId";
        List<ServiceObj> list = namedParameterJdbcTemplate.query(query, (rs, i) -> mapEventDetails(rs));
        return list != null && list.size() != 0 ? list : null;
    }

    private ServiceObj mapEventDetails(ResultSet rs) throws SQLException {
        ServiceObj serviceObj = new ServiceObj();
        serviceObj.setTitle(rs.getString("title"));
        serviceObj.setEventId(rs.getInt("eventId"));
        serviceObj.setDescription(rs.getString("description"));
        serviceObj.setPercentage(rs.getInt("percentage"));
        return serviceObj;
    }
}
