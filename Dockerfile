# 기본 이미지를 지정합니다.
FROM tomcat:latest

# Tomcat의 기본 웹 애플리케이션 디렉토리에 WAR 파일을 배포합니다.
COPY target/BoardService-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/

# Tomcat의 기본 포트를 노출합니다.
EXPOSE 8080

# Tomcat을 실행합니다.
CMD ["catalina.sh", "run"]
