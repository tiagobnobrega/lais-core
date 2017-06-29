package it.ninebee.lasa.laisfala.web;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.ninebee.lasa.laisfala.connector.ConnectorService;
import it.ninebee.lasa.laisfala.connector.ConnectorVO;
import it.ninebee.lasa.laisfala.conversation.ConversationRequestVO;
import it.ninebee.lasa.laisfala.conversation.ConversationResponseVO;
import it.ninebee.lasa.laisfala.conversation.ConversationServiceCreationException;
import it.ninebee.lasa.laisfala.conversation.service.ConversationServiceManager;
import it.ninebee.lasa.laisfala.conversation.service.IConversationService;
import it.ninebee.lasa.laisfala.connector.ConnectorTypeEnum;
import it.ninebee.lasa.laisfala.servicecredentials.ServiceCredentialService;
import it.ninebee.lasa.laisfala.servicecredentials.ServiceCredentialVO;
import it.ninebee.lasa.laisfala.watson.WatsonService;

@RestController
@RequestMapping(value = "api/test")
public class TestController {

	@Autowired
	ConnectorService connService;
	
	@Autowired
	ConversationServiceManager csManager;
	
	@Autowired
	ServiceCredentialService credentialService;
	
	@RequestMapping(value = "credential/{id}", method = RequestMethod.GET)
	ServiceCredentialVO serviceCredential(@PathVariable("id") String id){
		ServiceCredentialVO cred = credentialService.getById(id);
		return cred;
	}

	
	
	@RequestMapping(value = "connector", method = RequestMethod.GET)
	List<ConnectorVO> connector() {
	
		return connService.getAll();
		
//		return WorkspaceVO.builder().id("WKS_TESTE").tipo(WorkspaceTipoEnum.WATSON)
//				.connectionString("{\"teste\":\"abcde\"}").dtAlteracao(LocalDateTime.now()).build();
	}

	@RequestMapping(value = "rest", method = RequestMethod.GET)
	Map<String, Object> rest() {
		Map<String, Object> ret = new HashMap<String, Object>();
		Random rand = new Random();
		ret.put("integerValue", rand.nextInt());
		ret.put("doubleValue", rand.nextDouble());
		ret.put("booleanValue", rand.nextBoolean());
		ret.put("stringValue", "Este é uma valor de texto para validação");
		ret.put("dateValue", LocalDate.now());
		ret.put("dateTimeValue", LocalDateTime.now(ZoneId.of("UCT")));
		ret.put("defaultTimezone", ZoneId.systemDefault());

		ret.put("integerArray", new Integer[] { rand.nextInt(), rand.nextInt(), rand.nextInt(), rand.nextInt() });
		ret.put("doubleArray", new Double[] { rand.nextDouble(), rand.nextDouble(), rand.nextDouble() });
		ret.put("stringArray", new String[] { "A", "B", "C" });

		Map<String, Object> nested = new HashMap<String, Object>(ret);
		ret.put("nestedObject", nested);

		return ret;
	}

	@RequestMapping(value = "error", method = RequestMethod.GET)
	Map<String, Object> error() throws LaisException {
		throw new LaisException("Este é um erro de teste para o rest envelope");
	}

	@RequestMapping(value = "error2", method = RequestMethod.GET)
	Map<String, Object> error2() throws LaisException {
		throw new LaisException("Este é um erro de teste para o rest envelope",
				new Exception("Esta é a mensagem do erro original"));
	}

	@RequestMapping(value = "error3", method = RequestMethod.GET)
	Map<String, Object> error3() throws LaisException {
		throw new LaisException(new Exception("Esta é a mensagem do erro original"));
	}

}
