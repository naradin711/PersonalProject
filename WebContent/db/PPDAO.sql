--for the Personal Project
--------------------------------------------------------------------
                        -- CUSTOMER TABLE
--------------------------------------------------------------------
DROP TABLE CUSTOMER_SHOP;
CREATE TABLE CUSTOMER_SHOP (
            cid VARCHAR2(30) PRIMARY KEY,
            cpw VARCHAR2(30) NOT NULL,
            cname VARCHAR2(30) NOT NULL,
            cemail VARCHAR2(30) NOT NULL UNIQUE,
            ctel VARCHAR2(30) NOT NULL UNIQUE,
            caddress VARCHAR2(30) NOT NULL,
            cbirth DATE,
            crdate DATE DEFAULT SYSDATE
        );
select * from customer_shop;

--0. 로그인
SELECT * FROM customer_shop WHERE CID = 'aaa' and CPW = '111';

--1. ID중복체크
SELECT * FROM customer_shop WHERE CID = 'aaa';

--1. 1. 이메일중복체크
SELECT * FROM customer_shop WHERE CEMAIL = 'honghong13@hong.com';
--1. 2. 폰번호중복체크
SELECT * FROM customer_shop WHERE CTEL = '010-9999-9999';
 
--2. 회원가입        
INSERT INTO customer_shop (CID, CPW, CNAME, CEMAIL, CTEL, CADDRESS, CBIRTH)
        VALUES ('aaa', '111', '홍길동', 'honghong11@hong.com', '010-9999-9999', 
                '서울시 종로구 무악동', '1992-02-19');
INSERT INTO customer_shop (CID, CPW, CNAME, CEMAIL, CTEL, CADDRESS, CBIRTH)
        VALUES ('bbb', '111', '비길동', 'honghong12@hong.com', '010-2222-2222', 
                '서울시 종로구 창천동', '1992-02-22');
INSERT INTO customer_shop (CID, CPW, CNAME, CEMAIL, CTEL, CADDRESS, CBIRTH)
        VALUES ('eee', '111', '라길동', 'honghong15@hong.com', '010-2555-2555', 
                '서울시 종로구 오전동', '1992-05-30'); 
                
                
-- 2. 1. 회원 수 세보기

SELECT COUNT(*) CNT FROM CUSTOMER_SHOP;


-- 2. 2. 회원 목록 보기 페이징하기
SELECT * FROM 
        (SELECT ROWNUM RN, A.* FROM 
        (SELECT * FROM CUSTOMER_SHOP ORDER BY crdate DESC)A )
         WHERE RN BETWEEN 1 AND 9;
         
-- 2. 3. 회원정보 상세보기 (cid로 dto 출력)         
SELECT * FROM CUSTOMER_SHOP WHERE CID= 'aaa';   

-- 3. 회원 정보 수정

update customer_shop set cpw = '111',
                        CNAME = '씨길동',
                        CEMAIL = 'honghong13@hong.com',
                        CTEL = '010-2333-2333',
                        CADDRESS = '서울시 종로구 사전동' ,
                        CBIRTH = '1993-03-03'
                        WHERE CID = 'ccc';

-- 4. 회원 탈퇴

DELETE CUSTOMER_SHOP WHERE CID = 'bbb';
                
                
--------------------------------------------------------------------
                        -- ADMIN TABLE
--------------------------------------------------------------------    
DROP TABLE ADMIN CASCADE CONSTRAINTS;
CREATE TABLE ADMIN(
    aid VARCHAR2(30)    PRIMARY KEY,
    apw VARCHAR2(30)    NOT NULL,
    aname VARCHAR2(30)  NOT NULL);
    
select * from admin; 
-- (1) admin loginCheck
SELECT * FROM ADMIN WHERE AID='admin' AND APW='111';
-- (2) 로그인 후 세션에 넣을 용도 : admin aid로 dto 가져오기
SELECT * FROM ADMIN WHERE AID='admin';


--------------------------------------------------------------------
                        -- PRODUCT TABLE
--------------------------------------------------------------------  

DROP TABLE PRODUCT CASCADE CONSTRAINTS;
DROP SEQUENCE PRODUCT_SEQ;
CREATE  SEQUENCE PRODUCT_SEQ MAXVALUE 999999 NOCACHE NOCYCLE;

