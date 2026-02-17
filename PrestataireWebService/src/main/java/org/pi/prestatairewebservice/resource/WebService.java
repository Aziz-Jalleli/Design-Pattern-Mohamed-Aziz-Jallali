package org.pi.prestatairewebservice.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.pi.prestatairewebservice.dto.*;
import org.pi.prestatairewebservice.entity.Prestataire;
import org.pi.prestatairewebservice.repository.PrestataireRepository;
import org.pi.prestatairewebservice.service.PrestataireServices;
import org.pi.prestatairewebservice.util.LocationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Path("service")
public class WebService {
    @Autowired
    private PrestataireServices prestataireService;
    @GET
    @Path("/prestataires/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<PrestataireDTO> getAllPrestataire() {
        return ResponseEntity.ok(prestataireService.getAllPrestataires()).getBody();
    }

    @GET
    @Path("/prestataires")
    public Response searchPrestataires(
            @QueryParam("latitude") Double latitude,
            @QueryParam("longitude") Double longitude,
            @QueryParam("radiusKm") Double radiusKm,
            @QueryParam("minRating") Double minRating,
            @QueryParam("disponible") Boolean disponible,
            @QueryParam("serviceType") String serviceType,
            @QueryParam("ville") String ville
    ) {
        if (radiusKm != null && radiusKm <= 0) {
            throw new IllegalArgumentException("radiusKm must be greater than 0");
        }

        if (minRating != null && (minRating < 0 || minRating > 5)) {
            throw new IllegalArgumentException("minRating must be between 0 and 5");
        }
        if ((latitude != null && longitude == null) || (latitude == null && longitude != null)) {
            throw new IllegalArgumentException("latitude and longitude must be provided together");
        }
        if (radiusKm != null && radiusKm <= 0) {
            throw new IllegalArgumentException("radiusKm must be greater than 0");
        }
        if ((latitude == null || longitude == null) && (ville == null) ) {
            IpApiResponse userLocation = LocationUtils.getUserLocation();
            latitude = userLocation.getLat();
            longitude = userLocation.getLon();
            if (ville == null) {
                ville = userLocation.getCity();
            }
        }
        PrestataireFilterDTO dto = new PrestataireFilterDTO();
        dto.setLatitude(latitude);
        dto.setLongitude(longitude);
        dto.setRadiusKm(radiusKm);
        dto.setMinRating(minRating);
        dto.setDisponible(disponible);
        dto.setServiceType(serviceType);
        dto.setVille(ville);

        return Response.ok(
                prestataireService.FilterPrestataires(dto)
        ).build();
    }


}
