-- 테이블 삭제
DROP TABLE bookmark CASCADE CONSTRAINTS;
DROP TABLE coffeeshop CASCADE CONSTRAINTS;
DROP TABLE hashtag_classification CASCADE CONSTRAINTS;
DROP TABLE membership CASCADE CONSTRAINTS;
DROP TABLE report CASCADE CONSTRAINTS;
DROP TABLE report_type CASCADE CONSTRAINTS;
DROP TABLE review CASCADE CONSTRAINTS;
DROP TABLE shop_hashtag CASCADE CONSTRAINTS;
DROP TABLE uploadfile CASCADE CONSTRAINTS;
DROP TABLE code CASCADE CONSTRAINTS;

-- 시퀀스 삭제
DROP SEQUENCE membership_member_id_seq;
DROP SEQUENCE coffeeshop_shop_id_seq;
DROP SEQUENCE review_review_id_seq;
DROP SEQUENCE hashtag_id_seq;
DROP SEQUENCE hashtag_cmenu_id_seq;
DROP SEQUENCE hashtag_dmenu_id_seq;
DROP SEQUENCE hashtag_bmenu_id_seq;
DROP SEQUENCE hashtag_vmenu_id_seq;
DROP SEQUENCE hashtag_gmenu_id_seq;
DROP SEQUENCE hashtag_loc_id_seq;
DROP SEQUENCE hashtag_mood_id_seq;
DROP SEQUENCE hashtag_view_id_seq;
DROP SEQUENCE hashtag_etc_id_seq;
DROP SEQUENCE uploadfile_uploadfile_id_seq;

-- 시퀀스 생성
 create sequence membership_member_id_seq
    start with 1
    increment by 1
    minvalue 0
    maxvalue 99999999
    nocycle
    nocache;

 create sequence coffeeshop_shop_id_seq
    start with 1
    increment by 1
    minvalue 0
    maxvalue 99999999
    nocycle
    nocache;

 create sequence review_review_id_seq
    start with 1
    increment by 1
    minvalue 0
    maxvalue 99999999
    nocycle
    nocache;
    
 create sequence hashtag_id_seq
    start with 1
    increment by 1
    minvalue 0
    maxvalue 99999999
    nocycle
    nocache;    

 create sequence hashtag_cmenu_id_seq
    start with 1001
    increment by 1
    minvalue 1000
    maxvalue 1200
    nocycle
    nocache;

    create sequence hashtag_bmenu_id_seq
    start with 1201
    increment by 1
    minvalue 1200
    maxvalue 1400
    nocycle
    nocache;

 create sequence hashtag_dmenu_id_seq
    start with 1401
    increment by 1
    minvalue 1400
    maxvalue 1700
    nocycle
    nocache;

 create sequence hashtag_gmenu_id_seq
    start with 1701
    increment by 1
    minvalue 1700
    maxvalue 2000
    nocycle
    nocache;

 create sequence hashtag_loc_id_seq
    start with 2001
    increment by 1
    minvalue 2000
    maxvalue 3000
    nocycle
    nocache;

 create sequence hashtag_mood_id_seq
    start with 3001
    increment by 1
    minvalue 3000
    maxvalue 4000
    nocycle
    nocache;

 create sequence hashtag_view_id_seq
    start with 4001
    increment by 1
    minvalue 4000
    maxvalue 5000
    nocycle
    nocache;

 create sequence hashtag_etc_id_seq
    start with 5001
    increment by 1
    minvalue 5000
    maxvalue 6000
    nocycle
    nocache;

-- 테이블 생성
CREATE TABLE bookmark (
    bookmark_chkdate TIMESTAMP DEFAULT systimestamp NOT NULL,
    member_id        NUMBER(5) NOT NULL,
    shop_id          NUMBER(5) NOT NULL
);

COMMENT ON COLUMN bookmark.bookmark_chkdate IS
    '북마크 체크 일자';

COMMENT ON COLUMN bookmark.member_id IS
    '멤버 식별 ID';

COMMENT ON COLUMN bookmark.shop_id IS
    '커피숍 ID';

