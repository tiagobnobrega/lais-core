package it.ninebee.lasa.laisfala.conversation;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ConversationIntentVO {

	private double confidence;
	private String intent;
	
}
