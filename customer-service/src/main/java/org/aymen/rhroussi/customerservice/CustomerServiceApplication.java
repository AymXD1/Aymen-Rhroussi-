package org.aymen.rhroussi.customerservice;

import org.aymen.rhroussi.customerservice.entities.Customer;
import org.aymen.rhroussi.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository){
        return args -> {
            customerRepository.save(Customer.builder()
                            .name("Aymen")
                            .email("aymen@gmail.com")
                    .build());

            customerRepository.save(Customer.builder()
                            .name("Aymen2")
                            .email("aymen2@gmail.com")
                    .build());

            customerRepository.save(Customer.builder()
                            .name("Aymen3")
                            .email("aymen3@gmail.com")
                    .build());

            customerRepository.findAll().forEach(c->{
                System.out.println("===========================");
                System.out.println(c.getId());
                System.out.println(c.getName());
                System.out.println(c.getEmail());
                System.out.println("===========================");
            });
        };
    }

}
