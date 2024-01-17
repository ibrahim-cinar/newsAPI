package com.cinar.newsAPI.repository;

import com.cinar.newsAPI.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository  extends JpaRepository<Role,String> {
    Role findByRolename(String roleName);
}
