package org.exemple.ports.spi;


import java.util.List;
import java.util.Optional;

import org.exemple.data.PoliceAssuranceDto;

public interface PoliceAssurancePersistencePort {

	PoliceAssuranceDto addPoliceAssurance(PoliceAssuranceDto policeAssuranceDto);

    void deletePoliceAssuranceById(Long id);

    PoliceAssuranceDto updatePoliceAssurance(PoliceAssuranceDto policeAssuranceDto);

    List<PoliceAssuranceDto> getPoliceAssurances();

    Optional<PoliceAssuranceDto> getPoliceAssuranceById(Long Id);

}
