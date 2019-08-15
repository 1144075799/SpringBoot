# SpringBoot 小D

## 依赖环境和版本新特性

```shell
#简介
	1.依赖版本JDK8以上，SpringBoot2.X用JDK8,因为底层是Spring framework5，
	2.安装Maven最新版本，maven3.2以上版本
	3.Eclipse或者IDE
	4.新特性
```

## 手工创建web应用

```shell
#依赖
	<!--父依赖-->
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.1.7.RELEASE</version>
    </parent>
    
    
    <dependencies>
        <!--web依赖-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
    </dependencies>
    
#构建项目
@Controller
@EnableAutoConfiguration
public class HelloController {

    @RequestMapping("/")
    @ResponseBody
    public String hello(){
        return "Hello SpringBoot";
    }

    public static void main(String[] args) {
        SpringApplication.run(HelloController.class,args);
    }
}

```

## HTTP请求配置讲解

```shell
#常用注解讲解

	1.@SpringBootApplication相当于下面三个注解
		@SpringBootConfiguration
		@EnableAutoConfiguration
		@ComponentScan
		访问地址localhost:8080
	
	2.@RestController = @Controller+@ResponseBody(以JSON返回)
	
	3.@RestController and @RequestMapping是SpringMVC的注解，而不是SpringBoot也有的
	

#Get请求
	
	1. 从路径中获取参数
	接口测试:http://localhost:8080/100/10
   @RequestMapping(path = "/{city_id}/{user_id}",
   method = RequestMethod.GET)
   public Object findUser(@PathVariable("city_id") String cityId,
                          @PathVariable("user_id") String userId){
       params.clear();

       params.put("cityId",cityId);
       params.put("userId",userId);

       return params;
   }
   	
   	 2.测试GetMapping
   	 接口测试:localhost:8080/v1/page_user1?form=10&size=100
   	 
   	 @GetMapping(value = "/v1/page_user1")
    public Object pageUser(int form,int size){
        params.clear();

        params.put("from",form);
        params.put("size",size);

        return params;
   }
   	3.默认值，是否必须的参数		name中from映射到from
   	 @GetMapping(value = "/v1/page_user2")
    public Object pageUser2(@RequestParam(defaultValue = "0",name = "from") int from,int size){
        params.clear();

        params.put("from",from);
        params.put("size",size);


        return params;
    }
    
    4.功能描述:bean对象
     注意:  #1.注意需要定位http头为content-type为application/json
           #2.使用body传输数据
    @RequestMapping("/v1/save_user")
    public Object saveUser(@RequestBody User user){
        params.clear();

        params.put("user",user);

        return params;
    }
    
    5.功能描述:测试获取http头信息
    @GetMapping("/v1/get_header")
    public Object getHeader(@RequestHeader("access_token") String accessToken,String id){
        params.clear();
        params.put("access_token",accessToken);
        params.put("id",id);
        return params;
    }
    
    6.HttpServletRequest
    @GetMapping("/v1/test_request")
    public Object testRequest(HttpServletRequest request){
        params.clear();
        String id=request.getParameter("id");
        params.put("id",id);
        return params;
    }
   
#post请求 put请求 delete请求
```

## json框架和JackSon返回结果处理

```shell
#简介常用的Json框架和注解
	1.常用框架	阿里 fastjson 谷歌gson等
	JavaBean序列化为json，性能 JackSon>Fastjson>Gson>JSon-lib同个结构
		测试方法:循环序列化，百万次，次数达到一定才可以看出差别
		
	Jackson，FastJson，Gson类库各有优点，各有自己的专长
	空间换时间，时间换空间
	
	2.jackson处理相关自动 配置再变量上面
		指定字段不返回 @JsonIgnore
		指定日期格式	
		@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss",locale="zh",timezone="GNT+8")
		空子段不返回 @JsonInclude(Include.NON_NULL)
		指定别名:@JsonProperty
```

## 目录文件结构讲解

```shell
1.目录讲解
	src/main/java:存放代码
	src/main/resources
		static:存放静态文件,比如css,js,image,{访问方式 http:8080/js/main.js}
		templates:存放静态页面jsp，html，tpl
		config:存放配置文件，application，properties
		resource
2.引入依赖	thymeleaf
	#在templates里面写一个index.html
		<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
            <version>2.1.7.RELEASE</version>
        </dependency>
        
        @RequestMapping("/api/gopage")
        public Object index(){
            return "index";
        }
       
3.同个文件的加载顺序，静态资源文件
springboot 默认会挨个从
META/resources>resources>static>public 里面找是否存在相应的资源，如果有测直接返回.

4.修改资源文件
spring.resources.static-locations=classpath:/resources/,classpath:/public/,classpath:/static/,classpath:/templates/,classpath:/images/

5.静态资源文件存储在CDN
```

