package it.ninebee.lasa.laisfala.watson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;

import it.ninebee.lasa.laisfala.connector.ConnectorVO;
import it.ninebee.lasa.laisfala.conversation.ConversationRequestVO;
import it.ninebee.lasa.laisfala.conversation.ConversationResponseVO;
import it.ninebee.lasa.laisfala.conversation.service.IConversationService;

@Service
public class WatsonService implements IConversationService {

	private static Logger logger = LoggerFactory.getLogger(WatsonService.class);
	
	public void test(){
//		{
//			  "url": "https://gateway.watsonplatform.net/conversation/api",
//			  "username": "314c8a09-0d22-46c9-ada4-0bbc32e75237",
//			  "password": "8N16tiTbaMwF"
//			}
		//fa91bb9a-31a2-40b5-ae93-b320b414e5ed
//		 new MessageRequest.Builder()
//		 .inputText("Aqui").context()().build();
		
		
		ConversationService service = new ConversationService(ConversationService.VERSION_DATE_2017_02_03);
		service.setUsernameAndPassword("314c8a09-0d22-46c9-ada4-0bbc32e75237", "8N16tiTbaMwF");

		MessageRequest newMessage = new MessageRequest.Builder().inputText("Olá").build();
		MessageResponse response = service.message("fa91bb9a-31a2-40b5-ae93-b320b414e5ed", newMessage).execute();
		logger.info("WATSON RESPONSE: "+response.toString());
	}

	@Override
	public ConversationResponseVO call(ConversationRequestVO request) {
		// TODO Auto-generated method stub
		return null;
	}

}
