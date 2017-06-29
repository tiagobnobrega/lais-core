package it.ninebee.lasa.laisfala.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.ninebee.lasa.laisfala.conversation.ConversationRequestVO;
import it.ninebee.lasa.laisfala.conversation.ConversationResponseVO;
import it.ninebee.lasa.laisfala.conversation.ConversationServiceCreationException;
import it.ninebee.lasa.laisfala.conversation.service.ConversationServiceManager;
import it.ninebee.lasa.laisfala.conversation.service.IConversationService;

@RestController
@RequestMapping(value = "api")
public class FalaController {

	@Autowired
	ConversationServiceManager csManager;
	
	@RequestMapping(value = "fala", method = RequestMethod.POST)
	ConversationResponseVO fala(@RequestBody ConversationRequestVO request) throws ConversationServiceCreationException{
		IConversationService convServ =  csManager.getServiceFor(request);
		return convServ.call(request);
	}
	
}
