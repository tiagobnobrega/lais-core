package it.ninebee.lasa.laisfala.servicecredentials;

import org.skife.jdbi.v2.DBI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ServiceCredentialService {

	@Autowired
	@Qualifier("laisfalaDbi")
	DBI laisfalaDbi;

	public ServiceCredentialVO getById(final String id){
		return laisfalaDbi.withHandle((h) -> {
			IServiceCredentialDAO servDAO = h.attach(IServiceCredentialDAO.class);
			return servDAO.getById(id);
		});
	}
	
}
