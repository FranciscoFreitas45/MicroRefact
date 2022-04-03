DROP TABLE IF EXISTS public.USER_BOOK;
DROP TABLE IF EXISTS public.REQUIREMENT;
DROP TABLE IF EXISTS public.EXCHANGE;
DROP TABLE IF EXISTS public.CHAT_MESSAGE;
DROP TABLE IF EXISTS public.CHAT_ROOM;
DROP TABLE IF EXISTS public.USERS;
DROP TABLE IF EXISTS public.COVER;
DROP TABLE IF EXISTS public.BOOK_CATEGORIES;
DROP TABLE IF EXISTS public.BOOK_AUTHORS;
DROP TABLE IF EXISTS public.BOOK;
DROP TABLE IF EXISTS public.LANGUAGE;
DROP TABLE IF EXISTS public.COORDINATES;
DROP TABLE IF EXISTS public.CATEGORY;
DROP TABLE IF EXISTS public.AUTHOR;

CREATE TABLE IF NOT EXISTS AUTHOR
(
    ID      bigserial,
    NAME    VARCHAR(255) not null,
    SURNAME VARCHAR(255) not null,
    constraint author_pkey primary key (ID)
);

CREATE TABLE IF NOT EXISTS CATEGORY
(
    ID   serial,
    NAME VARCHAR(255),
    constraint category_pkey primary key (ID)
);

CREATE TABLE IF NOT EXISTS COORDINATES
(
    ID        bigserial,
    LATITUDE  numeric not null,
    LONGITUDE numeric not null,
    constraint coordinates_pkey primary key (ID)
);

CREATE TABLE IF NOT EXISTS LANGUAGE
(
    ID   serial,
    NAME VARCHAR(255) not null,
    constraint language_pkey primary key (ID)
);

CREATE TABLE IF NOT EXISTS BOOK
(
    ID          bigserial,
    CONDITION   INTEGER      not null,
    TITLE       VARCHAR(255) not null,
    LANGUAGE_ID INTEGER,
    constraint book_pkey primary key (ID),
    constraint FKMRHFP9WFI5DY4BWL87BX8IVUA
        foreign key (LANGUAGE_ID) references LANGUAGE (ID)
);

CREATE TABLE IF NOT EXISTS BOOK_AUTHORS
(
    BOOK_ID   bigserial not null,
    AUTHOR_ID bigserial not null,
    constraint FK78GU95LGLHC6CS2U5JFUDX6E5
        foreign key (AUTHOR_ID) references AUTHOR (ID),
    constraint FKS4XM7Q8I3UXVAISWJ1C35NNXW
        foreign key (BOOK_ID) references BOOK (ID)
);

CREATE TABLE IF NOT EXISTS BOOK_CATEGORIES
(
    BOOK_ID     bigserial not null,
    CATEGORY_ID INTEGER   not null,
    constraint FKEN2TGSLRQSPFIEN26R5HAGXF9
        foreign key (CATEGORY_ID) references CATEGORY (ID),
    constraint FKRQ5MFTM1EJL023EPQBN42LPA3
        foreign key (BOOK_ID) references BOOK (ID)
);

CREATE TABLE IF NOT EXISTS COVER
(
    ID      bigserial not null,
    DATA    bytea,
    NAME    VARCHAR(255),
    TYPE    VARCHAR(255),
    BOOK_ID bigserial,
    constraint cover_pkey primary key (ID),
    constraint FK7JOELP33TNSU71VUPV6KRKREM
        foreign key (BOOK_ID) references BOOK (ID)
);
CREATE TABLE IF NOT EXISTS USERS
(
    ID             bigserial,
    EMAIL          VARCHAR(255) not null,
    NAME           VARCHAR(255) not null,
    PASSWORD       VARCHAR(255) not null,
    SURNAME        VARCHAR(255) not null,
    USERNAME       VARCHAR(255) not null,
    COORDINATES_ID bigserial,
    constraint user_pkey primary key (ID),
    constraint UK_OB8KQYQQGMEFL0ACO34AKDTPE
        unique (EMAIL),
    constraint UK_SB8BBOUER5WAK8VYIIY4PF2BX
        unique (USERNAME),
    constraint FK1FL6UT5W9XEBF7364DYYQ6W5J
        foreign key (COORDINATES_ID) references COORDINATES (ID)
);

