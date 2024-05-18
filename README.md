参考大佬的md：原地址：https://gitee.com/galie/SPZX-Backend/blob/master/README.md
# SpringCloud 项目-尚品甄选

###### 项目运行图片

![](https://gitee.com/galie/SPZX-Backend/raw/master/IMG/演示.png)

###### **前端演示GIF**

![](https://gitee.com/galie/shiping/raw/master/tinywow_Video_1712590709390_52878502.gif)

## 一、项目功能

### 1、后台管理系统功能

![](https://gitee.com/galie/SPZX-Backend/raw/master/IMG/1.png)

### 2、前台用户系统功能

![](https://gitee.com/galie/SPZX-Backend/raw/master/IMG/2.png)



## 二、项目技术

### 1、表的关系

#### （1）权限相关的表关系

![](https://gitee.com/galie/SPZX-Backend/raw/master/IMG/3.png)

#### （2）商品相关的表关系

- product  商品基本信息表
- product_sku 商品sku表，一个商品包含多个sku
- product_details 商品详情表，商品详情图片



#### （3）订单相关的表关系

- order_info 订单基本信息表
- order_item 订单项表，一个订单里面包含多个订单项



### 2、前端技术

- Element-Admin：Vue3 + Element Plus
- ES6：模板字符串、箭头函数...
- NPM
- Axios



### 3、后端技术

![](https://gitee.com/galie/SPZX-Backend/raw/master/IMG/4.png)

![](https://gitee.com/galie/SPZX-Backend/raw/master/IMG/5.png)

## 三、项目准备工作

#### 前端开发：

1. [nodejs安装和环境配置 - 今晚打老虎！ - 博客园 (cnblogs.com)](https://www.cnblogs.com/netcore5/p/15259499.html)
2. [WebStorm2023汉化下载 WebStorm v2023.3.6 中文正式免费版(附汉化包+安装教程) 下载-脚本之家 (jb51.net)](https://www.jb51.net/softs/598714.html)或者[vscode安装及插件安装_vscode安装本地插件-CSDN博客](https://blog.csdn.net/qq_44757223/article/details/122676919)或者[vsCode安装教程和插件安装方法 - 宝哥软件园 (baoge.net)](https://www.baoge.net/article/49252.html)（选其中一个就好）

#### 后端开发：

1. [IDEA 2022.3.2 破解版下载_激活安装图文教程（永久激活,亲测有效） - 异常教程 (exception.site)](https://www.exception.site/essay/how-to-free-use-idea-202021-by-resigter-code)
2. [Java/JDK下载安装与环境配置（Windows 10 超详细的图文版教程 ）-CSDN博客](https://blog.csdn.net/qq_26552691/article/details/94598788)
3. [Maven超细致史上最全Maven下载安装配置教学（2023更新...全版本）建议收藏...赠送IDEA配置Maven教程-CSDN博客](https://blog.csdn.net/MSDCP/article/details/127680844)

#### 其他工具：

[MySQL的安装与配置——详细教程 - itcui - 博客园 (cnblogs.com)](https://www.cnblogs.com/itcui/p/15511683.html)

[Windows安装Redis的流程 - 罗毅豪 - 博客园 (cnblogs.com)](https://www.cnblogs.com/luoyihao/p/16793388.html)

https://yarnpkg.com/downloads/1.22.15/yarn-1.22.15.msi（yarn下载）

[Yarn 安装与使用详细介绍-CSDN博客](https://blog.csdn.net/csdn_yudong/article/details/82015885)

[Git的下载安装 (图文教程)_git 下载-CSDN博客](https://blog.csdn.net/weixin_47638941/article/details/120632890)

**注意:**

1. nodejs 要下载大于16.14，小于18的版本
2. idea 要下载2020.1以上的版本，才能下载MyBatisX插件
   推荐的可能不是最优的，大家可以根据自己的需求去百度查找相关教程

## 四、快速上手

❗️❗️**运行本项目首先需要启动 Mysql，redis，nacos，以及Minio** 

❗️❗️**注意：请使用我的前端代码，后端代码以及数据库文件，不然可能会有一些问题，比如登录不跳转，访问无数据**

我已经把这四个文件上传至百度网盘中，有需要的可以自行下载🌹🌹🌹

链接：https://pan.baidu.com/s/1EZEFFRNqCdZBOkK-hs6wAA?pwd=b37v 
提取码：b37v

之后需要根据你的情况去我的yml配置文件中修改，所有修改的地方我都在IDEA上打了 TODO 注释，如下图，可以快速定位修改，

![](https://gitee.com/galie/SPZX-Backend/raw/master/IMG/9.png)

### Minio图片存储

1）在spzx-manager中的 `application-dev.yml` 中修改如下配置为你自己的，注意账号密码以及ip，我用的是本地的ip：

```
minio:
  endpointUrl: http://127.0.0.1:9000
  accessKey: minioadmin
  secreKey: minioadmin
  bucketName: spzx-bucket
```

#### Minio本地启动方式

我的Monio放在D盘根目录下，需要D:\minio下输入cmd进入命令提示符系统

在命令符提示系统中输入minio.exe server D:\minio\data即可启动成功，账号密码都是minioadmin

![](https://gitee.com/galie/SPZX-Backend/raw/master/IMG/6.png)

### MySQL 数据库

1）在spzx-manager中的 `application-dev.yml` 中修改如下配置为你自己的，注意账号密码以及ip：

```yml
spring:
  datasource:
    type: com.zaxxer.hikari.HikariDataSource
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://192.168.10.100:3306/db_spzx?characterEncoding=utf-8&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=GMT%2B8
    username: root
    password: root
```

2）执行 `db_spzx.sql` 中的数据库语句，自动创建库表（sql文件已经放在仓库里面了）

### Redis

1）在spzx-manager中的 `application-dev.yml` 中修改如下 Redis 配置为你自己的：

```yml
# Redis的相关配置
data:
  redis:
    host: 192.168.10.100
    port: 6379
```

2）在spzx-server-gateway中的 `application-dev.yml` 中修改如下 Redis 配置为你自己的：

```yml
# Redis的相关配置
data:
  redis:
    host: 192.168.10.100
    port: 6379
```

### Nacos

1）在spzx-server-gateway中的 `application-dev.yml` 中修改如下 nacos配置为你自己的，我用的是本机的，根据实际情况修改即可：

```yml
cloud:
    nacos:
      discovery:
        server-addr: localhost:8848
```

#### Nacos本地启动

进入nacos目录下的bin目录cmd窗口输入**startup.cmd -m standalone**即可	

### 注意：

**service-cart，service-order，service-pay，service-product，service-user**模块中的 `application-dev.yml` 中都要修改如下（**mysql，redis，nacos**）配置为你自己的：

```yml
cloud:
    nacos:
      discovery:
        server-addr: localhost:8848
  # 配置数据库连接信息
  datasource:
    type: com.zaxxer.hikari.HikariDataSource
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://192.168.10.100:3306/db_spzx?characterEncoding=utf-8&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=GMT%2B8
    username: root
    password: root
  # Redis的相关配置
  data:
    redis:
      host: 192.168.10.100
      port: 6379
```

如上配置修改完成之后，分别启动如下模块，后端即可正确运行，如果有报错的话，需要针对报错检查排查原因，最后祝大家可以顺利启动，谢谢啦🌹🌹🌹

![](https://gitee.com/galie/SPZX-Backend/raw/master/IMG/7.png)



