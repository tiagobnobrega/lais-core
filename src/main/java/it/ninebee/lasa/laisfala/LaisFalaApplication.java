package it.ninebee.lasa.laisfala;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class LaisFalaApplication {

	public static void main(String[] args) {
		SpringApplication.run(LaisFalaApplication.class, args);
	}
}
