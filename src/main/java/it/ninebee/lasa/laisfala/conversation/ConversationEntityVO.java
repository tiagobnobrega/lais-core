package it.ninebee.lasa.laisfala.conversation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class ConversationEntityVO {

	private String entity;
	private Integer[] location;
	private String value;
	
}
