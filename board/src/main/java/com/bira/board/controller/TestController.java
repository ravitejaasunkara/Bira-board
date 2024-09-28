package com.bira.board.controller;

import com.bira.board.entity.Organisation;
import com.bira.board.entity.User;
import com.bira.board.repositories.OrganisationRepository;
import com.bira.board.repositories.UserRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;

import java.util.Optional;

@RestController
@Component
@RequestMapping("test")
public class TestController {

    @Autowired
    private OrganisationRepository organisationRepository;

    @Autowired
    private UserRepository userRepository;

    private Logger logger = LoggerFactory.getLogger(TestController.class);
    @GetMapping("/organisation")
    public ResponseEntity<Organisation> testOrganisation() throws Exception {
        Organisation organisation = new Organisation();
        organisation.setOrganisationName("Google");
        try {
            organisationRepository.save(organisation);
            logger.info("organisation:{}",organisation);
            logger.info("organisation saved to database");
        } catch (Exception e) {
            logger.error("Error saving to database",e.getMessage());
            logger.info("exception:{}",e.getMessage());
            throw new Exception(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(organisation);
    }

    @GetMapping("/user")
    public ResponseEntity<User> testUser() {
        User user = new User();
        user.setName("puja ravi");
        user.setEmail("pujaRavi@gmail.com");
        Optional<Organisation> organisation = organisationRepository.findById(1);
        user.setOrganisation(organisation.get());
        try {
            userRepository.save(user);
            logger.info("User saved to database:{}",user);
        } catch (Exception e) {
            logger.error("Error occurred while saving:{}",e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
