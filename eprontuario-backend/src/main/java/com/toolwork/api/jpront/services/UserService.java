package com.toolwork.api.jpront.services;

import com.toolwork.api.jpront.domains.User;
import com.toolwork.api.jpront.dtos.UserRequest;
import com.toolwork.api.jpront.dtos.UserResponse;
import com.toolwork.api.jpront.repositories.UserRepository;
import com.toolwork.api.jpront.configs.ObjectMapperUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDate;
import java.util.Optional;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    Logger logger = LoggerFactory.getLogger(UserService.class);

    public void insertUser(UserRequest userToInsert) {
        Optional<User> usuDB = userRepo.findById(userToInsert.getUserId());

        if (!usuDB.isPresent()) {
            User usuInsert = new User();
            usuInsert.setUserName(userToInsert.getUserId());
            usuInsert.setUserPassword(userToInsert.getUserPassword());
            usuInsert.setFullName(userToInsert.getUserFullName());
            usuInsert.setCreatedBy(userToInsert.getRequestor());
            usuInsert.setCreatedDate(LocalDate.now(Clock.systemUTC()));

            userRepo.save(usuInsert);
        }
        // TODO: Work on the error log for the already existing user.
        // else

    }

    public boolean doLogin(String userId, String pwd) {
        Optional<User> usuToLog = userRepo.findById(userId);
        return (usuToLog.isPresent() && this.comparePassword(usuToLog.get(), pwd));
    }

    public List<UserResponse> getAllUsers() {
        return ObjectMapperUtil.mapAll(userRepo.findAll(), UserResponse.class);
    }

    private boolean comparePassword(User usuToLog, String password) {
        return usuToLog.getUserPassword().equals(password);
    }

}