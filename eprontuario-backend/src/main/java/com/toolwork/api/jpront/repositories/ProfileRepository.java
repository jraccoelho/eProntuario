package com.toolwork.api.jpront.repositories;

import com.toolwork.api.jpront.domains.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

}