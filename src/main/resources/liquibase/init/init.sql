insert into oauth_client_details(client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri,
authorities) values('client_1', '', '$2a$10$fbzwjp8OyBuLCK98tuEBGuF8JZySKU6thOcqd44cbgoBW2KeffnC.', 'select',
'client_credentials,refresh_token', '', 'oauth2');
insert into oauth_client_details(client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri,
authorities) values('client_2', '', '$2a$10$fbzwjp8OyBuLCK98tuEBGuF8JZySKU6thOcqd44cbgoBW2KeffnC.', 'select',
'password,refresh_token', '', 'oauth2');
insert into oauth_client_details(client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri,
authorities) values('client_3', '', '$2a$10$fbzwjp8OyBuLCK98tuEBGuF8JZySKU6thOcqd44cbgoBW2KeffnC.', 'select',
'authorization_code,client_credentials,password,implicit,refresh_token', '', 'oauth2');


insert into `user`(user_name, password) values('user_1', '$2a$10$fbzwjp8OyBuLCK98tuEBGuF8JZySKU6thOcqd44cbgoBW2KeffnC.');
insert into role(role_name, `desc`) value('admin', '管理员');
insert into user_role(user_id, role_id) value(1,1);