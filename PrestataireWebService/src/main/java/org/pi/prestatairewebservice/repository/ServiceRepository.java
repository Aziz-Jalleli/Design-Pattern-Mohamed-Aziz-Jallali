package org.pi.prestatairewebservice.repository;

import org.pi.prestatairewebservice.entity.ServiceOffert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServiceRepository extends JpaRepository<ServiceOffert, Long> {
    Optional<ServiceOffert> findByTypeService(String s);
}
