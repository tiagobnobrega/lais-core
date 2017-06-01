package it.ninebee.lasa.laisfala.conversation.service;

import it.ninebee.lasa.laisfala.connector.ConnectorTypeEnum;
import it.ninebee.lasa.laisfala.connector.ConnectorVO;
import it.ninebee.lasa.laisfala.conversation.ConversationRequestVO;
import it.ninebee.lasa.laisfala.conversation.ConversationResponseVO;

public interface IConversationService {

	public ConversationResponseVO call(ConversationRequestVO request);
	
}