CREATE TABLE coffeeshop (
    shop_id             NUMBER(5) NOT NULL,
    shop_name           NVARCHAR2(30) NOT NULL,
    shop_address        VARCHAR2(105) NOT NULL,
    shop_tel            VARCHAR2(15) NOT NULL,
    view_count          NUMBER(5) DEFAULT 0,
    shop_bookmark_count NUMBER(5) DEFAULT 0,
    shop_review_count   NUMBER(5) DEFAULT 0,
    parking             VARCHAR2(10) DEFAULT '아니오' NOT NULL,
    allday              VARCHAR2(10) DEFAULT '아니오' NOT NULL,
    shop_cdate          TIMESTAMP DEFAULT systimestamp NOT NULL,
    bcategoryB0101      VARCHAR2(20),       --사진 분류카테고리
    bcategoryB0102      VARCHAR2(20),       --사진 분류카테고리
    bcategoryB0103      VARCHAR2(20),       --사진 분류카테고리
    bcategoryB0104      VARCHAR2(20),       --사진 분류카테고리
    bcategoryB0105      VARCHAR2(20),       --커피숍 대표 사진
    hcategoryH0101      VARCHAR2(60),       --커피숍 대표 해시태그1
    hcategoryH0102      VARCHAR2(60),       --커피숍 대표 해시태그2
    hcategoryH0103      VARCHAR2(60),       --커피숍 대표 해시태그3
    hcategoryH0104      VARCHAR2(60)        --커피숍 대표 해시태그4
);

ALTER TABLE coffeeshop
    ADD CHECK ( parking IN ( '예', '아니오' ) );

ALTER TABLE coffeeshop
    ADD CHECK ( allday IN ( '예', '아니오' ) );

COMMENT ON COLUMN coffeeshop.shop_id IS
    '커피숍 ID';

COMMENT ON COLUMN coffeeshop.shop_name IS
    '커피숍 이름';

COMMENT ON COLUMN coffeeshop.shop_address IS
    '커피숍 주소';

COMMENT ON COLUMN coffeeshop.shop_tel IS
    '커피숍 전화번호 (''-'' 포함)';

COMMENT ON COLUMN coffeeshop.view_count IS
    '커피숍 조회 카운트';

COMMENT ON COLUMN coffeeshop.shop_bookmark_count IS
    '북마크 등록 카운트';

COMMENT ON COLUMN coffeeshop.shop_review_count IS
    '등록된 리뷰 개수 카운트';

COMMENT ON COLUMN coffeeshop.parking IS
    '주차시설 여부';

COMMENT ON COLUMN coffeeshop.allday IS
    '24시간 운영 여부';

COMMENT ON COLUMN coffeeshop.shop_cdate IS
    '커피숍 등록 일자';

ALTER TABLE coffeeshop ADD CONSTRAINT coffeeshop_pk PRIMARY KEY ( shop_id );

CREATE TABLE hashtag_classification (
    hashtag_numbering       NUMBER(10) NOT NULL,
    hashtag_id              NUMBER(5) NOT NULL,
    hashtag_name            VARCHAR2(100) NOT NULL,
    hashtag_search_count    NUMBER(5) DEFAULT 0 NOT NULL
);

COMMENT ON COLUMN hashtag_classification.hashtag_id IS
    '해시태그 ID';

COMMENT ON COLUMN hashtag_classification.hashtag_name IS
    '해시태그 이름';

COMMENT ON COLUMN hashtag_classification.hashtag_search_count IS
    '해시태그 검색에 사용된 횟수';

ALTER TABLE hashtag_classification ADD CONSTRAINT hashtag_classification_pk PRIMARY KEY ( hashtag_id );

CREATE TABLE membership (
    member_id     NUMBER(5) NOT NULL,
    member_email  VARCHAR2(40) NOT NULL,
    member_passwd VARCHAR2(20) NOT NULL,
    member_name   VARCHAR2(15) NOT NULL,
    member_gender CHAR(3) NOT NULL,
    member_age    NUMBER(8) NOT NULL,
    member_tel    VARCHAR2(15) NOT NULL
);

