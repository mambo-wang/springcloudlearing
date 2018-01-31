
1、服务消费者（rest + ribbon）
写一个测试类HelloService，通过之前注入ioc容器的restTemplate来消费service-hi服务的“/hi”接口，
在这里我们直接用的程序名替代了具体的url地址，在ribbon中它会根据服务名来选择具体的服务实例，
根据服务实例在请求的时候会用具体的url替换掉服务名

在浏览器上多次访问http://localhost:8764/hi?name=forezp，浏览器交替显示：
hi forezp,i am from port:8762
hi forezp,i am from port:8763
这说明当我们通过调用restTemplate.getForObject(“http://SERVICE-HI/hi?name=“+name,String.class)方法时，
已经做了负载均衡，访问了不同的端口的服务实例。
ribbon是一个负载均衡客户端，可以很好的控制htt和tcp的一些行为。Feign默认集成了ribbon。

2、断路器（Hystrix）
在微服务架构中，根据业务来拆分成一个个的服务，服务与服务之间可以相互调用（RPC），
在Spring Cloud可以用RestTemplate+Ribbon和Feign来调用。为了保证其高可用，单个服务通常会集群部署。
由于网络原因或者自身的原因，服务并不能保证100%可用，如果单个服务出现问题，调用这个服务就会出现线程阻塞，
此时若有大量的请求涌入，Servlet容器的线程资源会被消耗完毕，导致服务瘫痪。
服务与服务之间的依赖性，故障会传播，会对整个微服务系统造成灾难性的严重后果，这就是服务故障的“雪崩”效应。

为了解决这个问题，业界提出了断路器模型。

较底层的服务如果出现故障，会导致连锁故障。
当对特定的服务的调用的不可用达到一个阀值（Hystric 是5秒20次） 断路器将会被打开。