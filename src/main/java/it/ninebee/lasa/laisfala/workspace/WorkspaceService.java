package it.ninebee.lasa.laisfala.workspace;

import java.util.List;

import org.skife.jdbi.v2.DBI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class WorkspaceService {

	@Autowired
	@Qualifier("laisfalaDbi")
	DBI laisfalaDbi;
	
	@Autowired
	WorkspaceDAO workspaceDao;
	
	public List<WorkspaceVO> getAll(){
		return laisfalaDbi.withHandle((h) -> {
				WorkspaceDAO wksDao =  h.attach(WorkspaceDAO.class);
				return wksDao.getAll();
		});
	}
	
}
