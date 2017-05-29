package it.ninebee.lasa.laisfala;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Controller
public class SpringfoxConfig {

	
	@RequestMapping(value="/doc/api")
	public String redirect(){
		return "redirect:/swagger-ui.html";
	} 
	
	
	@Bean
	  public Docket petApi() {
		return new Docket(DocumentationType.SWAGGER_2)
		        .select()
		          .apis(RequestHandlerSelectors.any())
		          .paths(PathSelectors.regex("/api/.*"))
		          .build()
		          .protocols(Stream.of("http", "https").collect(Collectors.toSet()))
		          
//		          Ajustar quando schema de seguran�a for definido
//		          .securitySchemes(newArrayList(apiKey()))
		          
		          //Definir parametro global para chamadas REST
//		          .globalOperationParameters(
//		                  newArrayList(new ParameterBuilder()
//		                      .name("someGlobalParameter")
//		                      .description("Description of someGlobalParameter")
//		                      .modelRef(new ModelRef("string"))
//		                      .parameterType("query")
//		                      .required(true)
//		                      .build()))
		          ;
	}
	
	@Bean
	  UiConfiguration uiConfig() {
	    return new UiConfiguration(
	        null,
	        "none",       
	        "alpha",      
	        "model",     
	        UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS,
	        true,
	        true,     
	        60000L);
	  }

}
