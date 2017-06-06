package it.ninebee.lasa.laisfala.watson;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1.model.Entity;
import com.ibm.watson.developer_cloud.conversation.v1.model.Intent;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;

import it.ninebee.lasa.laisfala.connector.ConnectorVO;
import it.ninebee.lasa.laisfala.conversation.ConversationEntityVO;
import it.ninebee.lasa.laisfala.conversation.ConversationIntentVO;
import it.ninebee.lasa.laisfala.conversation.ConversationRequestVO;
import it.ninebee.lasa.laisfala.conversation.ConversationResponseVO;
import it.ninebee.lasa.laisfala.conversation.ConversationServiceCreationException;
import it.ninebee.lasa.laisfala.conversation.service.IConversationService;
import it.ninebee.lasa.laisfala.servicecredentials.ServiceCredentialVO;

//TODO Incluir logs em severidades diferentes para facilitar debugging
public class WatsonService implements IConversationService {

	//FIXME Esta classe deve ser agnóstica da estrutura de connector, já deve receber os parâmetros após "parse".
	ObjectMapper mapper;

	private ConnectorVO conn;
	private WatsonServiceAttributesVO serviceAttributes;
	private ConversationService conversationService;

	private static Logger logger = LoggerFactory.getLogger(WatsonService.class);

	public WatsonService(ObjectMapper mapper,ConnectorVO conn) throws ConversationServiceCreationException {
		this.mapper = mapper;
		initialize(conn);
	}

	@Override
	public ConversationResponseVO call(ConversationRequestVO request) {
		MessageRequest message = WatsonMessageConverter.convertToMessageRequest(request);
		logger.trace("message-request:\n"+message);
		MessageResponse response = this.conversationService.message(serviceAttributes.getWorkspaceId(), message)
				.execute();
		logger.trace("message-response:\n"+response);
		ConversationResponseVO laisResp = WatsonMessageConverter.convertToConversationResponse(response);
		laisResp.setContextId(request.getContextId());
		logger.trace("lais-message-request:\n"+laisResp);
		return laisResp;
	}

	private void initialize(ConnectorVO conn) throws ConversationServiceCreationException {
		this.conn = conn;
		parseAttributes();
		initializeConversationService();
	}

	private void initializeConversationService() {
		ServiceCredentialVO credential = this.conn.getCredential();
		ConversationService service = new ConversationService(serviceAttributes.getVersion());
		service.setUsernameAndPassword(credential.getUsername(), credential.getPassword());
		this.conversationService = service;
	}

	private void parseAttributes() throws ConversationServiceCreationException {
		String strAttr = conn.getAttributes();
		
		if (StringUtils.isBlank(strAttr)) {
			throw new ConversationServiceCreationException("Não foi possível recuperar os attributos do connector "
					+ conn.getId() + " : <" + conn.getAttributes() + ">");
		}
		WatsonServiceAttributesVO attrs;
		try {
			attrs = mapper.readValue(strAttr, WatsonServiceAttributesVO.class);
		} catch (IOException e) {
			throw new ConversationServiceCreationException("Não foi possível realizar parse dos attributos do connector "
					+ conn.getId() + " : <" + conn.getAttributes() + ">", e);
		}

		if (StringUtils.isBlank(attrs.getWorkspaceId())) {
			throw new ConversationServiceCreationException("Atributo workspace não definido.");
		}
		if (StringUtils.isBlank(attrs.getVersion())) {
			throw new ConversationServiceCreationException("Atributo version não definido.");
		}
		if (!isValidVersion(attrs.getVersion())) {
			throw new ConversationServiceCreationException("Atributo version não é valido.");
		}
		if (attrs.getAlternateIntents() == null) {
			attrs.setAlternateIntents(true);
		}
		this.serviceAttributes = attrs;
	}

	private boolean isValidVersion(String version) {
		return (ConversationService.VERSION_DATE_2016_07_11.equals(version)
				|| ConversationService.VERSION_DATE_2016_09_20.equals(version)
				|| ConversationService.VERSION_DATE_2017_02_03.equals(version));
	}

}
