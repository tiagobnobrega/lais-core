package it.ninebee.lasa.laisfala.workspace;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

public interface WorkspaceDAO {

	@SqlQuery("SELECT * FROM workspace")
	@Mapper(WorkspaceRowMapper.class)
	public List<WorkspaceVO> getAll();
	
}
