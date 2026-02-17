package org.pi.prestatairewebservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocationSearchDTO {
    private String ville;
    private Double latitude;
    private Double longitude;
    private Double radiusKm;
}
