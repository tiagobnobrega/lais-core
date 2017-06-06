package it.ninebee.lasa.laisfala.watson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.ninebee.lasa.laisfala.connector.ConnectorVO;
import it.ninebee.lasa.laisfala.conversation.ConversationServiceCreationException;
import it.ninebee.lasa.laisfala.conversation.service.IConversationService;
import it.ninebee.lasa.laisfala.conversation.service.IConversationServiceFactory;

@Service
public class WatsonServiceFactory implements IConversationServiceFactory{

	private static String CONNECTOR_TYPE = "WATSON";
	
	@Autowired
	ObjectMapper mapper;
	
	@Override
	public IConversationService createFrom(ConnectorVO connector) throws ConversationServiceCreationException {
		WatsonService wservice = new WatsonService(this.mapper,connector);
		return wservice;
	}

	@Override
	public Boolean canResolve(String connectorType) {
		return CONNECTOR_TYPE.equals(connectorType);
	}

	
	
}
