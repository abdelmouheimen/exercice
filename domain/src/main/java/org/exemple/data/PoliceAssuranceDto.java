package org.exemple.data;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PoliceAssuranceDto implements Serializable {

    private Long id;

    private String nom;

    private ETypeActivite statut;

    private LocalDateTime dateDebutCouverture;
   
    private LocalDateTime dateFinCouverture;

    private LocalDateTime dateCreation;
    
    private LocalDateTime dateMAJ;

}