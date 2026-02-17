package org.pi.prestatairewebservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Data
@AllArgsConstructor
@Builder
@Entity
public class ServiceOffert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String typeService;

    private String description;

    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL)
    @Builder.Default
    private Set<PrestataireService> serviceOfferings = new HashSet<>();

    public ServiceOffert() {}

    public ServiceOffert(String typeService, String description) {
        this.typeService = typeService;
        this.description = description;
    }

}
