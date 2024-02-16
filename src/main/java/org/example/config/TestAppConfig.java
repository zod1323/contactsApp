package org.example.config;

import org.example.*;
import org.springframework.context.annotation.*;

@Configuration
@PropertySources(
        value = {
                @PropertySource("classpath:application-test.properties")
        }
)

@Profile("test")
public class TestAppConfig {

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
