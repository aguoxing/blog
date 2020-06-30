# Dockerfile
# VERSION 0.0.1
# Author：codexing
# 基础镜像
FROM jdk8
# 声明一个挂载点，容器内此路径会对应宿主机的某个文件夹
VOLUME /tmp
# 应用构建成功后的jar文件被复制到镜像内，名字改为app.jar
COPY blog.jar app.jar

RUN bash -c "touch /app.jar"
# 启动容器时的进程   ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]
ENTRYPOINT ["java","-jar","app.jar"]
# 暴露8088的端口 对外端口
EXPOSE 8088