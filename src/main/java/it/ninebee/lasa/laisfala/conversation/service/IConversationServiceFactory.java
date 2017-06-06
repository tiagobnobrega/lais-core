package it.ninebee.lasa.laisfala.conversation.service;

import it.ninebee.lasa.laisfala.connector.ConnectorVO;
import it.ninebee.lasa.laisfala.conversation.ConversationServiceCreationException;

public interface IConversationServiceFactory{

	IConversationService createFrom(ConnectorVO connector) throws ConversationServiceCreationException;
	
	Boolean canResolve(String connectorType);
	
}
