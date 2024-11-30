START TRANSACTION;
-- insert queries
insert ignore into users
values ('user', '{noop}12345', true);
insert ignore into `authorities`
values ('user', 'read');

insert ignore into users
values ('admin', '{bcrypt}$2a$12$WovTznOR8QYHHScJ2Fsr6.eBV5IzEyE0WTod2DR7PsokGeX0lxdjK', true); -- encrypted password from '123'

insert ignore into users
values ('lvs', '{bcrypt}$2a$12$WovTznOR8QYHHScJ2Fsr6.eBV5IzEyE0WTod2DR7PsokGeX0lxdjK', true);

insert ignore into users
values ('user', '{bcrypt}$2a$12$WovTznOR8QYHHScJ2Fsr6.eBV5IzEyE0WTod2DR7PsokGeX0lxdjK', true);


insert ignore into `authorities`
values ('admin', 'admin');
insert ignore into `authorities`
values ('lvs', 'admin');


INSERT INTO customer ( `email`, `password`, `role`) VALUES
('happy@example.com', '{bcrypt}$2a$12$WovTznOR8QYHHScJ2Fsr6.eBV5IzEyE0WTod2DR7PsokGeX0lxdjK', 'admin'),
( 'admin@example.com', '{bcrypt}$2a$12$WovTznOR8QYHHScJ2Fsr6.eBV5IzEyE0WTod2DR7PsokGeX0lxdjK', 'admin')
;




COMMIT;