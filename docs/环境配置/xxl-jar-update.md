## xxl的修改日志
首先下载xxl的源码，这里推荐gitee，因为不可爱，github太卡了；
> gitee原先版本：https://gitee.com/xuxueli0323/xxl-job
> 个人修改版本：https://gitee.com/yysimple/simple-xxl-job/tree/2.3.0/


这里我是使用的2.3.0的版本进行改造的；

1. 修改yml文件配置：
```yaml
server.port=9999
### xxl-job, datasource for update gitee
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/xxl_job?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&serverTimezone=Asia/Shanghai
spring.datasource.username=root
spring.datasource.password=970412@wcx.com
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

2. 修改logback.xml文件；
```xml
<define name="logPath" class="com.xxl.job.admin.config.LogPathConfig"/>
<property name="log.path" value="${logPath}"/>
```
这里就是简单的修改了下日志打印路径，有自己指定或者系统自动获取，不需要自己去创建文件；

3. 日志打印类：

配置类：
```java
@Component
@ConfigurationProperties(prefix = "xxl.job.log")
public class LogConfig {

    /**
     * 日志路径
     */
    private String logPath;

    public String getLogPath() {
        return logPath;
    }

    public void setLogPath(String logPath) {
        this.logPath = logPath;
    }
}
```

逻辑类：
```java
@Component
public class LogPathConfig extends PropertyDefinerBase {

    @Resource
    LogConfig logConfig;

    @Override
    public String getPropertyValue() {
        if (Objects.isNull(logConfig) || StrUtil.hasBlank(logConfig.getLogPath())) {
            return buildLogPath();
        } else {
            return logConfig.getLogPath();
        }
    }

    public String buildLogPath() {
        String property = System.getProperty("user.dir");
        String substring = property.substring(0, property.lastIndexOf("/"));
        String xxlLogPath = substring + "/xxl_logs/xxl_job.log";
        FileUtil.touch(xxlLogPath);
        return xxlLogPath;
    }
}
```