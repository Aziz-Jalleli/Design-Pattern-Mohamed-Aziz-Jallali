package org.pi.prestatairewebservice.repository;

import org.pi.prestatairewebservice.entity.PrestataireService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrestataireServiceRepository extends JpaRepository<PrestataireService, Long> {
    List<PrestataireService> findByPrestataireId(Long prestataireId);
    List<PrestataireService> findByServiceId(Long serviceId);
    List<PrestataireService> findByPrixLessThanEqual(Double maxPrix);
}
