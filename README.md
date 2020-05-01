# 基于java的Socket聊天室
## 登陆注册
![](https://github.com/jiayiwang5/socket_java/blob/master/img/1.png)
![](https://github.com/jiayiwang5/socket_java/blob/master/img/2.png)
## 主界面
![](https://github.com/jiayiwang5/socket_java/blob/master/img/3.png)

## Server
* 采用用户->流（User->*Stream）的映射，来存储并区分
* 主线程阻塞，有连接便新开子线程处理文本
## Client
* 有收/发两个线程
## 文件(图片、语音)处理
文本传输基于输入输出流的简单封装，文件传输同样基于此流
* client ： 文件读取流->装填byte->base64加密(编码)->打包json->write到流
* server ： 字节读取流->json解包->base64解码(转码)->装填byte->文件写入流
## 文件上传下载指令流
![](https://github.com/jiayiwang5/socket_java/blob/master/img/4.png)
### 为什么不采用文件流传输？
  为避免流占用，必须关闭文件流，这必将导致socket自行close，重连后对象更新并新开线程，造成诸如死线程，找不到原流等致命问题，此时Server必须全面改写，弃用用户->流模式，改用线程池方式等，此番虽然会在一定程度上提高效率但会加大代码量。
