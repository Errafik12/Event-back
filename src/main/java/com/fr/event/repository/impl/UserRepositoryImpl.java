package com.fr.event.repository.impl;

import com.fr.event.domain.User;
import com.fr.event.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepositoryImpl implements UserRepository {

    final
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public UserRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public int createUser(User user) {
        Map<String, Object> param = new HashMap<>();
        String query = "insert into user (name, first_name, date_of_birth, password, role) " +
                "VALUE (:name, :firstName, :dateOfBirth, :password, :role)";
        param.put("name", StringUtils.upperCase(StringUtils.trim(user.getName())));
        param.put("firstName", StringUtils.upperCase(StringUtils.trim(user.getFirstName())));
        param.put("dateOfBirth", user.dateOfBirth);
        param.put("password", user.getPassword());
        param.put("role", StringUtils.upperCase(StringUtils.trim(user.getRole())));
        return namedParameterJdbcTemplate.update(query, param);
    }

    @Override
    public User getUserByName(String name) {
        Map<String, Object> params = new HashMap<>();
        String query = "SELECT * FROM user WHERE name = (:name)";
        params.put("name", StringUtils.upperCase(StringUtils.trim(name)));
        List<User> list = namedParameterJdbcTemplate.query(query, params, (rs, i) -> mapUserDetails(rs));
        return list != null && list.size() != 0 ? list.get(0) : null;
    }

    private User mapUserDetails(ResultSet rs) throws SQLException {
        User user = new User();
        user.setName(rs.getString("name"));
        user.setFirstName(rs.getString("first_name"));
        user.setDateOfBirth(rs.getDate("date_of_birth"));
        user.setRole(rs.getString("role"));
        user.setPassword(rs.getString("password"));
        return user;
    }

    @Override
    public int updateUserRole(String name, String role) {
        Map<String, Object> params = new HashMap<>();
        String query = "UPDATE user SET role = (:role) WHERE name = (:name)";
        params.put("role", StringUtils.upperCase(StringUtils.trim(role)));
        params.put("name", StringUtils.upperCase(StringUtils.trim(name)));
        return namedParameterJdbcTemplate.update(query, params);
    }
}