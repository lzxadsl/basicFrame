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
DROP TABLE IF EXISTS "Permission";
CREATE TABLE "Permission" (
"id" int4 NOT NULL,
"name" varchar(1024) COLLATE "default" NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of Permission
-- ----------------------------
BEGIN;
INSERT INTO "Permission" VALUES ('1', '/**=authc');
INSERT INTO "Permission" VALUES ('2', '/forword/basic/*.htm=anon ');
COMMIT;

-- ----------------------------
-- Table structure for Role
-- ----------------------------
DROP TABLE IF EXISTS "Role";
CREATE TABLE "Role" (
"id" int4 NOT NULL,
"name" varchar(32) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of Role
-- ----------------------------
BEGIN;
INSERT INTO "Role" VALUES ('1', 'admin');
INSERT INTO "Role" VALUES ('2', 'gust');
COMMIT;

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS "role_permission";
CREATE TABLE "role_permission" (
"id" int4 NOT NULL,
"role_id" int4,
"permission_id" int4
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
BEGIN;
INSERT INTO "role_permission" VALUES ('1', '1', '1');
INSERT INTO "role_permission" VALUES ('2', '2', '2');
COMMIT;

-- ----------------------------
-- Table structure for User
-- ----------------------------
DROP TABLE IF EXISTS "User";
CREATE TABLE "User" (
"id" int4 NOT NULL,
"username" varchar(32) COLLATE "default",
"password" varchar(32) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of User
-- ----------------------------
BEGIN;
INSERT INTO "User" VALUES ('1', 'lzx', '123456');
INSERT INTO "User" VALUES ('2', 'lsl', '123456');
COMMIT;

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS "user_role";
CREATE TABLE "user_role" (
"id" int4 NOT NULL,
"user_id" int4,
"role_id" int4
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of user_role
-- ----------------------------
BEGIN;
INSERT INTO "user_role" VALUES ('1', '1', '1');
INSERT INTO "user_role" VALUES ('2', '2', '2');
COMMIT;

-- ----------------------------
-- Alter Sequences Owned By 
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table Permission
-- ----------------------------
ALTER TABLE "Permission" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table Role
-- ----------------------------
ALTER TABLE "Role" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table role_permission
-- ----------------------------
ALTER TABLE "role_permission" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Uniques structure for table User
-- ----------------------------
ALTER TABLE "User" ADD UNIQUE ("username");

-- ----------------------------
-- Primary Key structure for table User
-- ----------------------------
ALTER TABLE "User" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table user_role
-- ----------------------------
ALTER TABLE "user_role" ADD PRIMARY KEY ("id");