CREATE TABLE PRODUCT (
        pid NUMBER(10) PRIMARY KEY,
        pname VARCHAR2(30) NOT NULL,
        ptype VARCHAR2(30) NOT NULL,
        pcontent VARCHAR2(3000) NOT NULL,
        pphoto VARCHAR2(300) NOT NULL,
        pprice NUMBER(10) NOT NULL,
        phit NUMBER(10) DEFAULT 0 ,
        prdate DATE DEFAULT SYSDATE
        );
        
SELECT * FROM product;
COMMIT;

-- 0. 상품 출력
SELECT * FROM
 (SELECT ROWNUM RN, A.* FROM
 (select P.* from PRODUCT P ORDER BY prdate DESC )A )
  WHERE RN BETWEEN 1 AND 50;

-- 0. 1. 상품 갯수 세기
SELECT COUNT(*) CNT FROM PRODUCT;
        
-- 1. 상품등록        
INSERT INTO PRODUCT (PID, PNAME, PTYPE, pcontent, pphoto, pprice )
        VALUES (PRODUCT_SEQ.nextval , 'WHITE DRESS' , 'DRESS' , 
                '이것은 하얀색 원피스' , 'NOIMG.JPG' , 50000 );
        INSERT INTO PRODUCT (PID, PNAME, PTYPE, pcontent, pphoto, pprice )
        VALUES (PRODUCT_SEQ.nextval , 'WHITE TOP' , 'TOP' , 
                '이것은 하얀색 상의' , 'NOIMG.JPG' , 45000 );
        INSERT INTO PRODUCT (PID, PNAME, PTYPE, pcontent, pphoto, pprice )
        VALUES (PRODUCT_SEQ.nextval , 'RED DRESS' , 'DRESS' , 
                '이것은 빨간색 원피스' , 'NOIMG.JPG' , 35000 );
        INSERT INTO PRODUCT (PID, PNAME, PTYPE, pcontent, pphoto, pprice )
        VALUES (PRODUCT_SEQ.nextval , 'BLUE TOP', 'TOP', 
                '이것은 파란색 상의입니다.', 'NOIMG.JPG', 43000 );
        INSERT INTO PRODUCT (PID, PNAME, PTYPE, pcontent, pphoto, pprice )
        VALUES (PRODUCT_SEQ.nextval , 'GRAY TOP', 'TOP', 
                '이것은 회색 상의입니다.', 'NOIMG.JPG', 60000 );
        INSERT INTO PRODUCT (PID, PNAME, PTYPE, pcontent, pphoto, pprice )
        VALUES (PRODUCT_SEQ.nextval , 'SILVER PANTS', 'BOT', 
                '이것은 은색 바지입니다.', 'NOIMG.JPG', 95000 );
        INSERT INTO PRODUCT (PID, PNAME, PTYPE, pcontent, pphoto, pprice )
        VALUES (PRODUCT_SEQ.nextval , 'GOLD SKIRT', 'BOT', 
                '이것은 금빛 치마입니다.', 'NOIMG.JPG', 88000 );         
                
-- 2. 상품 상세 조회 (PID로 DTO 출력)

SELECT * FROM PRODUCT WHERE PID = 3;

-- 2-1. 상품 조회수 올리기
UPDATE PRODUCT SET PHIT = PHIT + 39 WHERE PID = 39;
commit;

-- 3-1 . 상품 목록 출력 이름 검색
Select * from PRODUCT where PNAME like '%' || upper( 'WH' ) || '%';

SELECT * FROM
 (SELECT ROWNUM RN, A.* FROM
 (select P.* from PRODUCT P WHERE PNAME like '%' || upper( 'WH' ) || '%' 
                            ORDER BY prdate DESC )A )
                            WHERE RN BETWEEN 1 AND 5;

-- 3-2 . 상품 목록 출력 가격 높은순

SELECT * FROM PRODUCT ORDER BY PPRICE DESC;

SELECT * FROM
 (SELECT ROWNUM RN, A.* FROM
 (select P.* from PRODUCT P ORDER BY PPRICE DESC )A )
                            WHERE RN BETWEEN 1 AND 5;

-- 3-3 . 상품 목록 출력 가격 낮은순
SELECT * FROM PRODUCT ORDER BY PPRICE ;
SELECT * FROM
 (SELECT ROWNUM RN, A.* FROM
 (select P.* from PRODUCT P ORDER BY PPRICE )A )
                            WHERE RN BETWEEN 1 AND 5;                           

