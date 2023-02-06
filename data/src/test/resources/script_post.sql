DROP TABLE IF EXISTS group_has_mentor;
DROP TABLE IF EXISTS group_has_student;
DROP TABLE IF EXISTS tb_mentors;
DROP TABLE IF EXISTS tb_groups;
DROP TABLE IF EXISTS tb_courses;
DROP TABLE IF EXISTS tb_students;

CREATE TABLE tb_students(
	id BIGSERIAL PRIMARY KEY,
	first_name VARCHAR NOT NULL,
	last_name VARCHAR NOT NULL,
	patronymic VARCHAR,
	email VARCHAR NOT NULL,
	phone_number VARCHAR NOT NULL
);


CREATE TABLE IF NOT EXISTS tb_mentors(
	id BIGSERIAL PRIMARY KEY,
	first_name VARCHAR NOT NULL,
	last_name VARCHAR NOT NULL,
	patronymic VARCHAR,
	email VARCHAR NOT NULL,
	phone_number VARCHAR NOT NULL,
	salary VARCHAR NOT NULL
);



CREATE TABLE IF NOT EXISTS tb_courses (
	id BIGSERIAL PRIMARY KEY,
	name VARCHAR NOT NULL,
	subject VARCHAR NOT NULL,
	course_duration_in_month INT NOT NULL,
	lesson_duration TIME NOT NULL,
	course_price VARCHAR NOT NULL
);



CREATE TABLE IF NOT EXISTS tb_groups (
	id BIGSERIAL PRIMARY KEY,
	group_name VARCHAR NOT NULL,
	course_id BIGINT NOT NULL,
	start_date DATE NOT NULL,

	CONSTRAINT fk_group_course FOREIGN KEY(course_id) 
	REFERENCES tb_courses(id)
);



CREATE TABLE IF NOT EXISTS group_has_mentor (
	group_id BIGINT,
	mentor_id BIGINT NOT NULL,

	CONSTRAINT pk_group_mentor PRIMARY KEY(group_id, mentor_id),
	CONSTRAINT fk_group FOREIGN KEY(group_id) REFERENCES tb_groups(id),
	CONSTRAINT fk_mentor FOREIGN KEY(mentor_id) REFERENCES tb_mentors(id)
);



CREATE TABLE IF NOT EXISTS group_has_student (
	group_id BIGINT,
	student_id BIGINT,

	CONSTRAINT pk_group_student PRIMARY KEY(group_id, student_id),
	CONSTRAINT fk_group FOREIGN KEY(group_id) REFERENCES tb_groups(id),
	CONSTRAINT fk_student FOREIGN KEY(student_id) REFERENCES tb_students(id)
);



INSERT INTO tb_mentors(first_name, last_name, patronymic, email, phone_number, salary) VALUES 
('John', 'Doe', null, 'john@gmail.com', '+999999999999', '400'),
('Jack', 'Sparrow', null, 'captain@gmail.com', '+888888888888', '540'),
('Eddard', 'Stark', null, 'stark@gmail.com', '+777777777777', '446'),
('Tyrion', 'Lannister', null, 'imp_halfman@gmail.com', '+666666666666', '322'),
('Nodir', 'Umarov', 'Khusan ugli', 'mr.umarov.1995@gmail.com', '+998977452165', '745'),
('Farzona', 'Yusupova', 'Rustam kizi', 'f.yus@gmail.com', '+998996324785', '625'),
('Svyetlana', 'Ismailova', null, 'svetlanaism.1996@gmail.com', '+998914563298', '500'),
('Aziz', 'Alimov', 'Alim ugli', 'alim.alim@gmail.com', '+998978524165', '500'),
('Sardor', 'Sharipov', null, 'outcast@gmail.com', '+998978524124', '400'),
('Umid', 'Yakubov', 'Zafar ugli', 'hope@gmail.com', '+998979565194', '250');


INSERT INTO tb_courses(name, subject, course_duration_in_month, lesson_duration, course_price) VALUES
('Java Backend Development', 'Java', 8, '2:00', '200'),
('Android Mobile Development', 'Kotlin', 5, '1:30', '150'),
('Python Backend Development', 'Python', 6, '2:00', '200'),
('Web Frontent Development', 'JavaScript', 4, '2:30', '180');


