package launcher;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.example.Application;
import org.example.configuration.PoliceAssuranceConfig;
import org.exemple.data.PoliceAssuranceDomain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;



@ActiveProfiles("test")
@Sql(value = "/load-database.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/clean-database.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@SpringBootTest(classes = {Application.class, PoliceAssuranceConfig.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
@ExtendWith(SpringExtension.class)
public class PoliceAssuranceControllerIntegrationTest {
	

    @LocalServerPort
    Integer port;

    @Autowired
    TestRestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        FixtureFactoryLoader.loadTemplates("launcher.utils.fixture");
    }

    @Test
    @DisplayName("[integration] - should created one policeAssurance")
    void shouldCreatedOnePoliceAssurance() {
        PoliceAssuranceDomain policeAssuranceRequest = Fixture.from(PoliceAssuranceDomain.class).gimme("create");
        ResponseEntity<PoliceAssuranceDomain> response =
                restTemplate.postForEntity("http://localhost:" + port + "/police-assurance", policeAssuranceRequest, PoliceAssuranceDomain.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @DisplayName("[integration] - should return a policeAssurance with id equals 1")
    void shouldReturnApoliceAssuranceWithIdEquals1() {
        ResponseEntity<PoliceAssuranceDomain> response = restTemplate.getForEntity("http://localhost:" + port + "/police-assurance/{id}", PoliceAssuranceDomain.class, 1);
        PoliceAssuranceDomain policeAssuranceResponse = response.getBody();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1L, policeAssuranceResponse.getId());
    }
    
    @Test
    @DisplayName("[integration] - should delete one policeAssurance with id 1")
    void shouldDeleteOnepoliceAssuranceWithId1() {
        ResponseEntity<Void> response = restTemplate.exchange("http://localhost:" + port + "/police-assurance/1", HttpMethod.DELETE, HttpEntity.EMPTY, Void.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    
    @Test
    @DisplayName("[integration] - should update one policeAssurance ")
    void should_update_one_policeAssurance() {
    	PoliceAssuranceDomain policeAssuranceRequest = Fixture.from(PoliceAssuranceDomain.class).gimme("update");
        ResponseEntity<PoliceAssuranceDomain> response = restTemplate.exchange("http://localhost:" + port + "/police-assurance", HttpMethod.PUT, new HttpEntity<>(policeAssuranceRequest), PoliceAssuranceDomain.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
