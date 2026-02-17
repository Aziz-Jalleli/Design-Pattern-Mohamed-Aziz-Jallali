package org.pi.prestatairewebservice.mapper;

import org.pi.prestatairewebservice.dto.*;
import org.pi.prestatairewebservice.entity.*;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

@Component
public class PrestataireMapper {

    public LocalisationDTO toLocalisationDTO(Localisation localisation) {
        if (localisation == null) return null;
        return LocalisationDTO.builder()
                .id(localisation.getId())
                .ville(localisation.getVille())
                .adresse(localisation.getAdresse())
                .latitude(localisation.getLatitude())
                .longitude(localisation.getLongitude())
                .build();
    }

    public ServiceDTO toServiceDTO(ServiceOffert service) {
        if (service == null) return null;
        return ServiceDTO.builder()
                .id(service.getId())
                .typeService(service.getTypeService())
                .description(service.getDescription())
                .nombrePrestataires(service.getServiceOfferings() != null ?
                        service.getServiceOfferings().size() : 0)
                .build();
    }

    public PrestataireServiceDTO toPrestataireServiceDTO(PrestataireService ps) {
        if (ps == null) return null;
        return PrestataireServiceDTO.builder()
                .id(ps.getId())
                .prestataireId(ps.getPrestataire() != null ? ps.getPrestataire().getId() : null)
                .prestataireName(ps.getPrestataire() != null ? ps.getPrestataire().getNom() : null)
                .serviceId(ps.getService() != null ? ps.getService().getId() : null)
                .serviceType(ps.getService() != null ? ps.getService().getTypeService() : null)
                .prix(ps.getPrix())
                .notes(ps.getNotes())
                .build();
    }

    public PrestataireDTO toPrestataireDTO(Prestataire prestataire) {
        if (prestataire == null) return null;
        return PrestataireDTO.builder()
                .id(prestataire.getId())
                .nom(prestataire.getNom())
                .telephone(prestataire.getTelephone())
                .note(prestataire.getNote())
                .experience(prestataire.getExperience())
                .disponible(prestataire.getDisponible())
                .localisation(toLocalisationDTO(prestataire.getLocalisation()))
                .services(prestataire.getServiceOfferings() != null ?
                        prestataire.getServiceOfferings().stream()
                                .map(this::toPrestataireServiceDTO)
                                .collect(Collectors.toList()) : null)
                .build();
    }

    public PrestataireSimpleDTO toPrestataireSimpleDTO(Prestataire prestataire) {
        if (prestataire == null) return null;
        return PrestataireSimpleDTO.builder()
                .id(prestataire.getId())
                .nom(prestataire.getNom())
                .telephone(prestataire.getTelephone())
                .note(prestataire.getNote())
                .experience(prestataire.getExperience())
                .disponible(prestataire.getDisponible())
                .ville(prestataire.getLocalisation() != null ?
                        prestataire.getLocalisation().getVille() : null)
                .build();
    }
}