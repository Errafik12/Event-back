package com.fr.event;

import com.fr.event.domain.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EventConfigTest extends ConfigAbstractBaseTest {

    public EventConfigTest() {
    }

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Test
    public void getApiListByKey() {
        String query = "SELECT * FROM user";
        namedParameterJdbcTemplate.query(query, (rs, i) -> userDomainMapper(rs));
        System.out.println("Hi");
    }

    private User userDomainMapper(ResultSet rs) throws SQLException {
        User user = new User();
        return user;
    }

}
