package it.ninebee.lasa.laisfala.conversation;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConversationEntityVO {

	private String type;
	private Integer[] location;
	private String value;
	
}
