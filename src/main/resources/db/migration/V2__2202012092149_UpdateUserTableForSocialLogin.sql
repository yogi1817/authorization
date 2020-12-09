ALTER TABLE auth.user ADD name varchar(50);
ALTER TABLE auth.user ADD image_url varchar(500);
ALTER TABLE auth.user ADD social_login_type varchar(10) default 'spjUser';