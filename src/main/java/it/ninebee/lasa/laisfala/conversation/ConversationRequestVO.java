package it.ninebee.lasa.laisfala.conversation;


import java.util.List;
import java.util.Map;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConversationRequestVO {

	private String id;
	private String connectorId;
	private String contextId;
	private String inputText;
	private Map<String,Object> context;
	private List<ConversationEntityVO> entities;
	private List<ConversationIntentVO> intents;
}
