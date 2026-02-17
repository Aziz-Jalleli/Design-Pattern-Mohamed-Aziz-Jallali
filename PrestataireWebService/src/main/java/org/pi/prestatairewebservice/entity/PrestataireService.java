package org.pi.prestatairewebservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "prestataire_service")
public class PrestataireService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "prestataire_id")
    private Prestataire prestataire;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private ServiceOffert service;

    private Double prix;
    private String notes;
}
