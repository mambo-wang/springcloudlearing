存在问题：
（1）已实现Config Server的/bus/refresh端点来实现配置的刷新，Config Server配置的label要和client一致
（2）已实现Config Server的{application}占位符支持，应用名称应该和git的仓库名称一致
（3）未实现Zuul代理的文件上传
