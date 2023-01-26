INSERT INTO user_info(username)
VALUES ("admin");

INSERT INTO user_info(username)
VALUES ("user");

INSERT INTO user_info(username)
VALUES ("guest");

INSERT INTO user_psh(passphrase, usernameid)
VALUES ("adminsecret0", "0");

INSERT INTO user_psh(passphrase, usernameid)
VALUES ("adminsecret1", "0");

INSERT INTO user_psh(passphrase, usernameid)
VALUES ("adminsecret2", "0");

INSERT INTO user_psh(passphrase, usernameid)
VALUES ("adminsecret3", "0");

INSERT INTO user_psh(passphrase, usernameid)
VALUES ("adminsecret4", "0");

INSERT INTO user_psh(passphrase, usernameid)
VALUES ("adminsecret5", "0");

INSERT INTO user_psh(passphrase, usernameid)
VALUES ("adminsecret6", "0");

INSERT INTO user_psh(passphrase, usernameid)
VALUES ("usernotsosecret0", "1");

INSERT INTO user_psh(passphrase, usernameid)
VALUES ("usernotsosecret1", "1");

INSERT INTO user_psh(passphrase, usernameid)
VALUES ("usernotsosecret2", "1");

commit;