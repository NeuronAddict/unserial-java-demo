FROM bitnami/java:17 as build

COPY . .

RUN ./gradlew build --no-daemon


FROM bitnami/java:17

COPY --from=build build/libs/unserial-java-demo-0.0.1-SNAPSHOT.jar app.jar

CMD ["java", "-jar", "app.jar"]
