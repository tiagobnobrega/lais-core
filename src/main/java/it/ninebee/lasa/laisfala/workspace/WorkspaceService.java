package it.ninebee.lasa.laisfala.workspace;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkspaceService {

	@Autowired
	WorkspaceDAO workspaceDao;
	
	public List<WorkspaceVO> getAll(){
		return workspaceDao.getAll();
	}
	
}
