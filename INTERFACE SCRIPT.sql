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