## 文件上传

```shell
#讲解SpringBoot 文件上传 MultipartFile File 源自SpringMVC
	MultipartFile 对象的transferTo方法，用于文件保存(效果和操作比原生用FileOuStream方便和高效)
#代码已经在项目中

```

## 文件大小设置

```shell
#设置上传文件的大小
在有@Configuration这个注解的类下面 而我们就在@SpringBootApplication下面配置
	@Bean
    public MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory factory=new MultipartConfigFactory();
        //单个文件最大
        factory.setMaxFileSize("10240KB");
        //设置总上传数据总大小
        factory.setMaxRequestSize("1024000KB");
        return factory.createMultipartConfig();
    }
```

## 热部署

```shell
#Dev-tool
spring-boot-devtools
```

# 自动注入

```shell
#配置文件加载
	
	方式一
		1.Controller上面配置
			@PropertySource({"classpath:application.properties"})
		2.增加属性
		@Value("${flie.path}")
    	private String filePath;
    
    方式二:实体类配置文件
    步骤
    	1.添加@Component	注解；
		2.使用@PropertySource({"classpath:application.properties"})
			注解指定配置文件的位置
		
		3.必须通过注入IOC对象Resource进来，才能在类中使用获取的配置文件值
			@Autowired
    		private ServerSettings serverSettings;
		
```

## MockMvc

```shell
# MockMvc讲解
	
	模拟一个mvc请求 去请求接口
	1.增加类注解
		@AutoConfigureMockMvc
		@RunWith(SpringRunner.class)
		@SpringBootTest(classes = {Application.class})
	2.相关API
		perform:执行一个RequestBuilder请求
		andExpect:添加ResultMatcher->MockMvcResultMatchers验证规则
		andReturn:最后返回相应的MvcResult
```

## 全局异常处理

```shell
#异常注解介绍
	@ControllerAdvice 如果返回json数据，则用RestControllerAdvice，就可以不加@ResponseBody		监听异常
	//捕获全局异常，处理所有不可知的异常
	@ExceptionHandler(value=Exception.class)
	

# 自定义异常和页面跳转
	resource目录下面新疆一个templates，并新建error.html
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("error.html");			//返回错误页面
        modelAndView.addObject("msg",e.getMessage());
        return modelAndView;
```

## 过滤器

```shell
#讲解springBoot 里面的Filter组件，回调机制
	
	filter简单理解:人---->检票员(filter)---->景点
	
	 1.SpringBoot启动默认加载的Filter
	 	characterEncodingFilter
	 	hiddenHttpMethodFilter
	 	httpPutFormContentFilter
	 	requestContextFilter
	 
	 2.Filter优先级
	 	Ordered.HIGHEST_PRECEDENCCE
	 	Ordered.LOWEST_PRECEDENCCE
	 	
	 	低位值意味着更高的优先级Higher values are interpreted as lower priority 
	 	自定义Filter，避免和默认的Filter优先级一样，不然会冲突
	 	同模块里面有相关默认Fliter
	 		web->servlet->filter
	 
	 3.自定义0Filter
	 	1.使用Servlet3.0的注解进行配置
	 	2.启动类里面添加@ServletComponentScan进行扫描
	 	3.新建一个Filter类，implement Filter，并实现对应的接口
	 	4.@WebFilter标记一个类是filter，被spring进行扫描
	 		urlPatterns:拦截规则，支持正则
	 		
	 	5.控制chain。doFilter的方法的调用，来实现是否通过放行，
		放行:filterChain.doFilter(servletRequest,servletResponse );
	 	不放行，web应用res.sendRedirect("/index.html");	页面跳转
	 	6.场景：权限控制，用户登录(非前端后端分离场景)
	 	
	 
#用Servlet3.0的注解自定义原生Servlet实战
	使用Servlet3.0的注解自定义原生Servlet和Listener
```



## Mybatis

