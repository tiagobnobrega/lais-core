package it.ninebee.lasa.laisfala.workspace;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import it.ninebee.db.AbstractDao;

@Repository
public class WorkspaceDaoImp extends AbstractDao implements WorkspaceDAO {

	public WorkspaceDaoImp(@Qualifier("laisfalaDs") DataSource dataSource) {
		setDataSource(dataSource);
	}
	
	private class WksRowMapper implements RowMapper<WorkspaceVO>{
		@Override
		public WorkspaceVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new WorkspaceVO("1", WorkspaceTipoEnum.AWS, "", LocalDateTime.now());
		}
	}
	
	@Override
	public List<WorkspaceVO> getAll() {
		super.getJdbcTemplate().query("SELECT * from TABELA_NAO_EXISTE", new Object[]{}, new WksRowMapper());
		return null;
	}

}
