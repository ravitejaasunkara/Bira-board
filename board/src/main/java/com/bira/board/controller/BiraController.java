package com.bira.board.controller;

import com.bira.board.entity.Organisation;
import com.bira.board.entity.User;
import com.bira.board.repositories.OrganisationRepository;
import com.bira.board.repositories.UserRepository;
import com.bira.board.utils.BiraGenericException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/bira")
public class BiraController {

    private static final Logger logger = LoggerFactory.getLogger(BiraController.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrganisationRepository organisationRepository;

    @PostMapping("/save/user")
    public ResponseEntity<String> saveUser(@RequestBody User user) throws BiraGenericException {
        try{
            logger.info("save user request body:{}",user);
            userRepository.save(user);
            logger.info("user has been saved:{}",user);
            return ResponseEntity.status(HttpStatus.OK).body("User has been saved.");
        } catch (Exception e) {
            logger.info("Error has been occurred:{}",e.getMessage());
            throw new BiraGenericException(e.getMessage(),e);
        }
    }

    @PostMapping("/save/org")
    public ResponseEntity<String> saveOrganisation(@RequestBody Organisation org) throws BiraGenericException {
        try {
            logger.info("organisation request body:{}", org);
            // Check if the organisation already exists by name
            Optional<Organisation> existingOrg = organisationRepository.findByOrganisationName(org.getOrganisationName());
            if (existingOrg.isPresent()) {
                throw new BiraGenericException("Organisation with name " + org.getOrganisationName() + " already exists.");
            }
            organisationRepository.save(org);
            logger.info("Organisation has been saved:{}", org);
            return ResponseEntity.status(HttpStatus.OK).body("Organisation saved to database.");
        } catch (Exception e) {
            logger.error("Error saving organisation to database:{}", e.getMessage());
            throw new BiraGenericException(e.getMessage(), e);
        }
    }

}


