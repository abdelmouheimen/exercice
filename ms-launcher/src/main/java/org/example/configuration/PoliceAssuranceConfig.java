package org.example.configuration;

import org.example.Application;
import org.example.adapters.PoliceAssuranceJpaAdapter;
import org.example.repository.PoliceAssuranceRepository;
import org.exemple.ports.api.PoliceAssuranceServicePort;
import org.exemple.ports.spi.PoliceAssurancePersistencePort;
import org.exemple.service.PoliceAssuranceServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = Application.class)
public class PoliceAssuranceConfig {

    @Bean
    public PoliceAssurancePersistencePort bookPersistence(PoliceAssuranceRepository repository ){
        return new PoliceAssuranceJpaAdapter(repository);
    }

    @Bean
    public PoliceAssuranceServicePort bookService(PoliceAssurancePersistencePort port){
        return new PoliceAssuranceServiceImpl(port);
    }
}
