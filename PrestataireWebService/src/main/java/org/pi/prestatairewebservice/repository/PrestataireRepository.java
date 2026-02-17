package org.pi.prestatairewebservice.repository;

import org.pi.prestatairewebservice.entity.Prestataire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrestataireRepository extends JpaRepository<Prestataire, Long> {
    List<Prestataire> findByLocalisation_Ville(String ville);
    @Query(value = """
    SELECT
        p.*
    FROM prestataire p
    WHERE
        (:minRating IS NULL OR p.note >= :minRating)
        AND (:disponible IS NULL OR p.disponible = :disponible)

        AND (
            :serviceType IS NULL
            OR EXISTS (
                SELECT 1
                FROM prestataire_service ps
                JOIN service_offert s ON s.id = ps.service_id
                WHERE ps.prestataire_id = p.id
                  AND s.type_service = :serviceType
            )
        )

        AND (
            :latitude IS NULL OR :longitude IS NULL OR :radiusKm IS NULL
            OR (
                6371 * acos(
                    cos(radians(:latitude)) *
                    cos(radians((
                        SELECT l.latitude FROM localisation l WHERE l.id = p.localisation_id
                    ))) *
                    cos(radians((
                        SELECT l.longitude FROM localisation l WHERE l.id = p.localisation_id
                    )) - radians(:longitude)) +
                    sin(radians(:latitude)) *
                    sin(radians((
                        SELECT l.latitude FROM localisation l WHERE l.id = p.localisation_id
                    )))
                )
            ) <= :radiusKm
        )

    ORDER BY
        CASE
            WHEN :latitude IS NOT NULL AND :longitude IS NOT NULL
            THEN (
                6371 * acos(
                    cos(radians(:latitude)) *
                    cos(radians((
                        SELECT l.latitude FROM localisation l WHERE l.id = p.localisation_id
                    ))) *
                    cos(radians((
                        SELECT l.longitude FROM localisation l WHERE l.id = p.localisation_id
                    )) - radians(:longitude)) +
                    sin(radians(:latitude)) *
                    sin(radians((
                        SELECT l.latitude FROM localisation l WHERE l.id = p.localisation_id
                    )))
                )
            )
        END
    """, nativeQuery = true)
    List<Prestataire> FilterPrestataires(
            @Param("latitude") Double latitude,
            @Param("longitude") Double longitude,
            @Param("radiusKm") Double radiusKm,
            @Param("minRating") Double minRating,
            @Param("disponible") Boolean disponible,
            @Param("serviceType") String serviceType
    );

}