CREATE TABLE IF NOT EXISTS CHAT_ROOM
(
    ID           bigserial,
    RECIPIENT_ID bigserial,
    SENDER_ID    bigserial,
    constraint chat_room_pkey primary key (ID),
    constraint FK5OMNAUEWBG4GA8EY3W8U6D0DD
        foreign key (SENDER_ID) references USERS (ID),
    constraint FKPK5KXFC5QE64H4F4F4W4M6YYC
        foreign key (RECIPIENT_ID) references USERS (ID)
);

CREATE TABLE IF NOT EXISTS CHAT_MESSAGE
(
    ID           bigserial,
    CONTENT      VARCHAR(255),
    TIMESTAMP    TIMESTAMP,
    ROOM_ID      bigserial,
    RECIPIENT_ID bigserial,
    SENDER_ID    bigserial,
    constraint chat_message_pkey primary key (ID),
    constraint FKFVBC4WVHK51Y0QTNJRBMINXFU
        foreign key (ROOM_ID) references CHAT_ROOM (ID),
    constraint FKM156ENE5XJFIA6VE3MWAPCKLA
        foreign key (RECIPIENT_ID) references USERS (ID),
    constraint FKM92RH2BMFW19XCN7NJ5VRIXSI
        foreign key (SENDER_ID) references USERS (ID)
);

CREATE TABLE IF NOT EXISTS EXCHANGE
(
    ID              bigserial,
    DEPOSIT         numeric,
    EXCHANGE_STATUS INTEGER not null,
    BOOK_ID         bigserial,
    COORDINATES_ID  bigserial,
    FOR_BOOK_ID     BIGINT references BOOK (ID),
    USER_ID         bigserial,
    WITH_USER_ID    BIGINT references BOOK (ID),
    constraint exchange_pkey primary key (ID),
    constraint FK2RLNDWL0584JHNKKX3KN19F1S
        foreign key (COORDINATES_ID) references COORDINATES (ID),
    constraint FK3EB1WCN8BB9V5MD8V8P3C2FE
        foreign key (USER_ID) references USERS (ID),
    constraint FKG74RQI4NPJH06N4X7KRJSQGD3
        foreign key (WITH_USER_ID) references USERS (ID)
);

CREATE TABLE IF NOT EXISTS REQUIREMENT
(
    ID          bigserial,
    CREATE_TIME TIMESTAMP,
    IS_ACTUAL   BOOLEAN not null,
    EXCHANGE_ID bigserial,
    USER_ID     bigserial,
    constraint requirement_pkey primary key (ID),
    constraint FK70OB1ES44DHN8NK8GQH485OW7
        foreign key (USER_ID) references USERS (ID),
    constraint FKTGLK51523CWR8PY7PY1HBCKQ
        foreign key (EXCHANGE_ID) references EXCHANGE (ID)
);

CREATE TABLE IF NOT EXISTS USER_BOOK
(
    BOOK_ID     bigserial not null,
    USER_ID     bigserial not null,
    BOOK_STATUS INTEGER,
    AT_USER_ID  BIGINT references USERS,
    constraint user_book_pkey primary key (BOOK_ID, USER_ID),
    constraint FK85PWLTN867PJXOG1GK5SMTQCW
        foreign key (BOOK_ID) references BOOK (ID),
    constraint FKBC0BWDNNDNXHCT38SINBEM0N0
        foreign key (USER_ID) references USERS (ID)
);
