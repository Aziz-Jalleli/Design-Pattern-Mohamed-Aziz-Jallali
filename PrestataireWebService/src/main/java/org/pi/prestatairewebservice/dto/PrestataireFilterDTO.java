package org.pi.prestatairewebservice.dto;

import lombok.*;
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrestataireFilterDTO {

        private Double latitude;
        private Double longitude;
        private Double radiusKm;
        private String ville;
        private Double minRating;
        private Boolean disponible;
        private String serviceType;

}
