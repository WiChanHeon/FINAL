create table smember(
	id varchar2(12) not null primary key,
	passwd varchar2(10) not null,
	name varchar2(10) not null,
	email varchar2(20) not null,
	reg_date date not null
);
create table sboard(
	seq number not null primary key,
	title varchar2(100) not null,
	content clob not null,
	hit number(5) default 0 not null,
	regdate date not null,
	filename varchar2(100) not null,
	id varchar2(12) not null,
	constraint sboard_smember_fk1 foreign key(id) references smember (id)
);
create sequence board_seq;

create table sreply(
	re_no number not null primary key,
	re_content varchar2(600) not null,
	re_date date not null,
	seq number not null,
	id varchar2(12) not null,
	constraint sreply_sboard_fk1 foreign key(seq) references sboard (seq),
	constraint sreply_smember_fk2 foreign key(id) references smember (id)
);

create sequence re_seq;