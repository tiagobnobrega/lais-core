package it.ninebee.lasa.laisfala;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.Cloud;
import org.springframework.cloud.CloudFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
public class DataSourceConfiguration {

	@Bean
	public Cloud cloud() {
		return new CloudFactory().getCloud();
	}

	@Bean
	@Profile("cloud")
	@Qualifier("laisDs")
	public DataSource cloudLaisDatasource() {
		return cloud().getSingletonServiceConnector(DataSource.class, null);
	}

	@Bean
	@Primary
	@Profile("default") //Não deve ser instanciado quando em cloud
	@Qualifier("laisDs")
	public DataSource localLaisDatasource() {
		return laisDataSourceProperties().initializeDataSourceBuilder().build();
	}

	@Bean
	@Primary
	@Profile("default")
	@Qualifier("laisDs")
	@ConfigurationProperties("laisfala.datasource.laisfala")
	public DataSourceProperties laisDataSourceProperties() {
	    return new DataSourceProperties();
	}
	
	
}
