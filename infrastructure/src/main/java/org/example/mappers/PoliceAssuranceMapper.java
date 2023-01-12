package org.example.mappers;

import java.util.List;

import org.example.entity.PoliceAssurance;
import org.exemple.data.PoliceAssuranceDomain;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * interface that uses mapStruct to autogenerate mapping between domain object and entity
 * @author Abdelmouheimen TRABELSSI
 *
 */
@Mapper
public interface PoliceAssuranceMapper {

    PoliceAssuranceMapper INSTANCE = Mappers.getMapper(PoliceAssuranceMapper.class);

    PoliceAssuranceDomain policeAssuranceToPoliceAssuranceDto(PoliceAssurance policeAssurance);
    
    PoliceAssurance policeAssuranceDtoToPoliceAssurance(PoliceAssuranceDomain policeAssuranceDto);

    List<PoliceAssuranceDomain> policeAssuranceListToPoliceAssuranceDtoList(List<PoliceAssurance> policeAssuranceList);

    List<PoliceAssurance> PoliceAssuranceDtoListTopoliceAssuranceList(List<PoliceAssuranceDomain> PoliceAssuranceDtoList);
}
