package it.ninebee.lasa.laisfala.conversation.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.ninebee.lasa.laisfala.connector.ConnectorService;
import it.ninebee.lasa.laisfala.connector.ConnectorTypeEnum;
import it.ninebee.lasa.laisfala.connector.ConnectorVO;
import it.ninebee.lasa.laisfala.conversation.ConversationRequestVO;
import it.ninebee.lasa.laisfala.conversation.ConversationServiceCreationException;
import it.ninebee.lasa.laisfala.servicecredentials.IServiceCredentialDAO;

@Component
public class ConversationServiceManager {

	@Autowired
	List<IConversationServiceFactory> avalilableFactories;
	
	Map<ConnectorTypeEnum, IConversationServiceFactory> mappedFactories;
	
	@Autowired
	UnImplementedConversationFactory unimplementedConversationFactory;
	
	@Autowired
	ConnectorService connService;
	
	@PostConstruct
	private void init(){
		mapAvalilableFactories();
	}

	/**
	 * Realiza mapeamento de {@link IConversationServiceFactory} disponiveis para cada {@link ConnectorTypeEnum}
	 */
	private void mapAvalilableFactories() {
		ConnectorTypeEnum[] availableConnectorTypes = ConnectorTypeEnum.values();
		mappedFactories = new HashMap<ConnectorTypeEnum,IConversationServiceFactory>();
		Arrays.stream(availableConnectorTypes).forEach((e) -> {
			List<IConversationServiceFactory> factoriesFound= avalilableFactories.stream().filter((f) ->{
				return f.canResolve(e.getValue());
			}).collect(Collectors.toList());
			if(factoriesFound.size()>1){
				throw new IllegalStateException("Conflicting connector factories:"+detailConflictingFactories(factoriesFound, e));
			}if(factoriesFound.size()==0){
				mappedFactories.put(e,unimplementedConversationFactory);
			}else{
				mappedFactories.put(e,factoriesFound.get(0));
			}
		});
	}
	
	private String detailConflictingFactories(List<IConversationServiceFactory> factoriesFound,ConnectorTypeEnum connType){
		Optional<String> optFactoriesNames = factoriesFound.stream().map((e) -> {
			return "["+e.getClass().getName()+"]";
		}).reduce( (s1,s2)->{return s1+s2;});
		
		String factoriesNames = optFactoriesNames.orElse("");
		return factoriesNames+"::"+connType.name();
				
	}
	
	
	//FIXME Implementar CACHE para serviços !!!!!!!!!!! 
	public IConversationService getServiceFor(ConnectorVO conn) throws ConversationServiceCreationException{
		if(conn==null) throw new ConversationServiceCreationException("Erro creating service for null connector");
		
		IConversationServiceFactory factory = mappedFactories.get(conn.getType());
		IConversationService service  = factory.createFrom(conn);
		return service;
	}
	
	//FIXME Implementar CACHE para serviços !!!!!!!!!!! 
		public IConversationService getServiceFor(ConversationRequestVO request) throws ConversationServiceCreationException{
			String connId = request.getConnectorId();
			if(StringUtils.isBlank(connId)) throw new ConversationServiceCreationException("Connector id not specified.");
			ConnectorVO conn = connService.getById(connId);
			if(conn==null) throw new ConversationServiceCreationException(String.format("Could not find connector with id: %s", connId));
			return getServiceFor(conn);
		}
}
