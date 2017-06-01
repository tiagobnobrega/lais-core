package it.ninebee.lasa.laisfala.conversation.service;

import it.ninebee.lasa.laisfala.connector.ConnectorTypeEnum;
import it.ninebee.lasa.laisfala.connector.ConnectorVO;

public interface IConversationServiceFactory<T extends ConnectorTypeEnum> {

	IConversationService instanceOf(ConnectorVO connector);
	
}