INSERT INTO tb_students(first_name, last_name, email, phone_number) VALUES 
('Sid', 'Bowhey', 'sbowhey0@ycombinator.com', '+55 185 813 8175'),
('Vergil', 'Cancutt', 'vcancutt1@slate.com', '+55 830 758 0267'),
('Budd', 'Yushachkov', 'byushachkov2@blog.com', '+963 171 952 5545'),
('Fiann', 'Angrove', 'fangrove3@google.nl', '+7 153 768 6520'),
('Bernete', 'Willcock', 'bwillcock4@mysql.com', '+86 149 532 4324'),
('Grier', 'Barron', 'gbarron5@pcworld.com', '+223 400 223 1124'),
('Shep', 'Kyteley', 'skyteley6@smh.com.au', '+55 696 813 4975'),
('Koenraad', 'Carriage', 'kcarriage7@spiegel.de', '+380 215 981 8621'),
('Sarajane', 'Pashba', 'spashba8@vistaprint.com', '+299 445 928 1668'),
('Krystal', 'Carlick', 'kcarlick9@ehow.com', '+351 778 363 0618'),
('August', 'Galey', 'agaleya@51.la', '+86 997 751 7684'),
('Davine', 'McMorran', 'dmcmorranb@com.com', '+63 743 456 8166'),
('Grace', 'Losel', 'gloselc@t-online.de', '+380 349 288 7824'),
('Paxon', 'O''Cassidy', 'pocassidyd@mail.ru', '+255 646 606 6729'),
('Giovanna', 'Bruckental', 'gbruckentale@google.com.hk', '+86 176 277 6811'),
('Hildagarde', 'Garford', 'hgarfordf@examiner.com', '+7 916 576 4499'),
('Jeremie', 'Pilmore', 'jpilmoreg@diigo.com', '+33 715 373 3984'),
('Oswell', 'Waylett', 'owayletth@netvibes.com', '+86 486 966 8778'),
('Portie', 'Caffrey', 'pcaffreyi@weibo.com', '+976 187 646 8345'),
('Cathy', 'Morse', 'cmorsej@tuttocitta.it', '+55 365 847 0246'),
('Sergei', 'Shotboult', 'sshotboultk@google.com.hk', '+63 182 336 5465'),
('Bevan', 'Hurtado', 'bhurtadol@webeden.co.uk', '+86 465 107 3072'),
('Calhoun', 'Robb', 'crobbm@yahoo.co.jp', '+62 354 218 5368'),
('Beauregard', 'Skilbeck', 'bskilbeckn@yahoo.co.jp', '+47 449 967 3996'),
('Ignacio', 'Syred', 'isyredo@qq.com', '+55 268 301 3865'),
('Godiva', 'Trevain', 'gtrevainp@nyu.edu', '+86 831 715 0551'),
('Perrine', 'Balf', 'pbalfq@edublogs.org', '+57 310 870 4698'),
('Gertrud', 'Blanshard', 'gblanshardr@ovh.net', '+86 420 473 1923'),
('Bram', 'Gerry', 'bgerrys@foxnews.com', '+86 998 598 9227'),
('Mame', 'Baser', 'mbasert@archive.org', '+57 531 305 5216'),
('Tarah', 'Trees', 'ttreesu@apache.org', '+380 626 517 0381'),
('Ferdinanda', 'Peacher', 'fpeacherv@smugmug.com', '+57 121 313 0643'),
('Kevon', 'Geistmann', 'kgeistmannw@google.it', '+92 330 187 9445'),
('Nestor', 'Athowe', 'nathowex@webeden.co.uk', '+57 125 592 2267'),
('Ossie', 'Erik', 'oeriky@china.com.cn', '+86 970 966 8993'),
('Pauly', 'Vasilic', 'pvasilicz@state.tx.us', '+63 498 543 7380'),
('Lillis', 'Pren', 'lpren10@elpais.com', '+64 931 290 9611'),
('Nance', 'Soeiro', 'nsoeiro11@whitehouse.gov', '+7 213 317 9440'),
('Kev', 'Couchman', 'kcouchman12@sogou.com', '+66 823 771 0908'),
('Frank', 'Clougher', 'fclougher13@addthis.com', '+236 459 408 2962'),
('Boyce', 'McMearty', 'bmcmearty14@cdc.gov', '+86 609 402 7933'),
('Sherrie', 'Barrable', 'sbarrable15@cbsnews.com', '+84 264 785 4599'),
('Mark', 'Fass', 'mfass16@princeton.edu', '+230 353 320 7492'),
('Rosamund', 'Horley', 'rhorley17@geocities.com', '+86 377 823 9784'),
('Conny', 'Tod', 'ctod18@bbc.co.uk', '+62 202 947 3348'),
('Jerrie', 'Lockhead', 'jlockhead19@shinystat.com', '+33 599 630 4203'),
('Bernadene', 'Selley', 'bselley1a@google.pl', '+1 809 894 1015'),
('Kristian', 'Floyde', 'kfloyde1b@auda.org.au', '+86 656 292 3733'),
('Judith', 'Vasyutin', 'jvasyutin1c@google.it', '+232 967 418 9564'),
('Brynne', 'Markwell', 'bmarkwell1d@wordpress.com', '+7 282 301 9693'),
('Malorie', 'De Normanville', 'mdenormanville1e@independent.co.uk', '+62 673 187 2240'),
('Nora', 'Silver', 'nsilver1f@yandex.ru', '+33 600 165 6955'),
('Suzann', 'Obell', 'sobell1g@blogger.com', '+86 376 486 4098'),
('Mallorie', 'Clewley', 'mclewley1h@rambler.ru', '+503 571 100 8919'),
('Tracy', 'Sphinxe', 'tsphinxe1i@google.com.hk', '+62 134 325 4647'),
('Rick', 'O'' Ronan', 'roronan1j@slideshare.net', '+86 373 216 4509'),
('Celesta', 'Arundale', 'carundale1k@tiny.cc', '+7 502 832 7747'),
('Sarge', 'Delamar', 'sdelamar1l@multiply.com', '+27 328 438 5339'),
('Micah', 'Upshall', 'mupshall1m@issuu.com', '+62 648 850 2721'),
('Jaye', 'Seine', 'jseine1n@csmonitor.com', '+86 820 692 5914'),
('Shermy', 'Orris', 'sorris1o@instagram.com', '+63 153 150 4030'),
('Vaclav', 'Doveston', 'vdoveston1p@cnet.com', '+81 618 755 0319'),
('Ewart', 'Drews', 'edrews1q@creativecommons.org', '+98 720 922 3838'),
('Hedwiga', 'Faichnie', 'hfaichnie1r@flavors.me', '+46 504 859 4876'),
('Ramsay', 'Kenrick', 'rkenrick1s@discovery.com', '+86 341 804 6640'),
('Tristam', 'Mughal', 'tmughal1t@mtv.com', '+86 787 741 9212'),
('Denise', 'Schmidt', 'dschmidt1u@shop-pro.jp', '+66 766 663 9552'),
('Theo', 'Duns', 'tduns1v@dedecms.com', '+212 339 313 2670'),
('Meg', 'Povey', 'mpovey1w@twitter.com', '+970 118 207 2330'),
('Charissa', 'McCullough', 'cmccullough1x@google.pl', '+86 347 903 0132'),
('Prissie', 'Hattrick', 'phattrick1y@nydailynews.com', '+62 734 155 1551'),
('Ban', 'Parsisson', 'bparsisson1z@unc.edu', '+63 674 819 0185'),
('Justinn', 'Stower', 'jstower20@aol.com', '+86 807 813 4888'),
('Norman', 'Lamperd', 'nlamperd21@altervista.org', '+60 425 670 1115'),
('Marwin', 'Casperri', 'mcasperri22@npr.org', '+7 779 289 6362'),
('Stillmann', 'Zahor', 'szahor23@angelfire.com', '+44 667 285 1163'),
('Dolly', 'Boyson', 'dboyson24@yahoo.com', '+7 934 701 8356'),
('Miguelita', 'Dict', 'mdict25@cpanel.net', '+86 111 442 1697'),
('Cori', 'Arntzen', 'carntzen26@ucoz.com', '+94 952 918 2402'),
('Rozalin', 'Rosita', 'rrosita27@dedecms.com', '+593 571 413 8613'),
('Isobel', 'Loos', 'iloos28@reverbnation.com', '+33 886 358 8343'),
('Maurizio', 'Oade', 'moade29@tripod.com', '+1 205 449 1075'),
('Maxie', 'Meeson', 'mmeeson2a@shareasale.com', '+62 424 501 9711'),
('Siusan', 'Shelmardine', 'sshelmardine2b@ask.com', '+34 650 211 6326'),
('Reade', 'Gapp', 'rgapp2c@amazon.co.jp', '+62 464 195 7844'),
('Evangelin', 'Baverstock', 'ebaverstock2d@trellian.com', '+93 371 705 1186'),
('Jule', 'MacMenemy', 'jmacmenemy2e@goo.gl', '+33 671 841 1169'),
('Gelya', 'Rosson', 'grosson2f@reuters.com', '+351 286 658 1928'),
('Ced', 'Gottschalk', 'cgottschalk2g@blog.com', '+380 692 483 2351'),
('Babita', 'Askaw', 'baskaw2h@cdc.gov', '+236 973 974 9624'),
('Annaliese', 'Ivanchikov', 'aivanchikov2i@disqus.com', '+48 912 806 6833'),
('Aile', 'Sokell', 'asokell2j@tinyurl.com', '+63 542 297 5496'),
('Burch', 'Darey', 'bdarey2k@gmpg.org', '+227 398 716 4966'),
('Saba', 'Groll', 'sgroll2l@ucoz.ru', '+86 208 712 6838'),
('Symon', 'Hysom', 'shysom2m@i2i.jp', '+964 305 141 6504'),
('Allyn', 'Andreini', 'aandreini2n@xinhuanet.com', '+53 931 727 2094'),
('Vince', 'Cadden', 'vcadden2o@cdbaby.com', '+52 500 696 0510'),
('Ewan', 'McAlees', 'emcalees2p@soundcloud.com', '+267 635 208 1895'),
('Ardyth', 'Skains', 'askains2q@squarespace.com', '+62 968 799 5599'),
('Kirsteni', 'Dielhenn', 'kdielhenn2r@adobe.com', '+351 559 611 4851');


