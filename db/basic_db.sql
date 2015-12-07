/*
Navicat PGSQL Data Transfer

Source Server         : basic_db
Source Server Version : 90204
Source Host           : localhost:5432
Source Database       : basic_db
Source Schema         : public

Target Server Type    : PGSQL
Target Server Version : 90200
File Encoding         : 65001

Date: 2015-12-06 23:02:44
*/


-- ----------------------------
-- Table structure for Permission
-- ----------------------------
DROP sequence IF EXISTS sys_permission_id_seq;
CREATE sequence sys_permission_id_seq increment by 1 minvalue 1 no maxvalue start with 1; 
DROP TABLE IF EXISTS "sys_permission";
CREATE TABLE "sys_permission" (
"id" int4 DEFAULT nextval('sys_permission_id_seq'::regclass) NOT NULL,
"name" varchar(1024) COLLATE "default" NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of Permission
-- ----------------------------
BEGIN;
INSERT INTO "sys_permission" VALUES (nextval('sys_permission_id_seq'), '/**=authc');
INSERT INTO "sys_permission" VALUES (nextval('sys_permission_id_seq'), '/forword/basic/*.htm=anon ');
INSERT INTO "sys_permission" VALUES (nextval('sys_permission_id_seq'), '/shiro/login.htm=anon ');
COMMIT;

-- ----------------------------
-- Table structure for Role
-- ----------------------------
DROP sequence IF EXISTS sys_role_id_seq;
CREATE sequence sys_role_id_seq increment by 1 minvalue 1 no maxvalue start with 1; 
DROP TABLE IF EXISTS "sys_role";
CREATE TABLE "sys_role" (
"id" int4 DEFAULT nextval('sys_role_id_seq'::regclass) NOT NULL,
"name" varchar(32) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of Role
-- ----------------------------
BEGIN;
INSERT INTO "sys_role" VALUES (nextval('sys_role_id_seq'), 'admin');
INSERT INTO "sys_role" VALUES (nextval('sys_role_id_seq'), 'gust');
INSERT INTO "sys_role" VALUES (nextval('sys_role_id_seq'), 'vip');
COMMIT;

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP sequence IF EXISTS sys_role_permission_id_seq;
CREATE sequence sys_role_permission_id_seq increment by 1 minvalue 1 no maxvalue start with 1; 
DROP TABLE IF EXISTS "sys_role_permission";
CREATE TABLE "sys_role_permission" (
"id" int4 DEFAULT nextval('sys_role_permission_id_seq'::regclass) NOT NULL,
"role_id" int4,
"permission_id" int4
)
WITH (OIDS=FALSE)
;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
BEGIN;
INSERT INTO "sys_role_permission" VALUES (nextval('sys_role_permission_id_seq'), '1', '1');
INSERT INTO "sys_role_permission" VALUES (nextval('sys_role_permission_id_seq'), '2', '2');
INSERT INTO "sys_role_permission" VALUES (nextval('sys_role_permission_id_seq'), '3', '3');
COMMIT;

-- ----------------------------
-- Table structure for User
-- ----------------------------
DROP sequence IF EXISTS sys_user_id_seq;
CREATE sequence sys_user_id_seq increment by 1 minvalue 1 no maxvalue start with 1; 
DROP TABLE IF EXISTS "sys_role_permission";
DROP TABLE IF EXISTS "sys_user";
CREATE TABLE "sys_user" (
"id" int4 DEFAULT nextval('sys_user_id_seq'::regclass) NOT NULL,
"username" varchar(32) COLLATE "default",
"password" varchar(32) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of User
-- ----------------------------
BEGIN;
INSERT INTO "sys_user" VALUES (nextval('sys_user_id_seq'), 'lzx', '123456');
INSERT INTO "sys_user" VALUES (nextval('sys_user_id_seq'), 'lsl', '123456');
COMMIT;

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP sequence IF EXISTS sys_user_role_id_seq;
CREATE sequence sys_user_role_id_seq increment by 1 minvalue 1 no maxvalue start with 1; 
DROP TABLE IF EXISTS "sys_user_role";
CREATE TABLE "sys_user_role" (
"id" int4 DEFAULT nextval('sys_user_role_id_seq'::regclass) NOT NULL,
"user_id" int4,
"role_id" int4
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of user_role
-- ----------------------------
BEGIN;
INSERT INTO "sys_user_role" VALUES (nextval('sys_user_role_id_seq'), '1', '1');
INSERT INTO "sys_user_role" VALUES (nextval('sys_user_role_id_seq'), '2', '2');
INSERT INTO "sys_user_role" VALUES (nextval('sys_user_role_id_seq'), '2', '3');
COMMIT;

-- ----------------------------
-- Alter Sequences Owned By 
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table Permission
-- ----------------------------
ALTER TABLE "sys_permission" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table Role
-- ----------------------------
ALTER TABLE "sys_role" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table role_permission
-- ----------------------------
ALTER TABLE "sys_role_permission" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Uniques structure for table User
-- ----------------------------
ALTER TABLE "sys_user" ADD UNIQUE ("username");

-- ----------------------------
-- Primary Key structure for table User
-- ----------------------------
ALTER TABLE "sys_user" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table user_role
-- ----------------------------
ALTER TABLE "sys_user_role" ADD PRIMARY KEY ("id");
