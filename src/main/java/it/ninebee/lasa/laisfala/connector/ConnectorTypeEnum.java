package it.ninebee.lasa.laisfala.connector;

import java.util.Arrays;

public enum ConnectorTypeEnum {

	WATSON("WATSON"),
	AWS("AWS"),
	LUIS("LUIS");
	
	private String value;
	
	ConnectorTypeEnum(String value){
		this.value=value;
	}
	
	public static ConnectorTypeEnum from(String v){	
		return Arrays.stream(ConnectorTypeEnum.values())
	            .filter(e -> e.value.equals(v))
	            .findFirst()
	            .orElseThrow(() -> new IllegalStateException(String.format("Unsupported type %s.", v)));
	}
	
	public String getValue(){
		return this.value;
	}
}
