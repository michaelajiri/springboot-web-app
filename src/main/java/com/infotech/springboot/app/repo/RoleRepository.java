package com.infotech.springboot.app.repo;

import com.infotech.springboot.app.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
