package it.ninebee.lasa.laisfala.conversation;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConversationIntentVO {

	private BigDecimal confidence;
	private String id;
	
}