ALTER TABLE membership
    ADD CHECK ( member_gender IN ( '남', '여' ) );

COMMENT ON COLUMN membership.member_id IS
    '멤버 식별 ID';

COMMENT ON COLUMN membership.member_email IS
    '멤버 이메일';

COMMENT ON COLUMN membership.member_passwd IS
    '멤버 비밀번호';

COMMENT ON COLUMN membership.member_name IS
    '멤버 이름';

COMMENT ON COLUMN membership.member_gender IS
    '멤버 성별';

COMMENT ON COLUMN membership.member_age IS
    '멤버 생년월일';

COMMENT ON COLUMN membership.member_tel IS
    '멤버 연락처 (''-'' 제외)';


ALTER TABLE membership ADD CONSTRAINT membership_pk PRIMARY KEY ( member_id );

ALTER TABLE membership ADD CONSTRAINT membership_email_uk UNIQUE ( member_email );

ALTER TABLE membership ADD CONSTRAINT membership_tel_uk UNIQUE ( member_tel );

CREATE TABLE report (
    review_id        NUMBER(5) NOT NULL,
    report_type_code NUMBER(1) NOT NULL
);

COMMENT ON COLUMN report.review_id IS
    '리뷰 식별 ID';

COMMENT ON COLUMN report.report_type_code IS
    '신고 사유 코드
0=스팸(부적절한 홍보), 1=음란물, 2=혐오표현, 3=욕설 및 비방';

CREATE TABLE report_type (
    report_type_code NUMBER(1) NOT NULL,
    report_type_name VARCHAR2(30) NOT NULL
);

COMMENT ON COLUMN report_type.report_type_code IS
    '신고 사유 코드
0=스팸(부적절한 홍보), 1=음란물, 2=혐오표현, 3=욕설 및 비방';

ALTER TABLE report_type ADD CONSTRAINT reporttype_code_pk PRIMARY KEY ( report_type_code );

CREATE TABLE review (
    review_id      NUMBER(5) NOT NULL,
    review_content CLOB NOT NULL,
    review_cdate   TIMESTAMP DEFAULT systimestamp NOT NULL,
    report_count   NUMBER(3) DEFAULT 0,
    member_id      NUMBER(5) NOT NULL,
    shop_id        NUMBER(5) NOT NULL
);

COMMENT ON COLUMN review.review_id IS
    '리뷰 식별 ID';

COMMENT ON COLUMN review.review_content IS
    '리뷰 내용';

COMMENT ON COLUMN review.review_cdate IS
    '리뷰 작성 일자';

COMMENT ON COLUMN review.report_count IS
    '리뷰의 신고된 횟수';

COMMENT ON COLUMN review.member_id IS
    '멤버 식별 ID';

COMMENT ON COLUMN review.shop_id IS
    '커피숍 ID';

ALTER TABLE review ADD CONSTRAINT review_pk PRIMARY KEY ( review_id );

CREATE TABLE shop_hashtag (
    shop_id    NUMBER(5) NOT NULL,
    hashtag_id NUMBER(5) NOT NULL
);

COMMENT ON COLUMN shop_hashtag.shop_id IS
    '커피숍 ID';

COMMENT ON COLUMN shop_hashtag.hashtag_id IS
    '해시태그 ID';

ALTER TABLE bookmark
    ADD CONSTRAINT bookmark_coffeeshop_fk FOREIGN KEY ( shop_id )
        REFERENCES coffeeshop ( shop_id ) ON DELETE CASCADE;

ALTER TABLE bookmark
    ADD CONSTRAINT bookmark_membership_fk FOREIGN KEY ( member_id )
        REFERENCES membership ( member_id ) ON DELETE CASCADE;

ALTER TABLE report
    ADD CONSTRAINT report_reporttype_code_fk FOREIGN KEY ( report_type_code )
        REFERENCES report_type ( report_type_code ) ON DELETE CASCADE;

ALTER TABLE report
    ADD CONSTRAINT report_review_fk FOREIGN KEY ( review_id )
        REFERENCES review ( review_id ) ON DELETE CASCADE;

