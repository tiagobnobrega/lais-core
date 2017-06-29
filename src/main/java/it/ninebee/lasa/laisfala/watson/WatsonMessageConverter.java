package it.ninebee.lasa.laisfala.watson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import com.ibm.watson.developer_cloud.conversation.v1.model.Entity;
import com.ibm.watson.developer_cloud.conversation.v1.model.Intent;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;

import it.ninebee.lasa.laisfala.conversation.ConversationEntityVO;
import it.ninebee.lasa.laisfala.conversation.ConversationIntentVO;
import it.ninebee.lasa.laisfala.conversation.ConversationRequestVO;
import it.ninebee.lasa.laisfala.conversation.ConversationResponseVO;

public class WatsonMessageConverter {

	public static MessageRequest convertToMessageRequest(ConversationRequestVO request) {
		List<Entity> lstEntities = null;
		if(request.getEntities()!=null){
			lstEntities = request.getEntities().stream().map(WatsonMessageConverter::toWatsonEntity).collect(Collectors.toList());	
		}
		
//		List<Intent> lstIntents = request.getIntents().stream().map(WatsonMessageConverter::toWatsonIntent).collect(Collectors.toList());
			 
		
		MessageRequest req = new MessageRequest.Builder()
				.inputText(request.getInputText())
				.context(request.getContext())
				.entities(lstEntities)
				.alternateIntents(true)
//				.intents(lstIntents)
				.build();
		return req;
	}

	public static ConversationResponseVO convertToConversationResponse(MessageResponse response) {
		List<ConversationEntityVO> lstEntities = response.getEntities().stream().map(WatsonMessageConverter::toLaisEntity).collect(Collectors.toList());
		List<ConversationIntentVO> lstIntents = response.getIntents().stream().map(WatsonMessageConverter::toLaisIntent).collect(Collectors.toList());
		
		ConversationResponseVO resp = ConversationResponseVO.builder()
				.context(response.getContext())
				.entities(lstEntities)
				.id(UUID.randomUUID().toString())
				.intents(lstIntents)
				.extendendAttributes(mapExtendendAttributes(response))
				.build();
		return resp;
	}
	
	private static Map<String,Object> mapExtendendAttributes(MessageResponse response){
		Map<String,Object> ext = new HashMap<String,Object>();
		ext.put("output", response.getOutput());
		return ext;
	}
	
	public static Entity toWatsonEntity(ConversationEntityVO laisEntity) {
		return new Entity(laisEntity.getEntity(), laisEntity.getValue(), laisEntity.getLocation());
	}
	
	public static Intent toWatsonIntent(ConversationIntentVO laisIntent) {
		return new Intent(laisIntent.getIntent(), laisIntent.getConfidence());
	}
	
	public static ConversationEntityVO toLaisEntity(Entity wEntity) {
		return new ConversationEntityVO(wEntity.getEntity(), wEntity.getLocation(), wEntity.getValue());
	}
	
	public static ConversationIntentVO toLaisIntent(Intent wIntent) {
		return new ConversationIntentVO(wIntent.getConfidence(), wIntent.getIntent());
	}
	
}
