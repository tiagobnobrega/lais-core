

CREATE TABLE IF NOT EXISTS workspace(
	"co_credential" Varchar(80) NOT NULL,
    "co_workspace" Varchar(80) NOT NULL,
    "co_tipo" Varchar(10),
    "de_conexao" TEXT,
    "dh_alteracao" Timestamp
);

CREATE TABLE IF NOT EXISTS service_credential(
	"co_credential" Varchar(80),
    "co_username" Varchar(200) NOT NULL,
    "co_password" Varchar(200),
    "dh_alteracao" Timestamp
);

INSERT INTO public.service_credential
(co_credential, co_username, co_password, dh_alteracao)
VALUES('WATSON_LASA_GTS_SO', '314c8a09-0d22-46c9-ada4-0bbc32e75237', '8N16tiTbaMwF', now());

INSERT INTO public.workspace
(co_credential, co_workspace, co_tipo, de_conexao, dh_alteracao)
VALUES('WATSON_LASA_GTS_SO', 'LASA_TESTE', 'WATSON', '{"workspaceId":"fa91bb9a-31a2-40b5-ae93-b320b414e5ed"}', now());

INSERT INTO public.workspace
(co_workspace, co_tipo, de_conexao, dh_alteracao)
VALUES('WKS2', 'WATSON', 'TESTE2', now());