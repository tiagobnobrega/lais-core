package it.ninebee.lasa.laisfala.conversation.service;

import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Component;

import it.ninebee.lasa.laisfala.connector.ConnectorVO;
import it.ninebee.lasa.laisfala.conversation.ConversationRequestVO;
import it.ninebee.lasa.laisfala.conversation.ConversationResponseVO;

@Component
public class UnImplementedConversationFactory implements IConversationServiceFactory {

	@Override
	public Boolean canResolve(String connectorType) {
		return false;
	}

	@Override
	public IConversationService createFrom(ConnectorVO connector) {
		throw new NotImplementedException("No factory found for connector: " +connector.getType().getValue());
	}

}
