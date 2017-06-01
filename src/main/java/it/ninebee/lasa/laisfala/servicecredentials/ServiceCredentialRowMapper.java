package it.ninebee.lasa.laisfala.servicecredentials;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

public class ServiceCredentialRowMapper implements ResultSetMapper<ServiceCredentialVO> {

	@Override
	public ServiceCredentialVO map(int index, ResultSet r, StatementContext ctx) throws SQLException {
		ServiceCredentialVO service = ServiceCredentialVO.builder()
				.id(r.getString("co_credential"))
				.username(r.getString("co_username"))
				.password(r.getString("co_password"))
				.build();
		return service;
	}

}
