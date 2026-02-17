package org.pi.prestatairewebservice.dto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrestataireServiceDTO {
    private Long id;
    private Long prestataireId;
    private String prestataireName;
    private Long serviceId;
    private String serviceType;
    private Double prix;
    private String notes;
}