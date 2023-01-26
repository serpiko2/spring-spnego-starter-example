create TABLE user_info (
                           id long not null AUTO_INCREMENT,
                           username varchar(1000) not null,
                           PRIMARY KEY (id),
);
create TABLE user_psh (
                           id long not null AUTO_INCREMENT,
                           passphrase varchar(1000) not null,
                           username_id varchar(1000) not null,
                           PRIMARY KEY (id),
                           FOREIGN KEY (username_id) REFERENCES user_info
);
commit;