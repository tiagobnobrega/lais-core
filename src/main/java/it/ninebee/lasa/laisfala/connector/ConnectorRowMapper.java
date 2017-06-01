package it.ninebee.lasa.laisfala.connector;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.ninebee.lasa.laisfala.servicecredentials.ServiceCredentialVO;

public class ConnectorRowMapper implements ResultSetMapper<ConnectorVO> {

	private static Logger logger = LoggerFactory.getLogger(ConnectorRowMapper.class);
	
	@Override
	public ConnectorVO map(int index, ResultSet rs, StatementContext ctx) throws SQLException {
		ServiceCredentialVO credential = ServiceCredentialVO.builder()
				.username(rs.getString("co_username"))
				.password(rs.getString("co_password"))
				.id(rs.getString("co_credential"))
				.build();
		
		ConnectorVO wks = ConnectorVO.builder()
				.id(rs.getString("co_connector"))
				.credential(credential)
				.attributes(rs.getString("de_attributes"))
				.dhAlteracao(rs.getTimestamp("dh_alteracao").toLocalDateTime())
				.type(ConnectorTypeEnum.from(rs.getString("co_type")))
				.build();
		return wks;
	}

}
