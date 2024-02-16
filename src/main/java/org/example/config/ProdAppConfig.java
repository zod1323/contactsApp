package org.example.config;

import org.example.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application-prod.properties")
@Profile("prod")
public class ProdAppConfig {

    @Bean
    public EnvProgram envProgram() {
        return new EnvProgramImpl();
    }

    @Bean
    public ContactRepository contactRepository(EnvProgram envProgram) {
        return new ContactRepository(envProgram);
    }

    @Bean
    public ContactService contactService(ContactRepository contactRepository) {
        return new ContactService(contactRepository);
    }

    @Bean
    public ContactApp contactApp(ContactService contactService) {
        return new ContactApp(contactService);
    }

}
