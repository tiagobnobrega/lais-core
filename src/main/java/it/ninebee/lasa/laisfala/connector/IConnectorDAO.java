package it.ninebee.lasa.laisfala.connector;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

@RegisterMapper(ConnectorRowMapper.class)
public interface IConnectorDAO {

	@SqlQuery("SELECT con.co_credential,co_connector,co_type,de_attributes,cred.co_username,cred.co_password,con.dh_alteracao "
			+" FROM public.connector con "
			+" left join public.service_credential cred "
			+" on con.co_credential=cred.co_credential ")
	public List<ConnectorVO> getAll();
	
	@SqlQuery("SELECT con.co_credential,co_connector,co_type,de_attributes,cred.co_username,cred.co_password,con.dh_alteracao "
			+" FROM public.connector con "
			+" left join public.service_credential cred "
			+" on con.co_credential=cred.co_credential "
			+ " WHERE "
			+ "	co_connector= :id")
	public ConnectorVO getById(@Bind("id") String id);
	

}
