package uk.co.bbc.pcs.amp.monitoring.application

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan


@SpringBootApplication()
@ComponentScan(basePackages = ["uk.co.bbc.pcs.amp.monitoring.controllers", 
        "uk.co.bbc.pcs.amp.monitoring.service",
        "uk.co.bbc.pcs.amp.monitoring.config"])
class Application {

    static void main(String[] args) {
        SpringApplication.run(Application.class, args)
    }
}