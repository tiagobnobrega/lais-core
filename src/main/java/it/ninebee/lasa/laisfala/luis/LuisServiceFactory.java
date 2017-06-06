package it.ninebee.lasa.laisfala.luis;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.ninebee.lasa.laisfala.connector.ConnectorVO;
import it.ninebee.lasa.laisfala.conversation.ConversationServiceCreationException;
import it.ninebee.lasa.laisfala.conversation.service.IConversationService;
import it.ninebee.lasa.laisfala.conversation.service.IConversationServiceFactory;

@Service
public class LuisServiceFactory implements IConversationServiceFactory {

	@Autowired
	ObjectMapper mapper;

	private static String CONNECTOR_TYPE = "WATSON";

	@Override
	public IConversationService createFrom(ConnectorVO connector) throws ConversationServiceCreationException {
		LuisServiceAttributes serviceAttributes = parseAttributes(connector);
		return new LuisService(serviceAttributes);
	}

	private LuisServiceAttributes parseAttributes(ConnectorVO conn) throws ConversationServiceCreationException {
		LuisServiceAttributes servAttr = new LuisServiceAttributes();
		String src = conn.getAttributes();
		try {
			servAttr = mapper.readValue(src, LuisServiceAttributes.class);
		} catch (IOException e) {
			throw new ConversationServiceCreationException("Não foi possível realizar parse dos attributos do connector "
					+ conn.getId() + " : <" + conn.getAttributes() + ">", e);
		}
		
		if(StringUtils.isBlank(servAttr.getAppId())){
			throw new ConversationServiceCreationException("Não foi possivel recuperar o attributo appId do connector"
					+ conn.getId() + " : <" + conn.getAttributes() + ">");
		}
		
		servAttr.setEndpointKey(conn.getCredential().getPassword());
		if(StringUtils.isBlank(servAttr.getEndpointKey())){
			throw new ConversationServiceCreationException("Não foi possivel recuperar a endpoint key. Verifique o password das credenciais do connector: "
					+ conn.getId());
		}
		
		if (servAttr.getSpellCheck() == null) {
			servAttr.setSpellCheck(false);
		}

		return servAttr;
	}

	@Override
	public Boolean canResolve(String connectorType) {
		return CONNECTOR_TYPE.equals(connectorType);
	}

}
