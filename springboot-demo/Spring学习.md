# Spring学习
>> [Spring官网文档](https://docs.spring.io/spring-framework/docs/current/spring-framework-reference/core.html)
>> [Spring 实战（第四版）](https://potoyang.gitbook.io/spring-in-action-v4/)
>> [Spring实战(第五版)](https://potoyang.gitbook.io/spring-in-action-v5/)

[TOC]


## 一、Spring 
![可能需要知道的Spring](https://img2020.cnblogs.com/blog/1489208/202006/1489208-20200610141802146-901108964.png)
>> Spring的核心是一个容器，通常称为Spring应用程序上下文，用于创建和管理应用程序组件。这些组件（或 bean）在Spring应用程序上下文中连接在一起以构成一个完整的应用程序
>> 将bean连接在一起的行为是基于一种称为 依赖注入（DI）的模式。依赖项注入的应用程序不是由组件自身创建和维护它们依赖的其他bean的生命周期，
>> 而是依赖于单独的实体（容器）来创建和维护所有组件，并将这些组件注入需要它们的bean。通常通过构造函数参数或属性访问器方法完成此操作。
>> 除了其核心容器之外，Spring和完整的相关库产品组合还提供Web框架、各种数据持久性选项、安全框架与其他系统的集成、运行时监视、微服务支持、响应式编程模型以及许多其他功能，应用于现代应用程序开发。
### 1.Spring基础知识
[随笔分类 - Java工程师之Spring Framework深度剖析专栏](https://www.cnblogs.com/jimisun/category/1359910.html)
#### 1.1.重新来认识你的老朋友Spring框架
```markdown
Spring是一个开源框架，最早由Rod Johnson创建，是为了解决企业级应用开发的复杂性而创建的。很多框架都宣称在某些方面针对Java开发做出了简化，
但是Spring的目标是致力于全方位简化Java开发，这也是Spring的根本使命 "简化Java开发"。
    1. 基于POJO的轻量级和最小侵入性编程
        Spring竭力避免因自身的API而弄乱你的代码。Spring不会强迫你实现Spring规范的接口或继承Spring规范的类。
        相反，在一个基于Spring构建的应用中，应用中的类没有任何痕迹表明你使用了Spring。最坏的情况就是你在应用的类中发现了Spring的相关注解，但它依然是POJO。
        Spring通过IOC（Inversion of Control）管理这个POJO，然后通过DI（Dependency Inject）来注入他们。
    2. 通过依赖注入和面向接口实现松耦合
        紧密耦合同时会造成代码的难以测试，难以服用，难以理解，并且典型地表现出"打地鼠“式的Bug特性（修复一个Bug，将会出现一个或多个新Bug），所以我们可以知道耦合是必须的，但必须谨慎管理耦合
        DI（依赖注入）会将所依赖关系自动交给目标对象，而不是让对象本身创建所依赖对象。通过DI依赖注入可以将对象之间依赖关系轻松解耦
        使用Spring xml配置文件或者Spring @Configuration注解为容器依赖注入。容器可以控制对象的实例化。
    3. 基于切面和惯例进行声明式编程
        DI能够将互相协作的软件保持松耦合，而面向切面编程（aspect-oriented-programming，AOP）可以将遍布应用各处的功能分离出来形成可从用的组件。
    4. 通过切面和模板减少样版式代码
```
#### 1.2.Spring框架的七大模块
```markdown
Spring Core：框架的最基础部分，提供IoC容器，对bean进行管理。
Spring Context：继承BeanFactory，提供上下文信息，扩展出JNDI、EJB、电子邮件、国际化等功能。
Spring DAO：提供了JDBC的抽象层，还提供了声明性事务管理方法。
Spring ORM：提供了JPA、JDO、Hibernate、MyBatis 等ORM映射层.
Spring AOP：集成了所有AOP功能
Spring Web：提供了基础的Web开发的上下文信息，现有的Web框架，如JSF、Tapestry、Structs等，提供了集成
Spring Web MVC：提供了Web应用的Model-View-Controller全功能实现。
```
#### 1.2.Spring框架之IOC
```markdown
IOC是控制反转（Inversion of Control），将原POJO内部管理其他对象的引用转换为IOC容器统一管理对象引用，在需要使用的时候从容器获取Bean即可。
    将对象交给IOC容器统一管理，是为了更好使用DI（Dependency Inject，依赖注入）进行POJO之间依赖关系的解耦。
    DI（Dependency Inject，依赖注入）即代码里对象之间的依赖关系转移到容器中进行装配，这样能很灵活地通过面向接口进行编程。
Spring的IOC注入方式
    构造器注入 setter方法注入 注解注入 接口注入
```
#### Spring IOC初始化流程?
```markdown
resource定位即寻找用户定义的bean资源，
由ResourceLoader通过统一的接口Resource接口来完成beanDefinition载入BeanDefinitionReader读取、
解析Resource定位的资源 成BeanDefinition载入到ioc中（通过HashMap进行维护BD）BeanDefinition注册
即向IOC容器注册这些BeanDefinition，通过BeanDefinitionRegistery实现
BeanDefinition加载流程?
定义BeanDefinitionReader解析xml的document BeanDefinitionDocumentReader解析document成beanDefinition
```
#### 1.3.Bean定义5种作用域
>> singleton（单例） prototype（原型） request  session  global session
#### 1.3.Spring容器装配Bean的三种方式
```markdown
1.隐式的Bean发现机制和自动装配
    Spring从两个角度来实现自动化装配；组件扫描（Spring自动发现应用中上下文所需要的创建的Bean），自动装配（Spring自动满足Bean之间的依赖）
        1.使用@Component将普通Java类配置成SpringBean,除了这一注解还可以使用@Controller，@Service，@Repository等注解。
        2.使用@Autowired（自动装配）使Spring满足Bean的依赖
        3.配置组件扫描包（组件扫描）
            在Java类中配置组件扫描
                @ComponentScan("com.jimisun.controller")
            在XML配置文件配置组件扫描
                <context:component-scan base-package="com.jimisun.controller,com.jimisun.service"/>
2.在Java中进行装配
    1.使用@Bean注解将方法返回的实例对象添加到上下文中
    2.在@Bean返回的实例对象中可以通过构造器注入传入相关依赖
3.在XML中进行装配
    <bean class="com.jimisun.spring.example.User" id="user">
            <constructor-arg ref="myArticle"/>
    </bean>
    <bean class="com.jimisun.spring.example.MyArticle" id="myArticle"></bean>
值得一提的是，我们在项目中应该优先实用隐式的Bean发现机制和自动装配，其次使用在Java中进行装配，最后再使用在XML中进行装配。
```
```markdown
BeanDefinition是spring容器创建对象的模板，定义了bean创建的细节。
BeanFactoryPostProcessor可以拿到整个容器对象，当然也能修改BeanDefinition，所以能直接操作bean的创建。
BeanPostProcessor执行的时候bean已经创建完成了，我们可以拿到想要的对象进行干预和设值等操作。
```
#### 1.4.Spring核心概念之Bean生命周期管理
[Spring Bean各阶段生命周期的介绍](https://www.cnblogs.com/-beyond/p/13188675.html)
![Bean的生命周期](https://img2020.cnblogs.com/blog/848880/202006/848880-20200627155704205-940936200.png)
```markdown
BeanFactory是Spring的核心--容器，ApplicationContext则是包裹容器的上下文，丰富容器的功能（资源加载，事件驱动等）。FactoryBean也是Spring扩展性的提现。
BeanFactory已经定义了整个的生命周期，子类只是负责实现，demo演示也只是为了证实。我们更应该关注更上层的东西 
ApplicationContext是对容器更精细化的包装，提供了更完善的功能
FactoryBean是Spring扩展性的提现，可供用户自己定义创建bean。扩展性提炼的很好。
Bean生命周期
- 实例化Bean：Ioc容器通过获取BeanDefinition对象中的信息进行实例化，实例化对象被包装在BeanWrapper对象中
- 设置对象属性（DI）：通过BeanWrapper提供的设置属性的接口完成属性依赖注入；
- 注入Aware接口（BeanFactoryAware， 可以用这个方式来获取其它 Bean，ApplicationContextAware）：
    Spring会检测该对象是否实现了xxxAware接口，并将相关的xxxAware实例注入给bean
- BeanPostProcessor：自定义的处理（分前置处理和后置处理）
- InitializingBean和init-method：执行我们自己定义的初始化方法
- 使用
- destroy：bean的销毁
```
#### 1.5.Spring的依赖注入(DI)
```markdown
Spring利用反射创建对象，并将创建好的对象放入一个大工厂，实现了对象创建和使用的解耦。需要使用的时候可以方便的通过BeanFactory.getBean()获取。
在此之上还扩展了对注解的支持，使用注解就可以注入对象。
DI依赖注入流程? （实例化，处理Bean之间的依赖关系）
过程在Ioc初始化后，依赖注入的过程是用户第一次向IoC容器索要Bean时触发
- 如果设置lazy-init=true，会在第一次getBean的时候才初始化bean，lazy-init=false，会容器启动的时候直接初始化（singleton bean）；
- 调用BeanFactory.getBean()生成bean的；
- 生成bean过程运用装饰器模式产生的bean都是beanWrapper（bean的增强）；
依赖注入怎么处理bean之间的依赖关系?
其实就是通过在beanDefinition载入时，如果bean有依赖关系，通过占位符来代替，在调用getbean时候，如果遇到占位符，从ioc里获取bean注入到本实例来
```
#### 1.6.Bean循环依赖
```markdown
怎么检测是否存在循环依赖?
    - Bean在创建的时候可以给该Bean打标，如果递归调用回来发现正在创建中的话，即说明了循环依赖了。
Spring如解决Bean循环依赖问题?
Spring中循环依赖场景有：
    - 构造器的循环依赖
    - 属性的循环依赖
    - singletonObjects：第一级缓存，里面放置的是实例化好的单例对象；earlySingletonObjects：第二级缓存，里面存放的是提前曝光的单例对象；
        singletonFactories：第三级缓存，里面存放的是要被实例化的对象的对象工厂
    - 创建bean的时候Spring首先从一级缓存singletonObjects中获取。如果获取不到，并且对象正在创建中，就再从二级缓存earlySingletonObjects中获取，
        如果还是获取不到就从三级缓存singletonFactories中取（Bean调用构造函数进行实例化后，即使属性还未填充，就可以通过三级缓存向外提前暴露依赖的引用值（提前曝光），
        根据对象引用能定位到堆中的对象，其原理是基于Java的引用传递），取到后从三级缓存移动到了二级缓存完全初始化之后将自己放入到一级缓存中供其他使用，
    - 因为加入singletonFactories三级缓存的前提是执行了构造器，所以构造器的循环依赖没法解决。
    - 构造器循环依赖解决办法：在构造函数中使用@Lazy注解延迟加载。在注入依赖时，先注入代理对象，当首次使用时再创建对象说明：
        一种互斥的关系而非层次递进的关系，故称为三个Map而非三级缓存的缘由 完成注入；
```
#### Spring AOP核心概念
>> 传统oop开发代码逻辑自上而下的，这个过程中会产生一些横切性问题，这些问题与我们主业务逻辑关系不大，会散落在代码的各个地方，造成难以维护，
>> aop思想就是把业务逻辑与横切的问题进行分离，达到解耦的目的，提高代码重用性和开发效率；
```markdown
1、切面（aspect）：类是对物体特征的抽象，切面就是对横切关注点的抽象
2、横切关注点：对哪些方法进行拦截，拦截后怎么处理，这些关注点称之为横切关注点。
3、连接点（joinpoint）：被拦截到的点，因为 Spring 只支持方法类型的连接点，所以在Spring 中连接点指的就是被拦截到的方法，实际上连接点还可以是字段或者构造器。
4、切入点（pointcut）：对连接点进行拦截的定义
5、通知（advice）：所谓通知指的就是指拦截到连接点之后要执行的代码，通知分为前置、后置、异常、最终、环绕通知五类。
6、目标对象：代理的目标对象
7、织入（weave）：将切面应用到目标对象并导致代理对象创建的过程
8、引入（introduction）：在不修改代码的前提下，引入可以在运行期为类动态地添加方法或字段。
AOP 主要应用场景有：
    记录日志
    监控性能
    权限控制
    事务管理
AOP源码分析
    - @EnableAspectJAutoProxy给容器（beanFactory）中注册一个AnnotationAwareAspectJAutoProxyCreator对象；
    - AnnotationAwareAspectJAutoProxyCreator对目标对象进行代理对象的创建，对象内部，是封装JDK和CGlib两个技术，实现动态代理对象创建的
    （创建代理对象过程中，会先创建一个代理工厂，获取到所有的增强器（通知方法），将这些增强器和目标类注入代理工厂，再用代理工厂创建对象）；
    - 代理对象执行目标方法，得到目标方法的拦截器链，利用拦截器的链式机制，依次进入每一个拦截器进行执行
AOP使用哪种动态代理?
    - 当bean的是实现中存在接口或者是Proxy的子类，---jdk动态代理；不存在接口，spring会采用CGLIB来生成代理对象；
    - JDK动态代理主要涉及到 java.lang.reflect包中的两个类：Proxy和InvocationHandler。
    - Proxy利用InvocationHandler（定义横切逻辑） 接口动态创建 目标类的代理对象。
jdk动态代理
    - 通过bind方法建立代理与真实对象关系，通过Proxy.newProxyInstance（target）生成代理对象
    - 代理对象通过反射invoke方法实现调用真实对象的方法
动态代理与静态代理区别
    - 静态代理，程序运行前代理类的.class文件就存在了；
    - 动态代理：在程序运行时利用反射动态创建代理对象<复用性，易用性，更加集中都调用invoke>
CGLIB与JDK动态代理区别
    - Jdk必须提供接口才能使用；
    - Cglib不需要，只要一个非抽象类就能实现动态代理
```
#### 1.7.Spring中使用了哪些设计模式？
```markdown
- 工厂模式：spring中的BeanFactory就是简单工厂模式的体现，根据传入唯一的标识来获得bean对象；
- 单例模式：提供了全局的访问点BeanFactory；
- 代理模式：AOP功能的原理就使用代理模式（1、JDK动态代理。2、CGLib字节码生成技术代理。）
- 装饰器模式：依赖注入就需要使用BeanWrapper；
- 观察者模式：spring中Observer模式常用的地方是listener的实现。如ApplicationListener。
- 策略模式：Bean的实例化的时候决定采用何种方式初始化bean实例（反射或者CGLIB动态字节码生成）
```
###
[Spring IOC的核心机制：实例化与注入](https://www.cnblogs.com/zyjimmortalp/p/12828726.html)
[重新认识 Spring IOC](https://www.cnblogs.com/i-code/p/12832545.html)
[Spring系列之IOC的原理及手动实现](https://www.cnblogs.com/liyus/p/10112118.html)
[关于IOC容器的一些个人理解](https://www.cnblogs.com/HanJunJun-blog/p/10579712.html)
[关于Spring AOP，除了动态代理、CGLIB，你还知道什么？](https://mp.weixin.qq.com/s?__biz=MzI3NzE0NjcwMg==&mid=2650128800&idx=3&sn=3a3e9806348f3b280bdc5ee725238a20&chksm=f36bdc81c41c5597d0e206d637dd7bd986886212e38ae69fd506cc8488ab98bac5698204e9bc&mpshare=1&scene=23&srcid=&sharer_sharetime=1590070530809&sharer_shareid=d812adcc01829f0f7f8fb06aea118511#rd)
[AOP 技术原理——代理模式全面总结](https://www.cnblogs.com/kubixuesheng/p/5183782.html)
[JAVA-Spring AOP五大通知类型](https://www.cnblogs.com/xiaoluohao/p/11286242.html)
[聊聊在AOP模式下的缓存方案](https://www.cnblogs.com/lori/p/10602746.html)

[spring注入bean的几种策略模式](https://www.cnblogs.com/zyjimmortalp/p/12833761.html)

[Spring中资源的加载原来是这么一回事啊！](https://www.cnblogs.com/i-code/p/12845329.html)

[Spring 循环引用(三)源码深入分析版](https://www.cnblogs.com/burg-xun/p/12865205.html)

[一文读懂Spring中的DI和AOP](https://www.cnblogs.com/xiaoyao2011/p/12866456.html)

[谈谈Spring中的BeanPostProcessor接口](https://www.cnblogs.com/tuyang1129/p/12866484.html)

[Spring之BeanFactory和FactoryBean接口的区别](https://www.cnblogs.com/dengpengbo/p/10493782.html)

[Spring 事务管理详解](https://www.cnblogs.com/liantdev/p/10149443.html)
### Spring强化 -- 博客
#### 1.[SpringIoC BeanDefinition的加载和注册](https://www.cnblogs.com/leisurexi/p/12701046.html)
```markdown
SpringIOC容器如何加载Bean的定义信息：
    //声明bean工厂，然后通过指定的XML文件加载bean的定义元信息，最后通过bean工厂获取bean。
    DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
    XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
    reader.loadBeanDefinitions("bean-definition.xml");
    User user = beanFactory.getBean("user", User.class);
    System.err.println(user);//User{id=1, name='leisurexi', password='azxcfs', age=18, city=City{id=1, name='beijing'}}
2个核心类DefaultListableBeanFactory和XmlBeanDefinitionReader。
    1.XmlBeanDefinitionReader继承AbstractBeanDefinitionReader#loadBeanDefinitions方法#
        #方法#将资源文件转换为Resource对象，然后调用loadBeanDefinitions(Resource...) 加载 BeanDefinition 。
    2.XmlBeanDefinitionReader#loadBeanDefinitions方法#
        #方法#将Resource封装成EncodedResource ，也就是制定资源的编码和字符集。然后获取Resource的输入流InputStream ，
        并封装成InputSource设置其编码，最终调用doLoadBeanDefinitions开始真正的加载流程。
    3.XmlBeanDefinitionReader#doLoadBeanDefinitions方法#
        #方法#根据inputSource和resource加载XML文件，并封装成Document，用doc去解析和注册bean definition，
    4.XmlBeanDefinitionReader#registerBeanDefinitions方法#
        #方法#获取到本次注册的数量
    5.DefaultBeanDefinitionDoucumentReader#registerBeanDefinitions方法#
        #方法#提取root，注册BeanDefinition
```
#### 2.[SpringIoC bean的加载](https://www.cnblogs.com/leisurexi/p/13194515.html)
>> beanFactory.getBean() 方法就获取了在XML中定义的bean。
```markdown
FactoryBean的特殊之处在于它可以向容器中注册两个bean，一个是它本身，一个是FactoryBean.getObject() 法返回值所代表的bean。
beanName前面加上&获取的是FactoryBean本身，不加获取的getObject()返回的对象。
bean的加载
   1.AbstractBeanFactory#getBean方法# 
        getBean调用doGetBean方法(方法以do开头实际做操作的方法)，根据bean的名称和类型返回bean实例。
            1.转换对应的beanName->AbstractBeanFactory#transformedBeanName#
            2.尝试从单例缓存获取 bean->AbstractBeanFactory#getSingleton#
            3.获取 bean 实例对象->AbstractBeanFactory#getObjectForBeanInstance#
            4.合并 bean 定义元信息->AbstractBeanFactory#getMergedLocalBeanDefinition#
                主要是获取 MergedBeanDefinition ，主要步骤如下：
                1.首先从缓存中获取bean的MergedBeanDefinition，如果存在并且未过期直接返回。
                2.不存在或者已过期的MergedBeanDefinition，获取已经注册的BeanDefinition去作为顶级bean合并。
                3.bean没有parent(就是XML中的parent属性)，直接封装成RootBeanDefinition 。
                4.bean有parent，先去获取父MergedBeanDefinition，然后覆盖和合并与parent相同的属性。
                注意：这里只有abstract、scope、lazyInit、autowireMode、dependencyCheck、dependsOn 、factoryBeanName、factoryMethodName、
                initMethodName、destroyMethodName会覆盖，而constructorArgumentValues、propertyValues、methodOverrides 会合并。
                5.如果没有设置作用域，默认作用域为singleton 。
                6.缓存MergedBeanDefinition 。
            5.寻找依赖->DefaultSingletonBeanRegistry#isDependent#
                     ->DefaultSingletonBeanRegistry#registerDependentBean#
            6.创建和注册单例 bean->DefaultSingletonBeanRegistry#getSingleton#
getBean()方法流程，我们可以重新梳理一下思路：
    1.获取bean实际名称，如果缓存中存在直接取出实际bean返回。
    2.缓存中不存在，判断当前工厂是否有BeanDefinition，没有递归去父工厂创建bean。
    3.合并BeanDefinition，如果depends-on不为空，先去初始化依赖的bean。
    4.如果bean的作用域是单例，调用createBean()方法创建实例，这个方法会执行bean的其它生命周期回调，以及属性赋值等操作；
        接着执行单例bean创建前后的生命周期回调方法，并放入singletonObjects缓存起来。
    5.如果bean的作用域是原型，调用createBean()方法创建实例，并执行原型bean前后调用生命周期回调方法。
    6.如果bean的作用域是自定义的，获取对应的Scope对象，调用重写的get()方法获取实例，并执行原型bean前后调用生命周期回调方法。
    7.最后检查所需的类型是否与实际bean实例的类型匹配，如果不等进行转换，最后返回实例。
```
#### 3.[SpringIoC bean的创建](https://www.cnblogs.com/leisurexi/p/13196998.html)
```markdown
本篇文章主要介绍Spring IoC容器是怎么创建bean的实例。
if (mbd.isSingleton()) {
    // 创建和注册单例 bean
    sharedInstance = getSingleton(beanName, () -> {
        try {
            // 创建 bean 实例
            return createBean(beanName, mbd, args);
        }
       	// 省略异常处理...
    });
    bean = getObjectForBeanInstance(sharedInstance, name, beanName, mbd);
}
如果bean的作用域是单例，会调用getSingleton()方法并传入beanName和ObjectFactory作为参数；
而getSingleton()方法会调用ObjectFactory的getObject()方法也就是上面代码中的createBean()方法，返回bean。
这里的 ObjectFactory 是 bean 的延迟依赖查找接口，定义如下：
    @FunctionalInterface
    public interface ObjectFactory<T> {
        T getObject() throws BeansException;
    }
只有在调用getObject()方法时才会真正去获取bean。下面我们正式开始分析createBean()方法。
    1.调用createBean()->AbstractAutowireCapableBeanFactory#createBean#
    2.bean实例化前置处理->InstantiationAwareBeanPostProcessor接口继承于BeanPostProcessor
        BeanPostProcessor中定义了bean的初始化阶段生命周期回调方法，会在后续介绍）提供了三个扩展点，如下：
             bean 实例化前
             bean 实例化后
             bean 属性赋值前
             这也是bean实例化阶段的生命周期回调方法。
        判断bean在实例化之前是否已经解析过->AbstractAutowireCapableBeanFactory#resolveBeforeInstantiation#
    3.创建bean->AbstractAutowireCapableBeanFactory#doCreateBean#
        1).创建bean的实例->AbstractAutowireCapableBeanFactory#createBeanInstance#
        2).默认无参构造器实例化bean->AbstractAutowireCapableBeanFactory#instantiateBean#
        3).寻找合适的构造器实例化bean->ConstructorResolver#autowireConstructor#
            解析构造函数参数->ConstructorResolver#resolveConstructorArguments#
            创建参数数组->ConstructorResolver#createArgumentArray#判断构造函数如果有匹配的参数会转换成对应类型，如果没有匹配的参数，
                多半是构造函数自动注入，通过resolveAutowiredArgument()去查找bean并返回实例。
                ConstructorResolver#resolveAutowiredArgument#
        4).依赖解决->DefaultListableBeanFactory#resolveDependency#
            DefaultListableBeanFactory#doResolveDependency#
        5).BeanDefinition合并后处理->AbstractAutowireCapableBeanFactory#applyMergedBeanDefinitionPostProcessors#
            拿到所有注册的BeanPostProcessor，然后遍历判断是否是MeragedBeanDefinitionPostProcessor类型，是的话进行BeanDefinition合并后的方法回调，
            在这个回调方法内你可以对指定bean的BeanDefinition做一些修改。
本文主要介绍了创建bean实例的流程，我们可以重新梳理一下思路：
    1.进行bean的实例化前方法回调，如果返回非空，跳过后面步骤
    2.创建bean的实例，如果是构造函数注入会选择最适合的构造函数进行参数自动注入，否则调用默认的无参构造进行实例化bean。
```
#### 4.[SpringIoC 循环依赖的处理](https://www.cnblogs.com/leisurexi/p/13199726.html)
```markdown
什么是循环依赖?循环依赖就是循环引用，就是两个或多个bean相互之间的持有对方，比如A引用B，B引用A。
Spring IoC 容器对循环依赖的处理有三种情况：
    1.构造器循环依赖：此依赖Spring无法处理，直接抛出BeanCurrentlylnCreationException异常。
    2.单例作用域下的setter循环依赖：此依赖Spring通过三级缓存来解决。
    3.非单例的循环依赖：此依赖Spring无法处理，直接抛出BeanCurrentlylnCreationException异常。
构造器循环依赖：
    getSingleton()方法会判断是否是第一次创建该bean，如果是第一次会先去创建bean，也就是调用ObjectFacoty的getObject()方法，
        即调用createBean()方法创建bean前，会先将当前正要创建的bean记录在缓存singletonsCurrentlyInCreation中。
setter循环依赖：
    在创建A时发现依赖B，便先去创建B；B在创建时发现依赖A，此时A因为是通过构造函数创建，所以没创建完，便又去创建A，
        发现A存在于singletonsCurrentlyInCreation，即正在创建中，便抛出BeanCurrentlylnCreationException异常。
非单例循环依赖：
    对于非单例的bean，Spring容器无法完成依赖注入，因为Spring容器不进行缓存，因此无法提前暴露一个创建中的bean。
```
#### 5.[搞懂Spring代理创建及AOP链式调用过程](https://www.cnblogs.com/yewy/p/13199260.html)
```markdown
AOP也就是面向切面编程，它可以将公共的代码抽离出来，动态的织入到目标类、目标方法中，大大提高我们编程的效率，也使程序变得更加优雅。
如事务、操作日志等都可以使用AOP实现。这种织入可以是在运行期动态生成代理对象实现，也可以在编译期、类加载时期静态织入到代码中。
而Spring正是通过第一种方法实现，且在代理类的生成上也有两种方式：JDK Proxy和CGLIB，默认当类实现了接口时使用前者，否则使用后者；另外Spring AOP只能实现对方法的增强。
    AOP的术语很多，虽然不清楚术语我们也能很熟练地使用AOP，但是要理解分析源码，术语就需要深刻体会其含义。
        增强（Advice）：就是我们想要额外增加的功能
        目标对象（Target）：就是我们想要增强的目标类，如果没有AOP，我们需要在每个目标对象中实现日志、事务管理等非业务逻辑
        连接点（JoinPoint）：程序执行时的特定时机，如方法执行前、后以及抛出异常后等等。
        切点（Pointcut）：连接点的导航，我们如何找到目标对象呢？切点的作用就在于此，在Spring中就是匹配表达式。
        引介（Introduction）：引介是一种特殊的增强，它为类添加一些属性和方法。这样，即使一个业务类原本没有实现某个接口，
            通过AOP的引介功能，我们可以动态地为该业务类添加接口的实现逻辑，让业务类成为这个接口的实现类。
        织入（Weaving）：即如何将增强添加到目标对象的连接点上，有动态（运行期生成代理）、静态（编译期、类加载时期）两种方式。
        代理（Proxy）：目标对象被织入增强后，就会产生一个代理对象，该对象可能是和原对象实现了同样的一个接口（JDK），也可能是原对象的子类（CGLIB）。
        切面（Aspect、Advisor）：切面由切点和增强组成，包含了这两者的定义。
```
### 2.Spring源码
[读Spring源码，我们可以从第一行读起](https://blog.csdn.net/qq_41907991/article/details/105667900)
[spring源码](https://www.cnblogs.com/youzhibing/category/958792.html)
[由浅入深详细的介绍Spring框架的原理和源码](https://www.cnblogs.com/binghe001/category/1780611.html)
[当前标签：品Spring](https://www.cnblogs.com/lixinjie/tag/%E5%93%81Spring/)
#### 2.1.SpringIOC框架容器核心源码逐步剖析
[Spring Framework框架容器核心源码逐步剖析](https://www.cnblogs.com/jimisun/p/10104002.html)
```markdown

```
#### 2.2Spring bean的实例化过程
[Spring 源码学习 - 单例bean的实例化过程](https://www.cnblogs.com/hackingForest/p/13054173.html)
[我该如何学习spring源码以及解析bean定义的注册](https://www.cnblogs.com/liyus/p/10983108.html)

#### 2.3.Spring源码分析笔记--AOP
[Spring源码分析笔记--AOP](https://www.cnblogs.com/little-sheep/p/10103797.html)

[Spring拓展接口之FactoryBean，我们来看看其源码实现](https://www.cnblogs.com/youzhibing/p/10528821.html)
#### 2.4.Spring源码分析笔记--事务管理
[Spring源码分析笔记--事务管理](https://www.cnblogs.com/little-sheep/p/10115173.html)
[【面试】足够应付面试的Spring事务源码阅读梳理（建议珍藏）](https://www.cnblogs.com/lixinjie/p/a-enough-source-read-of-spring-tx-for-interview.html)

### 3.Spring注解
#### [【Spring注解驱动开发】聊聊Spring注解驱动开发那些事儿！](https://www.cnblogs.com/binghe001/p/13047333.html)
![Spring注解驱动开发](https://img2020.cnblogs.com/blog/1729473/202006/1729473-20200605000243595-700419751.jpg)
```markdown

```
#### Spring IOC相关常用注解
```markdown
自动扫描装配Bean的相关注解
    @Component   　将java类标记成一个Spring Bean组件
    @Service       将业务层实现类标记成一个Spring Bean组件
    @Controller    将表现层类标记成一个Spring Bean组件
    @Repository    将一个持久层实现类标记成一个Spring Bean组件
作用域相关注解
    ＠scope        用来指定bean的作用域，组件默认是单例的作用域，不使用单例则声明此注解
自动装配Bean相关注解
    @Autowired    通过类型匹配，通过名称则需要Spring的@Qualifier注解配合
    @Resource     根据Bean的名称去匹配，获取不到再根据类型
```
#### [聊聊依赖注入注解@Resource和@Autowired](https://www.cnblogs.com/felordcn/p/13063802.html)
>> @Resource和@Autowired注解都可以在Spring Framework应用中进行声明式的依赖注入。
```markdown
@Resource使用在成员属性和setter方法上。
    默认情况下按照名称注入，如果没有显式声明名称则按照变量名称或者方法中对应的参数名称进行注入。Qualifier约束，装配失败后异常。
@Autowired通常适用于构造函数，成员变量以及方法上。
    默认情况下是按照类型注入，其次是Qualifier约束，然后按照名称，最后需要判断是否required。
两者总结：
    @Resource和@Autowired的优先级顺序不同（参见上图），另外@Resource属于Jakarta EE规范而@Autowired属于Spring范畴，
    @Resource无法使用在构造参数中，@Autowired支持required属性。
    从面向对象来说，@Resource更加适用于多态性的细粒度注入，而@Autowired更多专注于多态的单例注入。
```
[Spring框架使用@Autowired自动装配引发的讨论](https://www.cnblogs.com/ibigboy/p/11236729.html)

[Spring Boot @Condition 注解，组合条件你知道吗](https://www.cnblogs.com/FraserYu/p/11280420.html)

[Spring中重要的注解](https://www.cnblogs.com/rolandlee/p/11014923.html)

[精进Spring—Spring常用注解](https://blog.csdn.net/u010648555/article/details/76299467)
#### [spring下应用@Resource, @Autowired 和 @Inject注解进行依赖注入的差异](https://www.cnblogs.com/both-eyes/p/10096882.html)
```markdown

```
#### Java配置类 @Configuration
```markdown
@Configuration注释向Spring 表明这是一个配置类，它将为Spring 应用程序上下文提供beans。
配置的类方法带有@Bean注释，指示它们返回的对象应作为beans添加到应用程序上下文中（默认情况下，它们各自的bean IDs将与定义它们的方法的名称相同）。
与基于XML的配置相比，基于Java的配置具有多个优点，包括更高的类型安全性和改进的可重构性。即使这样，仅当Spring无法自动配置组件时，才需要使用Java或XML进行显式配置。
```
#### @Conditional 
```markdown
@Conditional 将会通过 Condition 接口进行条件对比：
public interface Condition {
  boolean matches(ConditionContext ctxt, AnnotatedTypeMetadata metadata);
}
如果matches()方法返回true，那么就会创建带有@Conditional注解的bean。如果matches()方法返回false，将不会创建这些bean。
```
#### Spring @Transactional事务
```markdown

```

### 4.Spring框架应用
[从spring框架中的事件驱动模型出发，优化实际应用开发代码](https://www.cnblogs.com/l3306/p/10757291.html)
[Spring4+Springmvc+quartz实现多线程动态定时调度](https://www.cnblogs.com/alterem/p/11301235.html)
###

##