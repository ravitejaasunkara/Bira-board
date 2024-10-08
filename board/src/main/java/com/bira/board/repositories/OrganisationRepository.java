package com.bira.board.repositories;

import com.bira.board.entity.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganisationRepository extends JpaRepository<Organisation,Integer> {

    Optional<Organisation> findByOrganisationName(String organisationName);
}
