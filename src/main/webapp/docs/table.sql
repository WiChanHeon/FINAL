create table f_member(
   mem_num number not null primary key,
   mem_id varchar2(20) not null,
   mem_name varchar2(30) not null,
   mem_pw varchar2(15) not null,
   mem_cell varchar2(15) not null,
   mem_email varchar2(50) not null,
   mem_reg date not null
);

--카드 이용내역은 현재 생성 제외
--create table f_1111222233334444(
--   pay_num number not null primary key,
--   pay_reg date not null,
--   pay_price number not null,
--   pay_spot varchar2(30) not null,
--   pay_rate number(3,1) default(0),
--   pay_point number default(0)
--);

create table f_info(
   info_name varchar2(10) not null primary key,
   info_img varchar2(200) not null,
   info_stitle varchar2(50) not null,
   info_scontent varchar2(200) not null,
   info_flag varchar2(4) not null,
   info_rcm number(1),
   info_view number(1) default(1),
   cp_afee varchar2(1000) not null,
   cp_voucher varchar2(1000),
   cp_benefit varchar2(1000),
   cp_service varchar2(1000),
   cp_advice varchar2(1000)
);

create table f_sapply(
   sap_num number not null primary key,
   sap_name varchar2(30) not null,
   sap_cell varchar2(15) not null
);

create table f_apply(
   ap_num number not null primary key,
   ap_rrn varchar2(13) not null,
   ap_name varchar2(30) not null,
   ap_ename varchar2(30) not null,
   ap_cell varchar2(15) not null,
   ap_email varchar2(50) not null,
   ap_address varchar2(300) not null,
   ap_paydate date not null,
   ap_bank varchar2(20) not null,
   ap_banknum varchar2(20) not null,
   ap_reg date not null,
   ap_job varchar2(20) not null,
   ap_pass number default(0),
   ap_id varchar2(20),
   card_num varchar2(16) unique
);

create table f_card(
   card_num varchar2(16) not null primary key,
   card_reg date not null,
   card_yyyy varchar2(4) not null,
   card_mm varchar2(2) not null,
   card_cvc varchar2(3) not null,
   card_pw varchar2(4) not null,
   card_limit number default(1000000),
   card_status number(1) default(0)
);

create table f_loss(
   loss_num number not null primary key,
   card_num varchar2(16) not null,
   loss_reg date not null,
   loss_memo varchar2(100),
   constraint loss_card_fk1 foreign key(card_num) references f_card(card_num)
);

create table f_ca(
   ca_num number not null primary key,
   card_num varchar2(16) not null,
   ca_reg date not null,
   ca_price number not null,
   ca_spot varchar2(20) not null,
   ca_sort number(1) default(0),
   ca_category number(2) default(0),
   ca_memo varchar2(100),
   ca_income number,
   constraint ca_card_fk1 foreign key(card_num) references f_card(card_num)
);

create table f_admin(
   m_id varchar2(20) not null primary key,
   m_name varchar2(30) not null,
   m_pw varchar2(15) not null,
   m_cell varchar2(15) not null,
   m_email varchar2(50) not null,
   m_position varchar2(16) not null,
   m_dept varchar2(30) not null
);

create table f_log(
   log_num number not null primary key,
   m_id varchar2(20) not null,
   log_reg date not null,
   log_content varchar2(100) not null,
   constraint log_admin_fk1 foreign key(m_id) references f_admin(m_id)
);

create table f_coboard(
   co_num number not null primary key,
   m_id varchar2(20) not null,
   m_name varchar2(30) not null,
   co_title varchar2(40) not null,
   co_content clob not null,
   co_reg date not null,
   co_hit number default(0),
   co_sort number(1) default(0),
   constraint coboard_admin_fk1 foreign key(m_id) references f_admin(m_id)
);

create table f_coboard_re(
   co_re_num number not null primary key,
   co_num number not null,
   m_id varchar2(20) not null,
   m_name varchar2(30) not null,
   co_re_content varchar2(600) not null,
   co_re_date date not null,   
   constraint coboardre_coboard_fk1 foreign key(co_num) references f_coboard(co_num),
   constraint coboardre_admin_fk2 foreign key(m_id) references f_admin(m_id)
);

create table f_notice(
   noti_num number not null primary key,
   noti_title varchar2(20) not null,
   noti_content clob not null,
   noti_reg date not null,
   m_id varchar2(20) not null,
   noti_writer varchar2(16) default('관리자'),
   constraint notice_admin_fk1 foreign key(m_id) references f_admin(m_id)
);


create sequence mem_seq;
--create sequence 0000111122223333_seq; --카드번호시퀀스
create sequence sap_seq;
create sequence ap_seq;
create sequence loss_seq;
create sequence ca_seq;
create sequence log_seq;
create sequence co_seq;
create sequence co_re_seq;
create sequence noti_seq;