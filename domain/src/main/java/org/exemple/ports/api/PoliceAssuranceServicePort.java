package org.exemple.ports.api;


import java.util.List;

import org.exemple.data.PoliceAssuranceDomain;

/**
 * gathers all the interfaces for everything that needs to query the domain. These interfaces are implemented by the hexagon
 * @author Abdelmouheimen TRABELSSI
 *
 */
public interface PoliceAssuranceServicePort {

	PoliceAssuranceDomain addPoliceAssurance(PoliceAssuranceDomain policeAssuranceDto);

    void deletePoliceAssuranceById(Long id);

    PoliceAssuranceDomain updatePoliceAssurance(PoliceAssuranceDomain policeAssuranceDto);

    List<PoliceAssuranceDomain> getPoliceAssurances();

    PoliceAssuranceDomain getPoliceAssuranceById(Long policeAssuranceId);
}
