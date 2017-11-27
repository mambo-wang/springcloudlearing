写一个测试类HelloService，通过之前注入ioc容器的restTemplate来消费service-hi服务的“/hi”接口，
在这里我们直接用的程序名替代了具体的url地址，在ribbon中它会根据服务名来选择具体的服务实例，
根据服务实例在请求的时候会用具体的url替换掉服务名

在浏览器上多次访问http://localhost:8764/hi?name=forezp，浏览器交替显示：

hi forezp,i am from port:8762
hi forezp,i am from port:8763
这说明当我们通过调用restTemplate.getForObject(“http://SERVICE-HI/hi?name=“+name,String.class)方法时，
已经做了负载均衡，访问了不同的端口的服务实例。

ribbon是一个负载均衡客户端，可以很好的控制htt和tcp的一些行为。Feign默认集成了ribbon。