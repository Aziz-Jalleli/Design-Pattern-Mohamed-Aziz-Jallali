package org.pi.prestatairewebservice.dto;

import lombok.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceDTO {
    private Long id;
    private String typeService;
    private String description;
    private Integer nombrePrestataires; // Count of providers offering this service
}