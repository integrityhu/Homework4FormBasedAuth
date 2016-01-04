create database jdbcauthtest;

CREATE TABLE IF NOT EXISTS `systemuser` (
  `userid` bigint(20) NOT NULL AUTO_INCREMENT,
  `emailAddress` varchar(255) COLLATE utf8_hungarian_ci DEFAULT NULL,
  `enabled` tinyint(1) NOT NULL,
  `userpassword` varchar(255) COLLATE utf8_hungarian_ci NOT NULL,
  `username` varchar(255) COLLATE utf8_hungarian_ci NOT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci AUTO_INCREMENT=3 ;

CREATE TABLE IF NOT EXISTS `user_join_group` (
  `user_name` varchar(255) COLLATE utf8_hungarian_ci NOT NULL,
  `group_name` varchar(64) COLLATE utf8_hungarian_ci NOT NULL,
  PRIMARY KEY (`user_name`,`group_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

-- password: q (SHA-256/BASE64)
insert into systemuser (username,userpassword,enabled,emailaddress) values ('Papp Zoltán','ypeBEsobvcr6wjGzmiPcTaeG7/gUfE5yuYB3ha/uSLs=',true,'papp.zoltan@mondoka.hu');
insert into systemuser (username,userpassword,enabled,emailaddress) values ('Papp Zoltán (integrity)','jjXCzTv2ZBvbDiBQt2kyy7LmA0oN2swdm+qCprpX988=',true,'papp.zoltan@integrity.hu');

insert into user_join_group (user_name,group_name) values ('papp.zoltan@mondoka.hu','ROLE_ADMIN');
insert into user_join_group (user_name,group_name) values ('papp.zoltan@integrity.hu','ROLE_USER');

CREATE VIEW `v_active_user` AS select `u`.`emailAddress` AS `username`,`u`.`userpassword` AS `password` from `systemuser` `u` where enabled = 1;