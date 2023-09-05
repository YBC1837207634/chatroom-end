### chatroom

服务端

Springboot + mybatis-plus + jwt



websocket 请求路径：`ws://localhost:8080/websocket`

接口：`/login`

请求方式：POST

from-data 

```json
{
    username:admin
    password:admin
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
{"broadcast":true,"msg":["aaa","管"]}   // 广播消息
{"broadcast":false,"from":"管理员","msg":"asdasdasd"}   // 发送到指定用户
```

客户端发送给服务器

```json
{"target":"王五","message":"hello"}
```

