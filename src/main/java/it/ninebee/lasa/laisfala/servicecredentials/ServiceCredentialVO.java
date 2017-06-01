package it.ninebee.lasa.laisfala.servicecredentials;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ServiceCredentialVO {

	private String id;
	private String username;
	private String password;
	private LocalDateTime alteracao;
	
}
