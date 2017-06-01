package it.ninebee.lasa.laisfala.conversation;

import com.sun.javafx.collections.MappingChange.Map;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConversationRequestVO {

	private String id;
	private String contextId;
	private Map<String,Object> context;
//	private 
	
}
