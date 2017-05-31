package it.ninebee.lasa.laisfala.workspace;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorkspaceRowMapper implements ResultSetMapper<WorkspaceVO> {

	private static Logger logger = LoggerFactory.getLogger(WorkspaceRowMapper.class);
	
	@Override
	public WorkspaceVO map(int index, ResultSet rs, StatementContext ctx) throws SQLException {
		WorkspaceVO wks = WorkspaceVO.builder()
				.id(rs.getString("co_workspace"))
				.connectionString(rs.getString("de_conexao"))
				.dtAlteracao(rs.getTimestamp("dh_alteracao").toLocalDateTime())
				.tipo(WorkspaceTipoEnum.from(rs.getString("co_tipo")))
				.build();
		return wks;
	}

}
