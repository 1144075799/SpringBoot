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