-- 3-4 . 상품 목록 최신순
SELECT * FROM
 (SELECT ROWNUM RN, A.* FROM
 (select P.* from PRODUCT P ORDER BY prdate DESC )A )
  WHERE RN BETWEEN 1 AND 5;

-- 3-5 . 상품 목록 인기순
SELECT * FROM PRODUCT ORDER BY PHIT DESC;
SELECT * FROM
 (SELECT ROWNUM RN, A.* FROM
 (select P.* from PRODUCT P ORDER BY PHIT DESC )A )
  WHERE RN BETWEEN 1 AND 12;

-- 3-6 . 상품 목록 종류별 (기본 - 인기순)
SELECT * FROM PRODUCT WHERE PTYPE='DRESS' ORDER BY PHIT DESC;
SELECT * FROM
 (SELECT ROWNUM RN, A.* FROM
 (select P.* from PRODUCT P WHERE PTYPE='DRESS'
                            ORDER BY PHIT DESC )A )
  WHERE RN BETWEEN 1 AND 5;

-- 4. 상품 정보 수정
UPDATE PRODUCT SET  PNAME = 'BLUE TOP',
                    PTYPE = 'TOP',
                    PCONTENT = '이것은 파란색 상의입니다.',
                    PPHOTO = 'NOIMG.JPG',
                    PPRICE = 43000
                    WHERE PID = 2;
-- 5. 상품 삭제

DELETE PRODUCT WHERE PID = 4;

COMMIT;
--------------------------------------------------------------------
                        -- FREEBOARD TABLE
--------------------------------------------------------------------
DROP TABLE FREEBOARD_SHOP;
DROP SEQUENCE FBSHOP_SEQ;
CREATE SEQUENCE FBSHOP_SEQ MAXVALUE 999999 NOCYCLE NOCACHE;
CREATE TABLE FREEBOARD_SHOP (
                fbid    NUMBER(10)       PRIMARY KEY,
                CID     VARCHAR2(30)     REFERENCES CUSTOMER_SHOP(CID),
                aname   VARCHAR2(30)     ,
                fbtitle VARCHAR2(30)     NOT NULL,
                fbcontent VARCHAR2(3000) NOT NULL,
                fbphoto VARCHAR2(300),
                fbrdate DATE DEFAULT SYSDATE,
                fbip    VARCHAR2(30) NOT NULL, 
                fbgroup NUMBER(10)   NOT NULL,
                fbstep  NUMBER(10)   NOT NULL,
                fbindent NUMBER(10)  NOT NULL,
                fbpw    VARCHAR2(3000) NOT NULL
            );
            SELECT * from FREEBOARD_SHOP;
            COMMIT;
 
-- 1. 글 출력 (int startRow, int endRow)
SELECT ROWNUM RN, A.* FROM
(select F.*  from FREEBOARD_SHOP F, CUSTOMER_SHOP C 
                 WHERE F.CID = C.CID
                ORDER BY FBGROUP DESC, fbstep) A ;
                commit;
           select * from FREEBOARD_SHOP;
SELECT * FROM
    (SELECT ROWNUM RN, A.* FROM
    (select F.* from FREEBOARD_SHOP F, CUSTOMER_SHOP C 
                 WHERE F.CID = C.CID
                ORDER BY FBGROUP DESC, fbstep )A )
     WHERE RN BETWEEN 5 AND 11; 

            
-- 2. 글 갯수 세기
SELECT COUNT(*)CNT FROM FREEBOARD_SHOP;

-- 3. 글 작성하기. (고객 원글)
INSERT INTO FREEBOARD_SHOP (fbid, cID, aname, fbtitle, fbcontent,  
        fbphoto, fbip, fbgroup, fbstep, fbindent, fbpw)
VALUES (FBSHOP_SEQ.NEXTVAL, 'aaa', NULL,'title220706','content0706001', 
        'NOIMG.JPG', '192.168.10.151', FBSHOP_SEQ.CURRVAL, 0, 0, '111');

-- 4. FBId로 글 dto보기 (글쓴이 이름 추가)
SELECT F.* from FREEBOARD_SHOP F, CUSTOMER_SHOP C 
           WHERE F.CID = C.CID AND FBID=1;

