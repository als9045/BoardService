# 기본 이미지를 지정합니다.
FROM tomcat:latest

# Tomcat의 기본 웹 애플리케이션 디렉토리에 WAR 파일을 배포합니다.
COPY BoardService-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/

# Tomcat의 서버 포트를 8081로 설정합니다.
EXPOSE 8081

# Tomcat을 실행합니다.
CMD ["catalina.sh", "run"]
