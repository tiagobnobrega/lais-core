package it.ninebee.lasa.laisfala.connector;

import java.time.LocalDateTime;

import it.ninebee.lasa.laisfala.servicecredentials.ServiceCredentialVO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConnectorVO {
	
	private String id;
	private ServiceCredentialVO credential;
	private ConnectorTypeEnum type;
	private String attributes;
	private LocalDateTime dhAlteracao;
	
}
