### chatroom

服务端

Springboot + mybatis-plus + jwt



websocket 请求路径：`ws://localhost:8080/websocket`

接口：`/login`

请求方式：POST

from-data 

```json
{
    "username":"admin"
    "password":"admin"
}
```

响应数据

```json
{
    "admin": true,
    "delete": false,
    "id": 1,
    "name": "管理员",
    "status": true,
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYW1lIjoi566h55CG5ZGYMSIsImV4cCI6MTY5Mzk0MzI0OCwidXNlcklkIjoiMSIsImlhdCI6MTY5MzkwNzI0OH0.LNh8_QyfBStJnrKNGD-Adg3ic5P-77fMghX42W6iXD4",
    "username": "admin"
}
```



wabsocket 发送消息的格式 

```json
{"broadcast":true,"msg":["aaa","管"]}   
{"broadcast":false,"from":"管理员","msg":"asdasdasd"}   
```

客户端发送给服务器

```json
{"target":"王五","message":"hello"}
```

```sql
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '账号',
  `password` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
  `head_image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '头像',
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '名称',
  `is_delete` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除',
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否禁用',
  `admin` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否是管理员',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE,
  UNIQUE INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
```
