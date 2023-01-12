package org.example.controller;

import org.exemple.data.PoliceAssuranceDomain;
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
    public PoliceAssuranceDomain addPoliceAssurance(@RequestBody PoliceAssuranceDomain policeAssuranceDto) {
        return policeAssuranceServicePort.addPoliceAssurance(policeAssuranceDto);
    }

    @PutMapping()
    public PoliceAssuranceDomain updatePoliceAssurance(@RequestBody PoliceAssuranceDomain policeAssuranceDto) {
        return policeAssuranceServicePort.updatePoliceAssurance(policeAssuranceDto);
    }

    @GetMapping("/{id}")
    public PoliceAssuranceDomain getPoliceAssuranceByID(@PathVariable long id) {
        return policeAssuranceServicePort.getPoliceAssuranceById(id);
    }

    @GetMapping("/all")
    public List<PoliceAssuranceDomain> getAllPoliceAssurances() {
        return policeAssuranceServicePort.getPoliceAssurances();
    }

    @DeleteMapping("/{id}")
    public void deletePoliceAssuranceByID(@PathVariable long id) {
        policeAssuranceServicePort.deletePoliceAssuranceById(id);
    }
}
