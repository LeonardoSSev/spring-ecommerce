package com.leonardossev.ecommerce.repository;

import com.leonardossev.ecommerce.model.entity.UserTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTypeRepository extends JpaRepository<UserTypeEntity, Long> {
}
