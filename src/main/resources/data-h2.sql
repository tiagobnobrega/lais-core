INSERT INTO public.service_credential
(co_credential, co_username, co_password, dh_alteracao)
VALUES('WATSON_LASA_GTS_SO', '314c8a09-0d22-46c9-ada4-0bbc32e75237', '8N16tiTbaMwF', now());

INSERT INTO public.workspace
(co_credential, co_workspace, co_tipo, de_conexao, dh_alteracao)
VALUES('WATSON_LASA_GTS_SO', 'LASA_TESTE', 'WATSON', '{"workspaceId":"fa91bb9a-31a2-40b5-ae93-b320b414e5ed"}', now());

INSERT INTO public.workspace
(co_workspace, co_tipo, de_conexao, dh_alteracao)
VALUES('WKS2', 'WATSON', 'TESTE2', now());