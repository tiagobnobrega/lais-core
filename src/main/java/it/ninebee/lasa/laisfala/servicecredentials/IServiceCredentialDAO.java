package it.ninebee.lasa.laisfala.servicecredentials;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

public interface IServiceCredentialDAO {

	@SqlQuery("SELECT co_credential, co_username, co_password, dh_alteracao FROM service_credential where co_credential = :id")
	@Mapper(ServiceCredentialRowMapper.class)
	public ServiceCredentialVO getById(@Bind("id") String id);
}
