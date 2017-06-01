CREATE TABLE IF NOT EXISTS service_credential(
		"co_credential" Varchar(80),
    "co_username" Varchar(200) NOT NULL,
    "co_password" Varchar(200),
    "dh_alteracao" Timestamp
);
CREATE TABLE IF NOT EXISTS workspace(
	"co_credential" Varchar(80) NOT NULL,
    "co_workspace" Varchar(80) NOT NULL,
    "co_tipo" Varchar(10),
    "de_conexao" TEXT,
    "dh_alteracao" Timestamp
);

commit;