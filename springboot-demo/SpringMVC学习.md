# SpringMVC学习
  [手码两万余字，SpringMVC 包教包会](https://www.cnblogs.com/lenve/p/12100698.html)
  
  [SpringMVC源码学习：容器初始化+MVC初始化+请求分发处理+参数解析+返回值解析+视图解析](https://www.cnblogs.com/summerday152/p/12856338.html)
  
  [随笔分类 - SpringMVC](https://www.cnblogs.com/xinhudong/category/1150740.html)
  
[TOC]

## SpringMVC基础
>> Spring MVC的核心是控制器的概念，这是一个处理请求并使用某种信息进行响应的类。
>> 对于面向浏览器的应用程序，控制器的响应方式是可选地填充模型数据并将请求传递给视图，以生成返回给浏览器的 HTML。
#### 1. SpringMVC简介
  ```markdown
MVC即模型-视图-控制器（Model-View-Controller）
Spring Web MVC是一种基于Java的实现了Web MVC设计模式的请求驱动类型的轻量级Web框架，即使用了MVC架构模式的思想，
将web层进行职责解耦，基于请求驱动指的就是使用请求-响应模型，框架的目的就是帮助我们简化开发，SpringWebMVC也是要简化我们日常Web开发的。
  ```
#### 2. SpringMVC运行原理
![SpringMVC运行原理步骤](https://img2018.cnblogs.com/blog/1363940/201910/1363940-20191031231647804-398588825.png)
  ```markdown
（1）：用户请求发送给DispatcherServlet，DispatcherServlet调用HandlerMapping处理器映射器；
（2）：HandlerMapping根据xml或注解找到对应的处理器，生成处理器对象返回给DispatcherServlet；
（3）：DispatcherServlet会调用相应的HandlerAdapter；
（4）：HandlerAdapter经过适配调用具体的处理器去处理请求，生成ModelAndView返回给DispatcherServlet
（5）：DispatcherServlet将ModelAndView传给ViewReslover解析生成View返回给DispatcherServlet；
（6）：DispatcherServlet根据View进行渲染视图；
->DispatcherServlet->HandlerMapping->Handler->DispatcherServlet->HandlerAdapter处理handler->ModelAndView
->DispatcherServlet->ModelAndView->ViewReslover->View ->DispatcherServlet->返回给客户
  ```
#### 3. 常用注解
  ```markdown
@Controller负责注册一个bean到spring上下文中
@RequestMapping 注解为控制器指定可以处理哪些URL请求
@RequestBody 该注解用于读取Request请求的body部分数据，使用系统默认配置的HttpMessageConverter进行解析，
  然后把相应的数据绑定到要返回的对象上 ,再把HttpMessageConverter返回的对象数据绑定到controller中方法的参数上
@ResponseBody 该注解用于将Controller的方法返回的对象，通过适当的HttpMessageConverter转换为指定格式后，写入到Response对象的body数据区
@ModelAttribute 在方法定义上使用 @ModelAttribute注解：Spring MVC在调用目标处理方法前，会先逐个调用在方法级上标注了@ModelAttribute的方法，
  在方法的入参前使用 @ModelAttribute 注解：可以从隐含对象中获取隐含的模型数据中获取对象，再将请求参数 –绑定到对象中，再传入入参将方法入参对象添加到模型中
@RequestParam　在处理方法入参处使用@RequestParam可以把请求参 数传递给请求方法
@PathVariable 绑定URL占位符到入参
@ExceptionHandler 注解到方法上，出现异常时会执行该方法
@ControllerAdvice 使一个Controller成为全局的异常处理类，类中用@ExceptionHandler方法注解的方法可以处理所有Controller发生的异常
  ```

##
[SpringMVC+Mybatis 如何配置多个数据源并切换？](https://www.cnblogs.com/haha12/p/10613549.html)
[SpringMVC之文件上传](https://www.cnblogs.com/huskysir/p/13170179.html)