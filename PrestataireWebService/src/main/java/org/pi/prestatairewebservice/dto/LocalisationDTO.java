package org.pi.prestatairewebservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocalisationDTO {
    private Long id;
    private String ville;
    private String adresse;
    private Double latitude;
    private Double longitude;
}