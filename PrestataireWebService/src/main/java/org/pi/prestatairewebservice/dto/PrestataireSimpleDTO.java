package org.pi.prestatairewebservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrestataireSimpleDTO {
    private Long id;
    private String nom;
    private String telephone;
    private Double note;
    private Integer experience;
    private Boolean disponible;
    private String ville;
}