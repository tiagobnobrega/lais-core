package it.ninebee.lasa.laisfala.luis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import it.ninebee.lasa.laisfala.conversation.ConversationEntityVO;
import it.ninebee.lasa.laisfala.conversation.ConversationIntentVO;
import it.ninebee.lasa.laisfala.conversation.ConversationResponseVO;

public class LuisResponseConverter {

	public static ConversationResponseVO convertToConversationResponse(LuisResponse luisResp){
		
		List<ConversationEntityVO> laisEntities = luisResp.getEntities().stream().map(LuisResponseConverter::toLaisEntity).collect(Collectors.toList());
		List<ConversationIntentVO> laisIntents = luisResp.getIntents().stream().map(LuisResponseConverter::toLaisIntent).collect(Collectors.toList());
		
		ConversationResponseVO resp = ConversationResponseVO.builder()
//				.context(context)
				.entities(laisEntities)
				.intents(laisIntents)
				.id(UUID.randomUUID().toString())
				.extendendAttributes(mapExtendedAttributes(luisResp))
				.build();
		return resp;
	}
	
	public static ConversationEntityVO toLaisEntity(LuisEntity e){
		return new ConversationEntityVO(e.getType(), new Integer[]{e.getStartIndex(),e.getEndIndex()}, e.getEntity());
	};
	
	public static ConversationIntentVO toLaisIntent(LuisIntent i){
		return new ConversationIntentVO(i.getScore(), i.getIntent());
	};
	
	public static Map<String,Object> mapExtendedAttributes(LuisResponse resp){
		Map<String,Object> ext = new HashMap<String,Object>();
		ext.put("query", resp.getQuery());
		ext.put("compositeEntities", resp.getCompositeEntities());
		return ext;
	}
	
}
