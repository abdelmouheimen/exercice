package org.exemple.service;

import org.exemple.data.PoliceAssuranceDomain;
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
    public PoliceAssuranceDomain addPoliceAssurance(PoliceAssuranceDomain policeAssuranceDto) {
        return policeAssurancePersistencePort.addPoliceAssurance(policeAssuranceDto);
    }

    @Override
    public void deletePoliceAssuranceById(Long id) {
        policeAssurancePersistencePort.deletePoliceAssuranceById(id);
    }

    @Override
    public PoliceAssuranceDomain updatePoliceAssurance(PoliceAssuranceDomain policeAssuranceDto) {
        return policeAssurancePersistencePort.updatePoliceAssurance(policeAssuranceDto);
    }

    @Override
    public List<PoliceAssuranceDomain> getPoliceAssurances() {
        return policeAssurancePersistencePort.getPoliceAssurances();
    }

    @Override
    /**
     * get PoliceAssurance by id if exists else throw ResourceNotFoundException 
     * @param  id of police assurance
     * @return policeAssurance
     * @throws InterruptedException

     */
    public PoliceAssuranceDomain getPoliceAssuranceById(Long id) {
        return policeAssurancePersistencePort.getPoliceAssuranceById(id)
        		.orElseThrow(() -> new ResourceNotFoundException(format("Not found police with code {0}", id)));
    }
}
