package org.sid;

import java.time.Instant;
import java.util.stream.Stream;

import org.sid.dao.SocieteRepository;
import org.sid.dao.TransactionRepository;
import org.sid.entities.Societe;
import org.sid.entities.Transaction;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WebFluxReactiveApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebFluxReactiveApplication.class, args);
	}

	@Bean
	CommandLineRunner start(SocieteRepository societeRepository, TransactionRepository transactionRepository) {

		return args -> {
			societeRepository.deleteAll().subscribe(null, null, () -> {
				Stream.of("SHALIMAR", "OMEX", "DLF", "GAUR CITY").forEach(s -> {
					societeRepository.save(new Societe(s, s, 100 + Math.random() * 900)).subscribe(soc -> {
						System.out.println(soc.toString());
					});
				});
				transactionRepository.deleteAll().subscribe(null, null, () -> {
					Stream.of("SHALIMAR", "OMEX", "DLF", "GAUR CITY").forEach(s -> {
						societeRepository.findById(s).subscribe(soc -> {
							for (int i = 0; i < 10; i++) {
								Transaction transaction = new Transaction();
								transaction.setInstant(Instant.now());
								transaction.setSociete(soc);
								transaction.setPrice(soc.getPrice()*(1+(Math.random()*12-6)/100));
								transactionRepository.save(transaction).subscribe(t -> {
									System.out.println(t.toString());
								});
							}
						});

					});
				});

			});
			System.out.println("......");

		};
	}

	/**
	 * Swagger setup
	 * 
	 * @return
	 */
	/*
	 * @Bean public Docket productApi() { return new
	 * Docket(DocumentationType.SWAGGER_2).select()
	 * .apis(RequestHandlerSelectors.basePackage("org.sid.web"))
	 * .paths(PathSelectors.regex("/.*")).build().apiInfo(apiEndPointsInfo()); }
	 * 
	 * private ApiInfo apiEndPointsInfo() { return new
	 * ApiInfoBuilder().title("Spring Boot + WebFlux REST API")
	 * .description("Societies REST API") .contact(new
	 * Contact("Praveen Kumar Verma", "", "praveen.verma@hcl.com"))
	 * .license("Apache 2.0")
	 * .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
	 * .version("1.0.0") .build(); }
	 */

}
