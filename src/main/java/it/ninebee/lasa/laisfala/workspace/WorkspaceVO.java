package it.ninebee.lasa.laisfala.workspace;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WorkspaceVO {

	private String id;
	private WorkspaceTipoEnum tipo;
	private String connectionString;
	private LocalDateTime dtAlteracao;
	
}
