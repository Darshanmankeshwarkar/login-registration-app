package com.logisignup.loginsignupapplication.repository;

import com.logisignup.loginsignupapplication.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}