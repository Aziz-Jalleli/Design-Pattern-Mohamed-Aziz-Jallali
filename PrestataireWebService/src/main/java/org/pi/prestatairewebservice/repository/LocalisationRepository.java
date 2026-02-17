package org.pi.prestatairewebservice.repository;

import org.pi.prestatairewebservice.entity.Localisation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocalisationRepository extends JpaRepository<Localisation, Long> {
    List<Localisation> findByVille(String ville);
}
