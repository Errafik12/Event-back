package com.fr.event.repository.impl;

import com.fr.event.domain.AddressObj;
import com.fr.event.domain.Event;
import com.fr.event.domain.OrgObj;
import com.fr.event.repository.EventRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class EventRepositoryImpl implements EventRepository {

    final
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public EventRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    @Transactional
    public void createEvent(Event event) {
        Map<String, Object> param = new HashMap<>();
        String query = "insert into event (eventId, name, status) VALUE (:eventId, :name, :status)";
        param.put("eventId", event.getEventId());
        param.put("name", StringUtils.upperCase(StringUtils.trim(event.getName())));
        param.put("status", StringUtils.upperCase(StringUtils.trim(event.getStatus())));
        namedParameterJdbcTemplate.update(query, param);

        Map<String, Object> paramOrg = new HashMap<>();
        String queryOrg = "insert into organisation (eventId, date, capacity, outdoors, age_limit, property) VALUE (:eventId, :date, :capacity, :outdoors, :age_limit, :property)";
        paramOrg.put("eventId", event.getEventId());
        paramOrg.put("date", event.getOrgObj().getDate());
        paramOrg.put("capacity", event.getOrgObj().getCapacity());
        paramOrg.put("outdoors", StringUtils.upperCase(StringUtils.trim(event.getOrgObj().getOutdoors())));
        paramOrg.put("age_limit", event.getOrgObj().getAge_limit());
        paramOrg.put("property", StringUtils.upperCase(StringUtils.trim(event.getOrgObj().getProperty())));
        namedParameterJdbcTemplate.update(queryOrg, paramOrg);

        Map<String, Object> paramAdd = new HashMap<>();
        String queryAdd = "insert into address (eventId, number, street, postal_code, city, country) VALUE (:eventId, :number, :street, :postal_code, :city, :country)";
        paramAdd.put("eventId", event.getEventId());
        paramAdd.put("number", event.getAddressObj().getNumber());
        paramAdd.put("street", StringUtils.upperCase(StringUtils.trim(event.getAddressObj().getStreet())));
        paramAdd.put("postal_code", StringUtils.upperCase(StringUtils.trim(event.getAddressObj().getPostal_code())));
        paramAdd.put("city", StringUtils.upperCase(StringUtils.trim(event.getAddressObj().getCity())));
        paramAdd.put("country", StringUtils.upperCase(StringUtils.trim(event.getAddressObj().getCountry())));
        namedParameterJdbcTemplate.update(queryAdd, paramAdd);
    }

    @Override
    public Event getEventById(Integer eventId) {
        Map<String, Object> params = new HashMap<>();
        String query = "SELECT * FROM EVENT e, ORGANISATION o, ADDRESS a WHERE e.eventId = o.eventId AND e.eventId = a.eventId AND e.eventId = (:eventId)";
        params.put("eventId", eventId);
        List<Event> list = namedParameterJdbcTemplate.query(query, params, (rs, i) -> mapEventDetails(rs));
        return list != null && list.size() != 0 ? list.get(0) : null;
    }

    @Override
    public List<Event> getAllEvent() {
        String query = "SELECT * FROM EVENT e, ORGANISATION o, ADDRESS a WHERE e.eventId = o.eventId AND e.eventId = a.eventId";
        List<Event> list = namedParameterJdbcTemplate.query(query, (rs, i) -> mapEventDetails(rs));
        return list != null && list.size() != 0 ? list : null;
    }

    private Event mapEventDetails(ResultSet rs) throws SQLException {
        Event event = new Event();
        OrgObj orgObj = new OrgObj();
        AddressObj addressObj = new AddressObj();

        event.setEventId(rs.getInt("eventId"));
        event.setName(rs.getString("name"));
        event.setStatus(rs.getString("status"));

        orgObj.setDate(rs.getDate("date"));
        orgObj.setCapacity(rs.getInt("capacity"));
        orgObj.setOutdoors(rs.getString("outdoors"));
        orgObj.setAge_limit(rs.getInt("age_limit"));
        orgObj.setProperty(rs.getString("property"));
        event.setOrgObj(orgObj);

        addressObj.setNumber(rs.getInt("number"));
        addressObj.setStreet(rs.getString("street"));
        addressObj.setPostal_code(rs.getString("postal_code"));
        addressObj.setCity(rs.getString("city"));
        addressObj.setCountry(rs.getString("country"));
        event.setAddressObj(addressObj);

        return event;
    }

    @Override
    @Transactional
    public void updateEvent(Event event) {
        Map<String, Object> params = new HashMap<>();
        String query = "UPDATE event SET name = (:name), status = (:status) WHERE eventId = (:eventId)";
        params.put("name", StringUtils.upperCase(StringUtils.trim(event.getName())));
        params.put("status", StringUtils.upperCase(StringUtils.trim(event.getStatus())));
        params.put("eventId", event.getEventId());
        namedParameterJdbcTemplate.update(query, params);

        Map<String, Object> updateOrg = new HashMap<>();
        String queryOrg = "UPDATE organisation SET date = (:date), capacity = (:capacity), outdoors = (:outdoors), age_limit = (:age_limit), property = (:property) WHERE eventId = (:eventId)";
        updateOrg.put("eventId", event.getEventId());
        updateOrg.put("date", event.getOrgObj().getDate());
        updateOrg.put("capacity", event.getOrgObj().getCapacity());
        updateOrg.put("outdoors", StringUtils.upperCase(StringUtils.trim(event.getOrgObj().getOutdoors())));
        updateOrg.put("age_limit", event.getOrgObj().getAge_limit());
        updateOrg.put("property", StringUtils.upperCase(StringUtils.trim(event.getOrgObj().getProperty())));
        namedParameterJdbcTemplate.update(queryOrg, updateOrg);

        Map<String, Object> updateAdd = new HashMap<>();
        String queryAdd = "UPDATE address SET number = (:number), street = (:street), postal_code = (:postal_code), city = (:city), country = (:country) WHERE eventId = (:eventId)";
        updateAdd.put("eventId", event.getEventId());
        updateAdd.put("number", event.getAddressObj().getNumber());
        updateAdd.put("street", StringUtils.upperCase(StringUtils.trim(event.getAddressObj().getStreet())));
        updateAdd.put("postal_code", StringUtils.upperCase(StringUtils.trim(event.getAddressObj().getPostal_code())));
        updateAdd.put("city", StringUtils.upperCase(StringUtils.trim(event.getAddressObj().getCity())));
        updateAdd.put("country", StringUtils.upperCase(StringUtils.trim(event.getAddressObj().getCountry())));
        namedParameterJdbcTemplate.update(queryAdd, updateAdd);
    }
}
