package org.example.mappers;

import java.util.List;

import org.example.entity.PoliceAssurance;
import org.exemple.data.PoliceAssuranceDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PoliceAssuranceMapper {

    PoliceAssuranceMapper INSTANCE = Mappers.getMapper(PoliceAssuranceMapper.class);

    PoliceAssuranceDto policeAssuranceToPoliceAssuranceDto(PoliceAssurance policeAssurance);
    
    PoliceAssurance policeAssuranceDtoToPoliceAssurance(PoliceAssuranceDto policeAssuranceDto);

    List<PoliceAssuranceDto> policeAssuranceListToPoliceAssuranceDtoList(List<PoliceAssurance> policeAssuranceList);

    List<PoliceAssurance> PoliceAssuranceDtoListTopoliceAssuranceList(List<PoliceAssuranceDto> PoliceAssuranceDtoList);
}