INSERT INTO tb_groups(group_name, start_date, course_id) VALUES 
('Blesbok', '2023-03-18', 2),
('Squirrel, eurasian red', '2023-04-13', 3),
('Bandicoot, short-nosed', '2023-03-18', 4),
('African elephant', '2023-05-26', 1),
('Dog, black-tailed prairie', '2023-01-28', 2),
('Wild water buffalo', '2023-04-13', 3),
('Galapagos albatross', '2023-04-06', 4),
('Lapwing, southern', '2023-04-04', 1),
('Baboon, chacma', '2023-03-05', 2),
('Galapagos hawk', '2023-01-29', 3);



INSERT INTO group_has_mentor VALUES 
(2, 9),
(9, 3),
(4, 4),
(5, 10),
(6, 4),
(7, 1),
(8, 8),
(9, 9),
(10, 7),
(1, 4),
(2, 2),
(3, 3),
(4, 8),
(5, 6),
(6, 9),
(7, 7),
(8, 6),
(9, 5),
(10, 10),
(1, 1),
(4, 2),
(3, 5),
(4, 7),
(5, 5),
(6, 6);


INSERT INTO group_has_student VALUES 
(2, 2),
(3, 5),
(4, 3),
(5, 2),
(6, 9),
(7, 1),
(8, 10),
(7, 9),
(1, 10),
(1, 1),
(2, 12),
(3, 13),
(4, 14),
(5, 1),
(6, 95),
(7, 17),
(8, 18),
(9, 33),
(10, 22),
(1, 21),
(2, 22),
(3, 23),
(4, 24),
(5, 25),
(6, 26),
(7, 27),
(8, 28),
(9, 29),
(10, 30),
(1, 31),
(2, 32),
(3, 33),
(4, 34),
(5, 35),
(6, 36),
(7, 37),
(8, 38),
(9, 39),
(10, 40),
(1, 41),
(2, 42),
(3, 43),
(4, 44),
(5, 45),
(6, 46),
(7, 47),
(8, 48),
(9, 48),
(10, 50),
(1, 51),
(2, 52),
(3, 53),
(4, 54),
(5, 55),
(6, 56),
(7, 57),
(8, 58),
(9, 59),
(10, 60),
(1, 61),
(2, 62),
(3, 63),
(4, 64),
(5, 65),
(6, 66),
(7, 67),
(8, 68),
(9, 69),
(10, 70),
(1, 71),
(2, 72),
(3, 73),
(4, 74),
(5, 75),
(6, 76),
(7, 77),
(8, 78),
(9, 79),
(10, 80),
(1, 81),
(2, 82),
(3, 83),
(4, 84),
(5, 85),
(6, 86),
(7, 87),
(8, 88),
(9, 89),
(10, 90),
(1, 91),
(2, 92),
(3, 93),
(4, 94),
(5, 95),
(6, 96),
(7, 97),
(8, 98),
(9, 99),
(10, 100),
(1, 75),
(2, 33),
(7, 23),
(4, 86),
(5, 54),
(6, 60),
(7, 79),
(8, 67),
(9, 49),
(10, 10),
(4, 10),
(6, 99),
(8, 87),
(8, 1),
(5, 15),
(4, 16),
(3, 45),
(2, 66),
(7, 19),
(10, 99),
(9, 21),
(8, 22),
(7, 22),
(6, 24),
(5, 26),
(4, 27),
(3, 28),
(2, 29),
(10, 66),
(9, 31),
(8, 32),
(7, 33),
(6, 34),
(4, 36),
(3, 37),
(2, 38),
(1, 39),
(10, 77),
(9, 41),
(8, 42),
(7, 43),
(6, 44),
(4, 46),
(3, 47),
(2, 48),
(1, 49),
(10, 36);