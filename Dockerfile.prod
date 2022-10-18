# BUILDER IMAGE
FROM gradle:7.4-jdk-alpine as Builder
WORKDIR /build

COPY build.gradle settings.gradle /build/
COPY .env /build/

RUN gradle build -x test --parallel --continue > /dev/null 2>&1 || true

COPY . /build
RUN gradle build -x test --parallel

# BASE IMAGE
FROM eclipse-temurin:11-alpine
WORKDIR /build
COPY --from=Builder /build/build/libs/*.jar app.jar

EXPOSE 8080
RUN chmod +x app.jar