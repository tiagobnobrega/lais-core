package it.ninebee.lasa.laisfala.conversation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.ninebee.lasa.laisfala.connector.ConnectorService;
import it.ninebee.lasa.laisfala.connector.ConnectorVO;
import it.ninebee.lasa.laisfala.conversation.service.ConversationServiceManager;
import it.ninebee.lasa.laisfala.conversation.service.IConversationService;

@Service
public class ConversationServiceGateway {

	@Autowired
	ConversationServiceManager serviceManager;
	
	@Autowired
	ConnectorService connService;
	
	public ConversationResponseVO callService(ConversationRequestVO req) throws ConversationServiceCreationException{
		ConnectorVO conn = connService.getById(req.getConnectorId());
		IConversationService service = serviceManager.getServiceFor(conn);
		return service.call(req);
	}
	
}
