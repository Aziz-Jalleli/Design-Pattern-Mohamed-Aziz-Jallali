package org.pi.prestatairewebservice.dto;

import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrestataireDTO {
    private Long id;
    private String nom;
    private String telephone;
    private Double note;
    private Integer experience;
    private Boolean disponible;
    private LocalisationDTO localisation;
    private List<PrestataireServiceDTO> services;
}