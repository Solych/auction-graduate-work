-- CREATE SCHEMA auction;
CREATE TABLE auction.BUYER (
  BUYER_ID INT AUTO_INCREMENT,
  MAIL     VARCHAR(30) UNIQUE NOT NULL,
  NICKNAME VARCHAR(30) UNIQUE NOT NULL,
  PASSWORD VARCHAR(20)        NOT NULL,
  PLACE    VARCHAR(40),
  PRIMARY KEY (BUYER_ID)
);
CREATE TABLE auction.CATEGORY (
  CATEGORY_ID   INT AUTO_INCREMENT,
  CATEGORY_NAME VARCHAR(30) NOT NULL UNIQUE,
  PRIMARY KEY (CATEGORY_ID)
);
CREATE TABLE auction.THING (
  THING_ID         INT AUTO_INCREMENT,
  OWNER_ID         INT         NOT NULL,
  CATEGORY_ID      INT,
  NAME             VARCHAR(30) NOT NULL,
  PICTURE          BLOB,
  MIN_PRICE        INT         NOT NULL,
  DATE_OF_PUT      DATETIME   NOT NULL,
  TIME_FOR_SELLING DATETIME   NOT NULL,
  MESSAGE          VARCHAR(30),
  FOREIGN KEY (OWNER_ID) REFERENCES auction.BUYER (BUYER_ID),
  FOREIGN KEY (CATEGORY_ID) REFERENCES auction.CATEGORY(CATEGORY_ID),
  PRIMARY KEY (THING_ID)
);

CREATE TABLE auction.FACT_SALE (
  FACT_SALE_ID INT AUTO_INCREMENT PRIMARY KEY,
  BUYER_ID     INT       NOT NULL REFERENCES auction.BUYER (BUYER_ID),
  THING_ID     INT       NOT NULL REFERENCES auction.THING (THING_ID),
  SALE_TIME    DATETIME NOT NULL,
  PRICE        INT       NOT NULL
);
CREATE TABLE auction.FACT_OVERRIDE (
  FACT_OVERRIDE_ID INT AUTO_INCREMENT PRIMARY KEY,
  BUYER_ID         INT       NOT NULL REFERENCES auction.BUYER (BUYER_ID),
  THING_ID         INT       NOT NULL REFERENCES auction.THING (THING_ID),
  OVERRIDE_TIME    DATETIME NOT NULL,
  PRICE            INT       NOT NULL
);
