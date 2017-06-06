package it.ninebee.lasa.laisfala.luis;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import it.ninebee.lasa.laisfala.connector.ConnectorVO;
import it.ninebee.lasa.laisfala.conversation.ConversationRequestVO;
import it.ninebee.lasa.laisfala.conversation.ConversationResponseVO;
import it.ninebee.lasa.laisfala.conversation.ConversationServiceCreationException;
import it.ninebee.lasa.laisfala.conversation.service.IConversationService;

public class LuisService implements IConversationService {

	private RestTemplate restTemplate;
	private LuisServiceAttributes serviceAttributes;
	private final HttpEntity<Object> requestHttpEntity;
	private final String LUIS_URL = "https://westus.api.cognitive.microsoft.com/luis/v2.0/apps";
	private final String SERVICE_MASK = "%s/%s?q=%s&verbose=true";
	private final String AUTH_HTTP_HEADER = "Ocp-Apim-Subscription-Key";

	public LuisService(LuisServiceAttributes serviceAttributes) {
		this.serviceAttributes = serviceAttributes;
		this.restTemplate = new RestTemplate();

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add(AUTH_HTTP_HEADER, serviceAttributes.getEndpointKey());
		this.requestHttpEntity = new HttpEntity<Object>(requestHeaders);
	}

	@Override
	public ConversationResponseVO call(ConversationRequestVO request) {

		ResponseEntity<LuisResponse> respEntity = restTemplate.exchange(
				String.format(SERVICE_MASK, LUIS_URL, serviceAttributes.getAppId(), request.getInputText()),
				HttpMethod.GET,
				this.requestHttpEntity, LuisResponse.class);
		
		LuisResponse luisResp = respEntity.getBody();
		ConversationResponseVO resp = LuisResponseConverter.convertToConversationResponse(luisResp);

		resp.setContext(request.getContext());
		resp.setContextId(request.getContextId());

		return resp;
	}

}
