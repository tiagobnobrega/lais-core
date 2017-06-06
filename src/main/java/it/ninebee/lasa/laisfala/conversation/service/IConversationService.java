package it.ninebee.lasa.laisfala.conversation.service;

import it.ninebee.lasa.laisfala.conversation.ConversationRequestVO;
import it.ninebee.lasa.laisfala.conversation.ConversationResponseVO;

public interface IConversationService {

	ConversationResponseVO call(ConversationRequestVO request);
}
