package org.exemple.service;

import org.exemple.data.PoliceAssuranceDto;
import org.exemple.exceptions.ResourceNotFoundException;
import org.exemple.ports.api.PoliceAssuranceServicePort;
import org.exemple.ports.spi.PoliceAssurancePersistencePort;
import static java.text.MessageFormat.format;

import java.util.List;

public class PoliceAssuranceServiceImpl implements PoliceAssuranceServicePort {

    private PoliceAssurancePersistencePort policeAssurancePersistencePort;

    public PoliceAssuranceServiceImpl(PoliceAssurancePersistencePort policeAssurancePersistencePort) {
        this.policeAssurancePersistencePort = policeAssurancePersistencePort;
    }

    @Override
    public PoliceAssuranceDto addPoliceAssurance(PoliceAssuranceDto policeAssuranceDto) {
        return policeAssurancePersistencePort.addPoliceAssurance(policeAssuranceDto);
    }

    @Override
    public void deletePoliceAssuranceById(Long id) {
        policeAssurancePersistencePort.deletePoliceAssuranceById(id);
    }

    @Override
    public PoliceAssuranceDto updatePoliceAssurance(PoliceAssuranceDto policeAssuranceDto) {
        return policeAssurancePersistencePort.updatePoliceAssurance(policeAssuranceDto);
    }

    @Override
    public List<PoliceAssuranceDto> getPoliceAssurances() {
        return policeAssurancePersistencePort.getPoliceAssurances();
    }

    @Override
    public PoliceAssuranceDto getPoliceAssuranceById(Long id) {
        return policeAssurancePersistencePort.getPoliceAssuranceById(id)
        		.orElseThrow(() -> new ResourceNotFoundException(format("Not found police with code {0}", id)));
    }
}
