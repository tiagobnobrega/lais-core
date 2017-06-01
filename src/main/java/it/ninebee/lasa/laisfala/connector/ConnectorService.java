package it.ninebee.lasa.laisfala.connector;

import java.util.List;

import org.skife.jdbi.v2.DBI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ConnectorService {

	@Autowired
	@Qualifier("laisfalaDbi")
	DBI laisfalaDbi;
	
	public List<ConnectorVO> getAll(){
		return laisfalaDbi.withHandle((h) -> {
				IConnectorDAO connDao =  h.attach(IConnectorDAO.class);
				return connDao.getAll();
		});
	}
	
}
