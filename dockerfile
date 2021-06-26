FROM openjdk:15 as builder
COPY . .
RUN ./gradlew installDist

FROM openjdk:15
COPY --from=builder /app/build/install .
WORKDIR /app/bin/
CMD ./app