-- 6. 글 수정 (FBID, fBtitle, FBContent, FBPHOTO,  FIp)
UPDATE FREEBOARD_SHOP SET FBTITLE = '바뀐제목3',
                            FBCONTENT = '바뀐 본문',
                            fBPHOTO = 'NOIMG.JPG',
                            FBIP = '111.168.151.11'
                      WHERE FBID = 3 AND FBPW = '111'; 
                      
-- (7) 글 삭제하기(FBID, FBPW로 삭제하기)
COMMIT;
DELETE FROM FREEBOARD_SHOP WHERE FBID=27 AND FBPW = '222';
ROLLBACK;                      
                      
-- (9) 답변글 쓰기 (관리자 ONLY)

INSERT INTO FREEBOARD_SHOP (fbid, cID, aname, fbtitle, fbcontent,  
        fbphoto, fbip, fbgroup, fbstep, fbindent, fbpw)
VALUES (FBSHOP_SEQ.NEXTVAL, 'aaa', '관리자','답) title220702','답) content23', 
        'NOIMG.JPG', '192.168.10.151', 3, 1, 1, '111');
            

--------------------------------------------------------------------
                        -- REVIEW TABLE
--------------------------------------------------------------------
DROP TABLE REVIEW;
DROP SEQUENCE REVIEW_SEQ;
CREATE SEQUENCE REVIEW_SEQ MAXVALUE 999999 NOCYCLE NOCACHE;
CREATE TABLE REVIEW (
                rbid      NUMBER(10)       PRIMARY KEY,
                CID       VARCHAR2(30)     REFERENCES CUSTOMER_SHOP(CID),
                pid       NUMBER(10)       REFERENCES PRODUCT(PID),
                rbtitle   VARCHAR2(300)    NOT NULL,
                rbcontent VARCHAR2(3000)   NOT NULL,
                rbphoto   VARCHAR2(300),
                rbrdate   DATE             DEFAULT SYSDATE,
                rbip      VARCHAR2(30)     NOT NULL,
                rbpw      VARCHAR2(300)    NOT NULL
            );

-- 1. 글 출력 (int startRow, int endRow)
SELECT ROWNUM RN, A.* FROM
                (select R.*  from REVIEW R, CUSTOMER_SHOP C, product p
                WHERE R.CID = C.CID AND R.PID = P.PID
                ORDER BY rbrdate DESC) A ;
                
SELECT * FROM
            (SELECT ROWNUM RN, A.* FROM
            (select R.*  from REVIEW R, CUSTOMER_SHOP C, product p
            WHERE R.CID = C.CID AND R.PID = P.PID AND R.PID = 20
            ORDER BY rbrdate DESC)A )
            WHERE RN BETWEEN 1 AND 50; 
     
     COMMIT;

            
-- 2. 글 갯수 세기
SELECT COUNT(*)CNT FROM REVIEW;

-- 3. 글 작성하기. (고객 원글)
INSERT INTO REVIEW (Rbid, cID, pid, Rbtitle, Rbcontent, Rbphoto, Rbip, Rbpw)
VALUES (REVIEW_SEQ.NEXTVAL, 'ccc', 20 , '꽃무늬 상의 220707','REVIEW005', 
        'NOIMG.JPG', '192.168.10.151', '111'); 

-- 4. RBId로 글 dto보기 (글쓴이 이름 추가)
SELECT R.* from REVIEW R, CUSTOMER_SHOP C, product p
           WHERE R.CID = C.CID AND R.PID = P.PID AND RBID=1;

-- 6. 리뷰 수정 (RBID, RBtitle, RBContent, RBPHOTO,  RIp)
UPDATE REVIEW SET RBTITLE = '바뀐 리뷰 제목 3',
                    RBCONTENT = '바뀐 리뷰 본문 33',
                    RBPHOTO = 'NOIMG.JPG',
                    RBIP = '103.133.133.33'
                WHERE RBID = 50 and RBPW = 111; 
                      
-- (7) 리뷰 삭제하기(RBID, RBPW로 삭제하기)
COMMIT;
DELETE FROM REVIEW WHERE RBID=3 AND RBPW = '111';
ROLLBACK;

--------------------------------------------------------------------
                        -- CART TABLE
