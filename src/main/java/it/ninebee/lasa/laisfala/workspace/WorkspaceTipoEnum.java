package it.ninebee.lasa.laisfala.workspace;

import java.util.Arrays;

public enum WorkspaceTipoEnum {

	WATSON("WATSON"),
	AWS("AWS"),
	LUIS("LUIS");
	
	private String valor;
	
	WorkspaceTipoEnum(String valor){
		this.valor=valor;
	}
	
	public static WorkspaceTipoEnum from(String v){	
		return Arrays.stream(WorkspaceTipoEnum.values())
	            .filter(e -> e.valor.equals(v))
	            .findFirst()
	            .orElseThrow(() -> new IllegalStateException(String.format("Unsupported type %s.", v)));
	}
}
