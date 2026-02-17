package org.pi.prestatairewebservice.service;

import lombok.RequiredArgsConstructor;
import org.pi.prestatairewebservice.dto.*;
import org.pi.prestatairewebservice.entity.*;
import org.pi.prestatairewebservice.mapper.PrestataireMapper;
import org.pi.prestatairewebservice.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PrestataireServices {

    private final PrestataireRepository prestataireRepository;
    private final ServiceRepository serviceRepository;
    private final PrestataireServiceRepository prestataireServiceRepository;
    private final LocalisationRepository localisationRepository;
    private final PrestataireMapper mapper;

    public List<PrestataireDTO> getAllPrestataires() {
        return prestataireRepository.findAll().stream()
                .map(mapper::toPrestataireDTO)
                .collect(Collectors.toList());
    }

    public List<PrestataireDTO> FilterPrestataires(PrestataireFilterDTO dto) {

        List<Prestataire> prestataires;

        if (dto.getVille() != null && !dto.getVille().isEmpty()) {
            prestataires = prestataireRepository.findByLocalisation_Ville(dto.getVille());
        }
        else {
            prestataires = prestataireRepository.FilterPrestataires(
                    dto.getLatitude(),
                    dto.getLongitude(),
                    dto.getRadiusKm(),
                    dto.getMinRating(),
                    dto.getDisponible(),
                    dto.getServiceType()
            );
        }

        return prestataires.stream()
                .map(mapper::toPrestataireDTO)
                .collect(Collectors.toList());
    }

    public Double calculateDistance(Double lat1, Double lon1, Double lat2, Double lon2) {
        final int EARTH_RADIUS = 6371;

        Double latDistance = Math.toRadians(lat2 - lat1);
        Double lonDistance = Math.toRadians(lon2 - lon1);

        Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c;
    }
}