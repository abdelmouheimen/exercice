package org.example.repository;

import org.example.entity.PoliceAssurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoliceAssuranceRepository extends JpaRepository<PoliceAssurance, Long> {

}
