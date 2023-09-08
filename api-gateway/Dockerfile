FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY .mvn/ .mvn
COPY mvn pom.xml ./
RUN ./mvn dependency:resolve

COPY src ./src

CMD ["./mvn", "spring-boot:run"]