ALTER TABLE review
    ADD CONSTRAINT review_coffeeshop_fk FOREIGN KEY ( shop_id )
        REFERENCES coffeeshop ( shop_id ) ON DELETE CASCADE;

ALTER TABLE review
    ADD CONSTRAINT review_membership_fk FOREIGN KEY ( member_id )
        REFERENCES membership ( member_id ) ON DELETE CASCADE;

ALTER TABLE shop_hashtag
    ADD CONSTRAINT shop_hashtag_classification_fk FOREIGN KEY ( hashtag_id )
        REFERENCES hashtag_classification ( hashtag_id ) ON DELETE CASCADE;

ALTER TABLE shop_hashtag
    ADD CONSTRAINT shop_hashtag_coffeeshop_fk FOREIGN KEY ( shop_id )
        REFERENCES coffeeshop ( shop_id ) ON DELETE CASCADE;

-----------------------------------------------------------------------------
-- 첨부파일 관련 테이블 --
-------
--코드
-------
create table code(
    code_id     varchar2(11),                     --코드
    decode      varchar2(30) NOT NULL,            --코드명
    discript    clob,                             --코드설명
    pcode_id    varchar2(11),                     --상위코드
    useyn       char(1) default 'Y' NOT NULL,     --사용여부 (사용:'Y',미사용:'N')
    cdate       timestamp default systimestamp,   --생성일시
    udate       timestamp default systimestamp    --수정일시
);
--기본키
alter table code add Constraint code_code_id_pk primary key (code_id);

--외래키
alter table code add constraint bbs_pcode_id_fk
    foreign key(pcode_id) references code(code_id) ON DELETE CASCADE;

--제약조건
alter table code add constraint code_useyn_ck check(useyn in ('Y','N'));

--샘플데이터 of code
insert into code (code_id,decode,pcode_id,useyn) values ('B01','커피숍',null,'Y');
insert into code (code_id,decode,pcode_id,useyn) values ('B0101','메인사진','B01','Y');
insert into code (code_id,decode,pcode_id,useyn) values ('B0102','음료','B01','Y');
insert into code (code_id,decode,pcode_id,useyn) values ('B0103','디저트','B01','Y');
insert into code (code_id,decode,pcode_id,useyn) values ('B0104','굿즈','B01','Y');
insert into code (code_id,decode,pcode_id,useyn) values ('B0105','썸네일','B01','Y');

---------
--첨부파일
---------
create table uploadfile(
    uploadfile_id   number(10),                     --파일아이디
    code            varchar2(11) NOT NULL,          --분류코드
    rid             varchar2(10) NOT NULL,          --참조번호(게시글번호등)
    store_filename  varchar2(100) NOT NULL,         --서버보관파일명
    upload_filename varchar2(100) NOT NULL,         --업로드파일명(유저가 업로드한파일명)
    fsize           varchar2(45) NOT NULL,          --업로드파일크기(단위byte)
    ftype           varchar2(100) NOT NULL,         --파일유형(mimetype)
    cdate           timestamp default systimestamp, --등록일시
    udate           timestamp default systimestamp --수정일시
);
--기본키
alter table uploadfile add constraint uploadfile_uploadfile_id_pk primary key(uploadfile_id);

--외래키
alter table uploadfile add constraint uploadfile_uploadfile_id_fk
    foreign key(code) references code(code_id);

--시퀀스
create sequence uploadfile_uploadfile_id_seq
nocycle
nocache;

--제약조건
alter table coffeeshop add constraint coffeeshop_bcategoryB0101_fk
    foreign key(bcategoryB0101) references code(code_id) ON DELETE CASCADE;

alter table coffeeshop add constraint coffeeshop_bcategoryB0102_fk
    foreign key(bcategoryB0102) references code(code_id) ON DELETE CASCADE;

alter table coffeeshop add constraint coffeeshop_bcategoryB0103_fk
    foreign key(bcategoryB0103) references code(code_id) ON DELETE CASCADE;

alter table coffeeshop add constraint coffeeshop_bcategoryB0104_fk
    foreign key(bcategoryB0104) references code(code_id) ON DELETE CASCADE;

commit;
