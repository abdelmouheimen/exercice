package org.example.controller;

import org.exemple.data.PoliceAssuranceDto;
import org.exemple.ports.api.PoliceAssuranceServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/police-assurance")
public class PoliceAssuranceController {

    @Autowired
    private PoliceAssuranceServicePort policeAssuranceServicePort;

    @PostMapping()
    public PoliceAssuranceDto addPoliceAssurance(@RequestBody PoliceAssuranceDto policeAssuranceDto) {
        return policeAssuranceServicePort.addPoliceAssurance(policeAssuranceDto);
    }

    @PutMapping()
    public PoliceAssuranceDto updatePoliceAssurance(@RequestBody PoliceAssuranceDto policeAssuranceDto) {
        return policeAssuranceServicePort.updatePoliceAssurance(policeAssuranceDto);
    }

    @GetMapping("/{id}")
    public PoliceAssuranceDto getPoliceAssuranceByID(@PathVariable long id) {
        return policeAssuranceServicePort.getPoliceAssuranceById(id);
    }

    @GetMapping("/all")
    public List<PoliceAssuranceDto> getAllPoliceAssurances() {
        return policeAssuranceServicePort.getPoliceAssurances();
    }

    @DeleteMapping("/{id}")
    public void deletePoliceAssuranceByID(@PathVariable long id) {
        policeAssuranceServicePort.deletePoliceAssuranceById(id);
    }
}
