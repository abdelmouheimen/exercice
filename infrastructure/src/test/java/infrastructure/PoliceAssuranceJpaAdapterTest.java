package infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.example.Application;
import org.example.adapters.PoliceAssuranceJpaAdapter;
import org.example.repository.PoliceAssuranceRepository;
import org.exemple.data.PoliceAssuranceDto;
import org.exemple.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.assertThrows;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

@Sql(value = "/load-database.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/clean-database.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@ActiveProfiles("test")
@DataJpaTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Application.class)
public class PoliceAssuranceJpaAdapterTest {

    private PoliceAssuranceJpaAdapter adapter;
    @Autowired
    private PoliceAssuranceRepository repository;

    @BeforeEach
    void setUp() {
        adapter = new PoliceAssuranceJpaAdapter(repository);
        FixtureFactoryLoader.loadTemplates("infrastructure.utils.fixture");
    }
    
    @Test
    @DisplayName("[infrastructure] - should return a list not empty")
    void shouldReturnAPageIsNotEmpty() {
        List<PoliceAssuranceDto> result = adapter.getPoliceAssurances();
        assertThat(result).isNotEmpty();
        assertThat(result.size()).isPositive();
    }
    
    @Test
    @DisplayName("[infrastructure] - should return a policeAssurance with id equals 1")
    void shouldReturnAPersonAssuranceDtoWithIdEquals1() {
        Optional<PoliceAssuranceDto> policeAssuranceOptional = adapter.getPoliceAssuranceById(1L);
        PoliceAssuranceDto result = policeAssuranceOptional.get();
        assertThat(result.getId()).isEqualTo(1L);

    }


    @Test
    @DisplayName("[infrastructure] - should created one policeAssurance and return id 1")
    void shouldCreatedOnePersonAssuranceDtoAndReturnId1() {
    	PoliceAssuranceDto policeAssurance = Fixture.from(PoliceAssuranceDto.class).gimme("create");
    	PoliceAssuranceDto result = adapter.addPoliceAssurance(policeAssurance);
        assertThat(result.getId()).isEqualTo(2L);
    }

    @Test
    @DisplayName("[infrastructure] - should update nom of policeAssurance and return policeAssurance with new nom")
    void shouldUpdateOnePersonAssuranceDto() {
    	PoliceAssuranceDto policeAssurance = Fixture.from(PoliceAssuranceDto.class).gimme("update");
    	PoliceAssuranceDto result = adapter.updatePoliceAssurance(policeAssurance);
        assertThat(result.getNom()).isEqualTo("Abdel2");
    }

    @Test
    @DisplayName("[infrastructure] - should delete one policeAssurance with id 1")
    void shouldDeleteOnePersonAssuranceDtoWithId1() {
        adapter.deletePoliceAssuranceById(1L);
        assertThrows(ResourceNotFoundException.class, () -> {
            adapter.getPoliceAssuranceById(1L);
        });
    }

}
