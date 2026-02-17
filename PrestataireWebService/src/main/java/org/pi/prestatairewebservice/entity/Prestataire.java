package org.pi.prestatairewebservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Data
@AllArgsConstructor
@Builder
@Entity
public class Prestataire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private String telephone;

    private Double note;

    private Integer experience;

    private Boolean disponible;

    @OneToMany(mappedBy = "prestataire", cascade = CascadeType.ALL)
    @Builder.Default
    private Set<PrestataireService> serviceOfferings = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "localisation_id")
    private Localisation localisation;

    public Prestataire() {}

    public Prestataire(String nom, String telephone, Double note, Integer experience, Boolean disponible) {
        this.nom = nom;
        this.telephone = telephone;
        this.note = note;
        this.experience = experience;
        this.disponible = disponible;
    }

}
