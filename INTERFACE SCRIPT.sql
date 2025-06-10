-- --------------------------------------------------------
-- Table: INTERFACE_LOCK_MASTER
-- --------------------------------------------------------

CREATE TABLE "INTERFACE_LOCK_MASTER" 
   (	"INTERFACE_TYPE" VARCHAR2(255 CHAR), 
	"LOCKED" NUMBER(1,0), 
	"LOCKED_DATE" DATE, 
	"UNLOCKED_DATE" DATE, 
	"CURRENT_SERVER" VARCHAR2(255 CHAR), 
	"EXEC_DELAY" NUMBER, 
	"MAX_LOCK" NUMBER  
   );

CREATE UNIQUE INDEX "INTERFACE_LOCK_MASTER_PK" ON "INTERFACE_LOCK_MASTER" ("INTERFACE_TYPE");

INSERT INTO INTERFACE_LOCK_MASTER (INTERFACE_TYPE,LOCKED,LOCKED_DATE,UNLOCKED_DATE,CURRENT_SERVER,EXEC_DELAY,MAX_LOCK) 
VALUES ('I01',0,null,null, null,15,7200);

INSERT INTO INTERFACE_LOCK_MASTER (INTERFACE_TYPE,LOCKED,LOCKED_DATE,UNLOCKED_DATE,CURRENT_SERVER,EXEC_DELAY,MAX_LOCK) 
VALUES ('I02',0,null,null, null,15,7200);

INSERT INTO INTERFACE_LOCK_MASTER (INTERFACE_TYPE,LOCKED,LOCKED_DATE,UNLOCKED_DATE,CURRENT_SERVER,EXEC_DELAY,MAX_LOCK) 
VALUES ('I03',0,null,null, null,15,7200);

INSERT INTO INTERFACE_LOCK_MASTER (INTERFACE_TYPE,LOCKED,LOCKED_DATE,UNLOCKED_DATE,CURRENT_SERVER,EXEC_DELAY,MAX_LOCK) 
VALUES ('I31',0,null,null, null,15,600);

-- --------------------------------------------------------
-- Table: OPS_LINE_EMAIL_MASTER
-- --------------------------------------------------------

CREATE TABLE ops_line_email_master (
    ops_line    VARCHAR2(10 CHAR),
    email       VARCHAR2(120 CHAR),
    site        VARCHAR2(10 BYTE),
    cost_centre VARCHAR2(10 BYTE)
)
SEGMENT CREATION IMMEDIATE
PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING;

CREATE UNIQUE INDEX ops_line_email_master_pk ON
    ops_line_email_master (
        ops_line,
        email
    )
        PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS;

ALTER TABLE ops_line_email_master
    ADD CONSTRAINT ops_line_email_master_pk PRIMARY KEY ( ops_line,
                                                          email )
        USING INDEX ops_line_email_master_pk
    ENABLE;

-- --------------------------------------------------------
-- Table: EMPLOYEE_SCHEDULE
-- --------------------------------------------------------

ALTER TABLE EMPLOYEE_SCHEDULE 
ADD BMM_INTERFACE_DATE DATE;

ALTER TABLE EMPLOYEE_SCHEDULE 
ADD BMM_INTERFACE_FLAG VARCHAR2(1 CHAR);

-- --------------------------------------------------------
-- Table: SHIFT_PATTERN
-- --------------------------------------------------------

ALTER TABLE SHIFT_PATTERN
ADD BMM_INTERFACE_DATE DATE;

ALTER TABLE SHIFT_PATTERN 
ADD BMM_INTERFACE_FLAG VARCHAR2(1 CHAR);

-- --------------------------------------------------------
-- Table: DAILY_SHIFT_PATTERN
-- --------------------------------------------------------

ALTER TABLE DAILY_SHIFT_PATTERN 
ADD BMM_INTERFACE_DATE DATE;

ALTER TABLE DAILY_SHIFT_PATTERN 
ADD BMM_INTERFACE_FLAG VARCHAR2(1 CHAR);