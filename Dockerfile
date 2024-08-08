# Base image olarak OpenJDK 17 kullanın
FROM openjdk:17-jdk-slim

# Çalışma dizinini oluşturun ve ayarlayın
WORKDIR /app

# Maven wrapper ve kaynak dosyalarını kopyalayın
COPY .mvn/ .mvn
COPY mvnw .
COPY pom.xml .
COPY src ./src

# Maven kullanarak projeyi derleyin
RUN ./mvnw clean package -DskipTests

# JAR dosyasının adını ve yolunu belirtin
CMD ["java", "-jar", "target/poc-0.0.1-SNAPSHOT.jar"]


