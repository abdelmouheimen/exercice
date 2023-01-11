package org.exemple.ports.api;


import java.util.List;

import org.exemple.data.PoliceAssuranceDto;

public interface PoliceAssuranceServicePort {

	PoliceAssuranceDto addPoliceAssurance(PoliceAssuranceDto policeAssuranceDto);

    void deletePoliceAssuranceById(Long id);

    PoliceAssuranceDto updatePoliceAssurance(PoliceAssuranceDto policeAssuranceDto);

    List<PoliceAssuranceDto> getPoliceAssurances();

    PoliceAssuranceDto getPoliceAssuranceById(Long policeAssuranceId);
}
