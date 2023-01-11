package org.example.adapters;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

import org.example.entity.PoliceAssurance;
import org.example.mappers.PoliceAssuranceMapper;
import org.example.repository.PoliceAssuranceRepository;
import org.exemple.data.PoliceAssuranceDto;
import org.exemple.exceptions.ResourceNotFoundException;
import org.exemple.ports.spi.PoliceAssurancePersistencePort;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class PoliceAssuranceJpaAdapter implements PoliceAssurancePersistencePort {

	private PoliceAssuranceRepository PoliceAssuranceRepository;
	
	public PoliceAssuranceJpaAdapter(PoliceAssuranceRepository policeAssuranceRepository) {
		super();
		PoliceAssuranceRepository = policeAssuranceRepository;
	}

	@Override
	public PoliceAssuranceDto addPoliceAssurance(PoliceAssuranceDto PoliceAssuranceDto) {

		PoliceAssurance PoliceAssurance = PoliceAssuranceMapper.INSTANCE
				.policeAssuranceDtoToPoliceAssurance(PoliceAssuranceDto);

		PoliceAssurance PoliceAssuranceSaved = PoliceAssuranceRepository.save(PoliceAssurance);

		return PoliceAssuranceMapper.INSTANCE.policeAssuranceToPoliceAssuranceDto(PoliceAssuranceSaved);
	}

	@Override
	public void deletePoliceAssuranceById(Long id) {
		PoliceAssuranceRepository.deleteById(id);
	}

	@Override
	public PoliceAssuranceDto updatePoliceAssurance(PoliceAssuranceDto PoliceAssuranceDto) {
		PoliceAssurance PoliceAssurance = PoliceAssuranceMapper.INSTANCE
				.policeAssuranceDtoToPoliceAssurance(PoliceAssuranceDto);
		PoliceAssurance PoliceAssuranceUpdated = PoliceAssuranceRepository.save(PoliceAssurance);
		
		return PoliceAssuranceMapper.INSTANCE.policeAssuranceToPoliceAssuranceDto(PoliceAssuranceUpdated);

	}

	@Override
	public List<PoliceAssuranceDto> getPoliceAssurances() {

		List<PoliceAssurance> PoliceAssuranceList = PoliceAssuranceRepository.findAll();

		return PoliceAssuranceMapper.INSTANCE.policeAssuranceListToPoliceAssuranceDtoList(PoliceAssuranceList);
	}

	@Override
	public Optional<PoliceAssuranceDto> getPoliceAssuranceById(Long PoliceAssuranceId) {

		PoliceAssurance PoliceAssurance = PoliceAssuranceRepository.findById(PoliceAssuranceId)
				.orElseThrow(() -> new ResourceNotFoundException(
						MessageFormat.format("Not found regitstry with code {0}", PoliceAssuranceId)));

		return Optional.of(PoliceAssuranceMapper.INSTANCE.policeAssuranceToPoliceAssuranceDto(PoliceAssurance));
	}
}
