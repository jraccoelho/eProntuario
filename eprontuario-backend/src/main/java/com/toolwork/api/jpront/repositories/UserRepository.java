package com.toolwork.api.jpront.repositories;

import com.toolwork.api.jpront.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

}