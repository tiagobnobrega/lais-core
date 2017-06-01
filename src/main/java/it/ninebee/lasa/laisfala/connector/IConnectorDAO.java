package it.ninebee.lasa.laisfala.connector;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

public interface IConnectorDAO {

	@SqlQuery("SELECT co_credential,co_connector,co_type,de_attributes,dh_alteracao FROM connector")
	@Mapper(ConnectorRowMapper.class)
	public List<ConnectorVO> getAll();
	
}
