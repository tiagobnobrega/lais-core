package it.ninebee.lasa.laisfala.conversation;

import java.util.List;
import java.util.Map;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConversationResponseVO {

	private String id;
	private String contextId;
	private Map<String,Object> context;
	private List<ConversationEntityVO> entities;
	private List<ConversationIntentVO> intents;
	private Map<String,Object> extendendAttributes;
	
}
