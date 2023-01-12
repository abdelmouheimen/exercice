package domain.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.exemple.data.PoliceAssuranceDomain;
import org.exemple.exceptions.ResourceNotFoundException;
import org.exemple.ports.spi.PoliceAssurancePersistencePort;
import org.exemple.service.PoliceAssuranceServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

@ExtendWith(SpringExtension.class)
public class PoliceAssuranceServiceImplTest {
	
	@InjectMocks
	private PoliceAssuranceServiceImpl policeAssuranceService;
	
	@Mock
	private PoliceAssurancePersistencePort policeAssurancePersistencePort;
	
    @BeforeEach
    void setUp() {
        FixtureFactoryLoader.loadTemplates("domain.utils.fixture");
    }
    
    @Test
    @DisplayName("[domain] - should return a list not empty")
    void shouldReturnAListIsNotEmpty() {
        List<PoliceAssuranceDomain> policeAssurances = Fixture.from(PoliceAssuranceDomain.class).gimme(1,"valid");
        when(policeAssurancePersistencePort.getPoliceAssurances()).thenReturn(policeAssurances);
        List<PoliceAssuranceDomain> result = policeAssuranceService.getPoliceAssurances();

        assertThat(result).isNotEmpty();
        assertThat(result.size()).isPositive();
    }
    
    @Test
    @DisplayName("[domain] - should return a policeAssuranceDto with id equals 1")
    void shouldReturnAPoliceAssuranceWithIdEquals1() {
    	PoliceAssuranceDomain policeAssurance = Fixture.from(PoliceAssuranceDomain.class).gimme("valid");
        when(policeAssurancePersistencePort.getPoliceAssuranceById(any())).thenReturn(Optional.of(policeAssurance));

        PoliceAssuranceDomain result = policeAssuranceService.getPoliceAssuranceById(1L);
        assertThat(result.getId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("[domain] - should return a ResourceNotFoundException with message Not found regitstry with code 1")
    void shouldReturnAResourceNotFoundExceptionWithMessageNotFoundRegitstryWithCode1() {

        when(policeAssurancePersistencePort.getPoliceAssuranceById(any())).thenReturn(Optional.empty());

        ResourceNotFoundException result = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
        	policeAssuranceService.getPoliceAssuranceById(1L);
        });

        assertThat(result.getMessage()).isEqualTo("Not found police with code 1");
    }
    
    @Test
    @DisplayName("[domain] - should created one policeAssuranceDto and return policeAssuranceDto with id 1")
    void shouldCreatedOnePoliceAssuranceAndReturnId1() {
    	PoliceAssuranceDomain policeAssurance = Fixture.from(PoliceAssuranceDomain.class).gimme("valid");
        when(policeAssurancePersistencePort.addPoliceAssurance(any())).thenReturn(policeAssurance);
        PoliceAssuranceDomain result = policeAssuranceService.addPoliceAssurance(policeAssurance);
        assertThat(result.getId()).isEqualTo(1L);
    }
    
    @Test
    @DisplayName("[domain] - should update nom of policeAssurance and return policeAssurance with the new nom")
    void shouldUpdateOnePoliceAssuranceAndReturnEmailChange() {
    	PoliceAssuranceDomain policeAssurance = Fixture.from(PoliceAssuranceDomain.class).gimme("valid_update");
        when(policeAssurancePersistencePort.updatePoliceAssurance(any())).thenReturn(policeAssurance);
        PoliceAssuranceDomain result = policeAssuranceService.updatePoliceAssurance(policeAssurance);
        assertThat(result.getNom()).isEqualTo("Abdel2");
    }
    
    @Test
    @DisplayName("[domain] - should delete one policeAssurance with id 1")
    void shouldDeleteOnePoliceAssuranceWithId1() {

    	PoliceAssuranceDomain policeAssurance = Fixture.from(PoliceAssuranceDomain.class).gimme("valid");
        when(policeAssurancePersistencePort.getPoliceAssuranceById(any())).thenReturn(Optional.of(policeAssurance));
        policeAssuranceService.deletePoliceAssuranceById(1L);
        verify(policeAssurancePersistencePort, times(1)).deletePoliceAssuranceById(1L);
    }

}
