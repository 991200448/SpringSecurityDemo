# Spring Security Demo

## 项目概述
本项目 `springSecurityDemo` 是一个基于 Spring Boot 框架搭建的演示项目，主要聚焦于 Spring Security 的应用，涵盖了用户认证、授权、JWT 令牌管理、资源权限控制等核心功能，同时集成了 MyBatis-Plus 进行数据库操作，采用 Thymeleaf 作为模板引擎。

## 技术栈
- **后端框架**：Spring Boot 3.4.3
- **数据库操作**：MyBatis-Plus 3.5.7，MySQL 8.2.0
- **安全框架**：Spring Security
- **模板引擎**：Thymeleaf
- **JSON 处理**：FastJSON 2.0.39
- **代码简化**：Lombok 1.18.36
- **连接池**：HikariCP 5.1.0
- **JWT 处理**：JJWT 0.11.5
- **API 文档**：Knife4j 4.4.0

## 项目结构
```
springSecurityDemo/
├── src/
│   ├── main/
│   │   ├── java/            # Java 源代码
│   │   ├── resources/       # 资源文件
│   │   │   ├── application.yml # 应用配置文件
│   │   │   └── templates/   # Thymeleaf 模板文件
│   └── test/                # 测试代码
├── pom.xml                  # Maven 项目配置文件

## 环境准备
1. **JDK**：Java 17
2. **Maven**：3.9.9 或更高版本
3. **MySQL**：8.0.33 或更高版本

## 配置步骤
1. **数据库配置**：
    - 创建名为 `jwtStarter` 的数据库。
    - 打开 `src/main/resources/application.yml` 文件，修改数据库连接信息：
```yaml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://127.0.0.1:3306/jwtStarter?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai
    username: root
    password: 123456
```
2. **Maven 依赖**：项目使用 Maven 管理依赖，运行以下命令下载依赖：
```bash
mvn clean install
```

## 运行项目
### 开发环境
在 IDE 中打开项目，找到 `com.daisypig.SpringSecurityDemoApplication` 类，运行其 `main` 方法启动项目。

### 命令行
在项目根目录下，运行以下命令启动项目：
```bash
mvn spring-boot:run
```
项目启动后，访问 `http://localhost:9082` 即可看到演示页面。

## 功能特性
1. **用户认证**：支持自定义登录页面，登录成功或失败后返回 JSON 信息。
2. **授权管理**：实现资源权限控制，对无权限请求进行处理，支持方法注解授权。
3. **JWT 令牌**：使用 JJWT 生成和验证 JWT 令牌，确保用户身份安全。
4. **API 文档**：集成 Knife4j 生成 API 文档，方便接口调试和查看。

## 常见问题
### 数据库连接问题
- 检查 `application.yml` 中的数据库连接信息是否正确。
- 确保 MySQL 服务已启动，并且数据库名、用户名和密码正确。

### 依赖下载问题
- 检查 Maven 配置，确保网络连接正常。
- 可以尝试清除本地 Maven 仓库中的缓存，然后重新下载依赖：
```bash
mvn dependency:purge-local-repository
mvn clean install
```

## 参考文档
- [官方 Apache Maven 文档](https://maven.apache.org/guides/index.html)
- [Spring Boot Maven 插件参考指南](https://docs.spring.io/spring-boot/3.4.3/maven-plugin)
- [Spring Security](https://docs.spring.io/spring-boot/3.4.3/reference/web/spring-security.html)
- [Thymeleaf](https://docs.spring.io/spring-boot/3.4.3/reference/web/servlet.html#web.servlet.spring-mvc.template-engines)
- [Spring Web](https://docs.spring.io/spring-boot/3.4.3/reference/web/servlet.html)

## 贡献者
如果你有任何建议或发现问题，请提交 Issue 或 Pull Request，我们将非常感谢你的贡献！
