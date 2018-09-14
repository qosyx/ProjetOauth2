package com.github.arocketman.repositories;

import com.github.arocketman.entities.Oauth_client_details;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Oauth_client_details,String>{

}