--------------------------------------------------------------------
DROP TABLE CART CASCADE CONSTRAINTS;
DROP SEQUENCE CART_SEQ;
CREATE SEQUENCE CART_SEQ MAXVALUE 999999 NOCYCLE NOCACHE;
CREATE TABLE CART (
                cartid      NUMBER(10)      PRIMARY KEY,
                CID         VARCHAR2(30)    REFERENCES CUSTOMER_SHOP(CID),
                PID         NUMBER(10)      REFERENCES PRODUCT(PID),
                pname       VARCHAR2(30)    NOT NULL,
                pphoto      VARCHAR2(300)   ,
                pprice      NUMBER(10)      NOT NULL
                 );
-- 1. 글 출력 (int startRow, int endRow)
SELECT ROWNUM RN, A.* FROM
(select CT.*  from CART CT, CUSTOMER_SHOP C , PRODUCT PR
                 WHERE CT.CID = C.CID AND CT.PID = PR.PID
                ORDER BY CT.CARTID) A ;
                
SELECT * FROM
    (SELECT ROWNUM RN, A.* FROM
    (select CT.* from CART CT, CUSTOMER_SHOP C
                 WHERE CT.CID = C.CID ORDER BY CT.CARTID DESC)A )
     WHERE RN BETWEEN 1 AND 10; 

select * from cart order by cartid desc;
     
     COMMIT;

            
-- 2. 글 갯수 세기
SELECT COUNT(*)CNT FROM CART;

-- 3. 장바구니 추가. (고객 입장)
INSERT INTO CART (CARTid, cID, PID, pname, pphoto, pprice)
VALUES (CART_SEQ.NEXTVAL, 'aaa', 44, 'BLACK HARRY POTTER', 'dress_harrypotter.jpg', 405000);
INSERT INTO CART (CARTid, cID, PID, pname, pphoto, pprice)
VALUES (CART_SEQ.NEXTVAL, 'bbb', 44, 'BLACK HARRY POTTER', 'dress_harrypotter.jpg', 405000);
INSERT INTO CART (CARTid, cID, PID, pname, pphoto, pprice)
VALUES (CART_SEQ.NEXTVAL, 'ccc', 44, 'BLACK HARRY POTTER', 'dress_harrypotter.jpg', 405000);


-- 4. CID로 CART dto보기 (개인 장바구니 목록 출력)
select CT.* from CART CT, CUSTOMER_SHOP C
                 WHERE CT.CID = C.CID 
                 AND CT.CID = 'bbb'
                 ORDER BY CT.CARTID DESC;

                      
-- 5 장바구니 목록별 삭제하기(CARTID 로 삭제하기)
COMMIT;
DELETE FROM CART WHERE CARTID=3;
ROLLBACK;

-- 5 -1  담은 장바구니 전체 삭제하기(CID 로 삭제하기). 오더 파트에서 주문 처리할때 쓰임
COMMIT;
DELETE FROM CART WHERE CID= 'ccc';
ROLLBACK;

--------------------------------------------------------------------
                        -- ORDER TABLE
--------------------------------------------------------------------

DROP TABLE ORDERLIST;
DROP SEQUENCE ORDERLIST_SEQ;
CREATE SEQUENCE ORDERLIST_SEQ MAXVALUE 999999 NOCYCLE NOCACHE;
CREATE TABLE ORDERLIST (
                ODID        NUMBER(10)      PRIMARY KEY,
                CID         VARCHAR2(30)    REFERENCES CUSTOMER_SHOP(CID),
                ODtitle     VARCHAR2(300)   NOT NULL,
                ODaddress   VARCHAR2(300)   NOT NULL,
                odprice      NUMBER(10)      NOT NULL,
                ODCALL      VARCHAR2(30)    DEFAULT 'N',
                ODDATE      DATE            DEFAULT SYSDATE
                 );

-- 0. 글 갯수 세기
SELECT COUNT(*)CNT FROM ORDERLIST;

SELECT * FROM ORDERLIST;

delete orderlist where odid = 6;

-- 1. 구매 목록 출력 시간순 (int startRow, int endRow)
                
SELECT * FROM
    (SELECT ROWNUM RN, A.* FROM
    (select OD.* from ORDERLIST OD, CUSTOMER_SHOP C
                 WHERE OD.CID = C.CID ORDER BY ODDATE DESC)A )
     WHERE RN BETWEEN 1 AND 10; 
     
     COMMIT;
