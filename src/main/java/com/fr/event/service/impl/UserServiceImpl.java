package com.fr.event.service.impl;

import com.fr.event.domain.User;
import com.fr.event.domain.res.Response;
import com.fr.event.repository.UserRepository;
import com.fr.event.service.UserService;
import com.fr.event.util.ApiResponse;
import com.fr.event.util.ResponseBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    final UserRepository userRepository;

    private final PasswordEncoder bcryptEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder bcryptEncoder) {
        this.userRepository = userRepository;
        this.bcryptEncoder = bcryptEncoder;
    }

    @Override
    public ResponseEntity<ApiResponse> createUser(User user) {
        Response rs = new Response();
        if (!StringUtils.isEmpty(user.getPassword())) {
            user.setPassword(bcryptEncoder.encode(user.getPassword()));
        } else {
            rs.setOutMsgCode(1);
            rs.setOutMsgDesc("Password Cannot Be Null");
            return ResponseBuilder.build(ResponseBuilder.badRequest(rs));
        }
        try {
            if (userRepository.createUser(user) > 0) {
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
    public ResponseEntity<ApiResponse> getUserByName(String name) {
        return ResponseBuilder.build(ResponseBuilder.success(userRepository.getUserByName(name)));
    }

    @Override
    public ResponseEntity<ApiResponse> updateUserRole(String name, String role) {
        Response rs = new Response();
        if (userRepository.updateUserRole(name, role) > 0) {
            rs.setOutMsgCode(0);
            rs.setOutMsgDesc("SUCCESS");
            return ResponseBuilder.build(ResponseBuilder.created(rs));
        } else {
            rs.setOutMsgCode(1);
            rs.setOutMsgDesc("FAIL");
            return ResponseBuilder.build(ResponseBuilder.badRequest(rs));
        }
    }
}
