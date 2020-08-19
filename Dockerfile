FROM oracle/graalvm-ce:20.1.0-java11 as graalvm
RUN gu install native-image

COPY . /home/app/words
WORKDIR /home/app/words

RUN native-image --no-server -cp build/libs/words-*-all.jar

FROM frolvlad/alpine-glibc
RUN apk update && apk add libstdc++
EXPOSE 8080
COPY --from=graalvm /home/app/words/words /app/words
ENTRYPOINT ["/app/words"]
