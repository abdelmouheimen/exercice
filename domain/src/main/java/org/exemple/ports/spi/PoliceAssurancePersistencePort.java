package org.exemple.ports.spi;


import java.util.List;
import java.util.Optional;

import org.exemple.data.PoliceAssuranceDomain;

/**
 * gathers all the interfaces required by the domain to retrieve information from third parties.<br/>
 * These interfaces are defined in the hexagon and implemented by the right side of the infrastructure.
 * @author Abdelmouheimen TRABELSSI
 *
 */
public interface PoliceAssurancePersistencePort {

	PoliceAssuranceDomain addPoliceAssurance(PoliceAssuranceDomain policeAssuranceDto);

    void deletePoliceAssuranceById(Long id);

    PoliceAssuranceDomain updatePoliceAssurance(PoliceAssuranceDomain policeAssuranceDto);

    List<PoliceAssuranceDomain> getPoliceAssurances();

    Optional<PoliceAssuranceDomain> getPoliceAssuranceById(Long Id);

}