```shell
#持久化数据方式介绍
	不提供对象和关系模型的直接映射，半ORM
#注解实战
	
	
	$ 有风险，有漏洞 所以采用 # 避免SQL注入
	
	Preparing预编译
	
	Parameters插入的值
	
#事务介绍和常见的隔离级别
	介绍什么事务
		单机事务，分布式事务处理
	
	讲解场景的隔离级别
		
		
	@Transactional(propagation = Propagation.REQUIRED)
	#//Propagation隔离级别
```

## Redis

```shell
#redis
	请求-> Tomcat->mysql
				->redis(缓存服务)	第二次的时候，就请求缓存

#nosql
	redis MC mongodb hive....
	
#高并发 大用户量 都是采用缓存，通过缓存可以减少mysql的IO

#在线学习网址	http://try.redis.io/

#启动服务 redis-server.exe redis.windows.conf

#启动客户端
	redis-cli.exe
```

## 定时任务

```shell
#简介
	1.常见定时任务 Java自带的java.util.Timer类
		Timer:配置麻烦，时间延后 
		Timertask：不推荐
	2.Quertz框架
		配置更简单
		xml或者注解
		
	3.SpringBoot使用注解方式(推荐使用)
		@EnableScheduling           //开启定时任务
		@Component					//被spring扫描
		@Scheduled(fixedRate = 2000)	//被定时任务扫描，定期执行方法
		
#常用定时任务配置实现
	简介:常用定时任务表达式配置和在线生成器
		1.crontab	工具
		 @Scheduled(cron = "*/2 * * * * *")  每两秒
		2.fixedRate:定时多久执行一次(上一次开始执行时间点后2缪在次执行)
		3.fixedDelay:上一次执行结束时间点后xx秒再次执行
		4.fixedDelayString	字符串形式，可以通过配置文件指定
```

## 异步任务

```shell
#什么是异步任务和使用场景
	适用于处理log，发送邮件，短信.....等

2.启动类里面使用@EnableAsync注解开启功能，自动扫描

3.定义异步任务类并使用@Component标记组件被容器扫描，异步方法加上@Async
	注意点
		1.增加Future<String> 返回结果 AsyncResult<String>("task执行完成")
		2.如果需要拿到结果，需要判断全部的tash.isDone()
4.通过注入方式，注入到controller里面。如果测试前后区别则改为同步则Async注解掉
```

## 日志框架

```shell
#LogBack
	
```

## ES

```shell
#简介
	简介:介绍ES的主要特点和使用场景，新特性讲解
	
	mysql：like 模糊 性能问题
	
	solr:争对企业，Lucene
	
	elasticsearch:争对数据量特别大，PB,TB
		纯Java开发，springBoot使用，5.6.8版本
	
#特点
	全文检索，结构化检索，数据统计，分析，接近试试处理，分布式搜素(可部署数百台服务器)，处理PB级别的数据搜索纠错，自动完成
	
#使用场景
	日志搜素，数据聚合，数据监控，报表统计分析
	
#新特性
	1.6.2.X版本基于Lucene 7.X 更快，性能进一步提升，对应的序列化组件，审计到Jackson2.8
	2.推荐使用5.0版本推出Java REST/
	HTTP客户端，依赖少，比Transport使用更方便，在基准测试中，性能并不术语Tramsport客户端
	
	3.(重要)不在支持一个索引库里面铎哥type，6.X版本已经禁止一个index里面多个type，所以一个index索引库只能存在1个type
	

#讲解3
	mysql:  database	table	record
	es	 :	index		type(1)	document
	
#下载 解压
	到bin目录下面，直接点击es.bat打开
	#输入http://127.0.0.1:9200/_cat/health   查看状态
	配置外网访问的 到config/es.yml 设置network.host:0.0.0.0 设置为外网可以访问
	
#基础
	查看集群健康状态:localhost:9200/_cat/health?v
	查看节点字数:localhost:9200/_cat/nodes?v
	查看索引    :localhost:9200/_cat/indices?v
	创建索引	:localhost:9200/customer?pretty
	添加数据	:localhost:9200/customer/external/1?pretty	put
	{
        "name":"John Doe"
    }
    查看数据	:localhost:9200/customer/external/1?pretty   get
    查看表结构	:localhost:9200/blog
#SpringBoot整合ES
	简介:SpringBoot 2.X整合es 5.6.8
	
	#1.添加依赖
	
	#2.接口集成ESRepository，里面有很多默认实现
		#索引名称记得小写，类属性名称也是小写
	新建实体对象article
	加上类注解 @Document(indexName = "blog",type = "article")
	
	#配置文件
		
    
```

