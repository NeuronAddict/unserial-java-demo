FROM bitnami/java:11 as build

RUN mkdir -p /src

WORKDIR /src

COPY gradlew gradlew
COPY gradle/ gradle/

COPY build.gradle build.gradle
COPY settings.gradle settings.gradle

RUN ./gradlew downloadDependencies --no-daemon --info

COPY src/ src/

RUN ./gradlew build --no-daemon --info


FROM bitnami/java:11-prod

COPY --from=build /src/build/libs/unserial-java-demo-*-SNAPSHOT.jar app.jar

CMD ["java", "-jar", "app.jar"]
