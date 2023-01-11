package application;

import org.example.Application;
import org.example.controller.PoliceAssuranceController;
import org.exemple.data.PoliceAssuranceDto;
import org.exemple.ports.api.PoliceAssuranceServicePort;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.Matchers.containsString;



@WebMvcTest(PoliceAssuranceController.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Application.class)
public class PoliceAssuranceControllerTest {
	

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PoliceAssuranceServicePort policeAssuranceServicePort;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        FixtureFactoryLoader.loadTemplates("application.utils.fixture");
    }
    
    
    @Test
    @DisplayName("[application] - should created one policeAssurance")
    void shouldCreatedOnePoliceAssurance() throws Exception {

        PoliceAssuranceDto policeAssurance = Fixture.from(PoliceAssuranceDto.class).gimme("created");

        String jsonBody = objectMapper.writeValueAsString(policeAssurance);

        when(policeAssuranceServicePort.addPoliceAssurance(any())).thenReturn(policeAssurance);

        ResultActions result =
                mockMvc.perform(post("/police-assurance")
                        .content(jsonBody).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));

        result.andDo(MockMvcResultHandlers.print());
        result.andExpect(status().is2xxSuccessful());
    }
    
    @Test
    @DisplayName("[application] - should return a policeAssurance with id equals 1")
    void shouldReturnAPoliceAssuranceWithIdEquals1() throws Exception {

        PoliceAssuranceDto policeAssurance = Fixture.from(PoliceAssuranceDto.class).gimme("created");
        when(policeAssuranceServicePort.getPoliceAssuranceById(any())).thenReturn(policeAssurance);

        ResultActions result =
                mockMvc.perform(get("/police-assurance/{id}", 1L)
                        .accept(MediaType.APPLICATION_JSON));

        result.andDo(MockMvcResultHandlers.print());
        result.andExpect(status().is2xxSuccessful());
    }
    
    
    @Test
    @DisplayName("[application] - should return a list is not empty")
    void shouldReturnAListIsNotEmpty() throws Exception {

        List<PoliceAssuranceDto> policeAssurances = Fixture.from(PoliceAssuranceDto.class).gimme(1,"created");
        when(policeAssuranceServicePort.getPoliceAssurances()).thenReturn(policeAssurances);

        ResultActions result =
                mockMvc.perform(get("/police-assurance/all")
                        .accept(MediaType.APPLICATION_JSON));

        result.andDo(MockMvcResultHandlers.print());
        result.andExpect(status().isOk());
        result.andExpect(content().string(containsString("Abdel")));
    }
    
    
    @Test
    @DisplayName("[application] - should update one policeAssurance")
    void shouldUpdateOnePoliceAssurance() throws Exception {

        PoliceAssuranceDto policeAssurance = Fixture.from(PoliceAssuranceDto.class).gimme("created");

        String jsonBody = objectMapper.writeValueAsString(policeAssurance);

        when(policeAssuranceServicePort.getPoliceAssuranceById(any())).thenReturn(policeAssurance);

        when(policeAssuranceServicePort.updatePoliceAssurance(any())).thenReturn(policeAssurance);

        ResultActions result =
                mockMvc.perform(put("/police-assurance")
                        .content(jsonBody).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));

        result.andDo(MockMvcResultHandlers.print());
        result.andExpect(status().isOk());
    }


    @Test
    @DisplayName("[application] - should delete one policeAssurance with id 1")
    void shouldDeleteOnePoliceAssuranceDtoWithId1() throws Exception {

        PoliceAssuranceDto policeAssurance = Fixture.from(PoliceAssuranceDto.class).gimme("created");
        when(policeAssuranceServicePort.getPoliceAssuranceById(any())).thenReturn(policeAssurance);
        doNothing().when(policeAssuranceServicePort).deletePoliceAssuranceById(any());

        ResultActions result =
                mockMvc.perform(delete("/police-assurance/{id}", 1L)
                        .accept(MediaType.APPLICATION_JSON));

        result.andDo(MockMvcResultHandlers.print());
        result.andExpect(status().is2xxSuccessful());
        verify(policeAssuranceServicePort, times(1)).deletePoliceAssuranceById(1L);
    }

}