-- 1. 1. 구매목록 출력 - 카테고리별
SELECT * FROM
    (SELECT ROWNUM RN, A.* FROM
    (select OD.* from ORDERLIST OD, CUSTOMER_SHOP C
                 WHERE OD.CID = C.CID 
                 AND PTYPE = 'TOP'
                 ORDER BY ODDATE DESC)A )
     WHERE RN BETWEEN 1 AND 3; 
     
-- 1. 2. 구매목록 출력 - 주문 미처리
SELECT * FROM
    (SELECT ROWNUM RN, A.* FROM
    (select OD.* from ORDERLIST OD, CUSTOMER_SHOP C
                 WHERE OD.CID = C.CID 
                 AND ODCALL = 'N'
                 ORDER BY ODDATE DESC)A )
     WHERE RN BETWEEN 1 AND 3; 
            


-- 3. 구매 목록 추가. (고객이 관리자에게)
INSERT INTO ORDERLIST (ODID, cID, ODTITle, ODaddress, odprice)
VALUES (ORDERLIST_SEQ.NEXTVAL, 'bbb', 'bbb 님의 1번 주문', '안남시 금정구', 405000);
INSERT INTO ORDERLIST (ODID, cID, ODTITle, ODaddress, odprice)
VALUES (ORDERLIST_SEQ.NEXTVAL, 'ccc', 'ccc 님의 2번 주문', '안남시 금정구', 225000);
INSERT INTO ORDERLIST (ODID, cID, ODTITle, ODaddress, odprice)
VALUES (ORDERLIST_SEQ.NEXTVAL, 'eee', 'eee 님의 3번 주문', '안남시 금정구', 225000);

-- 4. CID로 ORDERLIST dto보기 (개인 구매 목록 출력)
select OD.* from ORDERLIST OD, CUSTOMER_SHOP C
                 WHERE OD.CID = C.CID 
                 AND OD.CID = 'bbb'
                 ORDER BY OD.ODID DESC; 
                      
-- 5. 구매처리하기 (ORDER CALL을 Y로 업데이트)
COMMIT; 
UPDATE ORDERLIST SET ODtitle = CONCAT( ODtitle, ' - 처리 완료.') ,
                     ODCALL = 'Y'
                 WHERE ODID = 2 ;              
                 
-- 6. ODID로 ORDERLIST dto보기 (개인 구매 목록 출력)
select OD.* from ORDERLIST OD, CUSTOMER_SHOP C
                 WHERE OD.CID = C.CID 
                 AND OD.ODID = 1
                 ORDER BY OD.ODID DESC;                 
ROLLBACK;

-- 7. 제목 바꾸기
UPDATE ORDERLIST SET ODtitle = 'ddd 님의 주문 - 처리 완료.'
                 WHERE ODID = 8 ;  
--------------------------------------------------------------------
                        -- ORDERDETAIL TABLE
--------------------------------------------------------------------

DROP TABLE ORDERDETAIL;
DROP SEQUENCE ORDERDETAIL_SEQ;
CREATE SEQUENCE ORDERDETAIL_SEQ MAXVALUE 999999 NOCYCLE NOCACHE;
CREATE TABLE ORDERDETAIL (
                ODDID       NUMBER(10)      PRIMARY KEY,
                CID         VARCHAR2(30)    REFERENCES CUSTOMER_SHOP(CID),
                PID         NUMBER(10)      REFERENCES PRODUCT(PID),
                pname       VARCHAR2(300)   NOT NULL,
                pprice      NUMBER(10)      NOT NULL
                 );

-- 0. 글 갯수 세기
SELECT COUNT(*)CNT FROM ORDERDETAIL;

-- 1.  구매목록 출력 - 아이디별
SELECT * FROM
    (SELECT ROWNUM RN, A.* FROM
    (select ODD.* from ORDERDETAIL ODD, CUSTOMER_SHOP C
                 WHERE ODD.CID = C.CID AND ODD.cid = 'aaa'
                 ORDER BY ODDID DESC)A )
     WHERE RN BETWEEN 1 AND 10; 
     
     commit;

-- 2. 구매 상세 추가

INSERT INTO ORDERDETAIL (ODDID, cid, pid, pname, pprice)
select ORDERDETAIL_SEQ.nextval ,c.cid, c.pid, c.pname, c.pprice from CART c where c.cid = 'bbb';









