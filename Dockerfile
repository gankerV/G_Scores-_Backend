# Sử dụng image Java
FROM openjdk:17-jdk-slim

# Tạo thư mục app trong container
WORKDIR /app

# Copy file JAR vào container
COPY target/G_Scores-0.0.1-SNAPSHOT.jar app.jar

# Mở port (Render sẽ tự set PORT)
EXPOSE 8080

# Chạy ứng dụng Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]
