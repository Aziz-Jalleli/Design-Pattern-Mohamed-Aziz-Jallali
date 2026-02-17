package org.pi.prestatairewebservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@Builder
@Entity
public class Localisation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ville;

    private String adresse;

    private Double latitude;

    private Double longitude;

    public Localisation() {}

    public Localisation(String ville, String adresse, Double latitude, Double longitude) {
        this.ville = ville;
        this.adresse = adresse;
        this.latitude = latitude;
        this.longitude = longitude;
    }

}
