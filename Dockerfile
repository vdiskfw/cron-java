# builder
FROM vdisk/maven-java21-graalvm:latest AS builder
ARG BUILDER_ROOTFS="/builder-rootfs"
WORKDIR /build
COPY ./pom.xml /build/pom.xml
COPY ./src/ /build/src/
RUN \
    --mount=type=cache,sharing=private,target=/root/.m2/repository \
    set -eux; \
    ls -lh /build/; \
    ls -lh /root/.m2/repository/; \
    mvn -Pnative native:compile -DskipTests;
RUN ls -lh /build/target/;
RUN mkdir -v -p "${BUILDER_ROOTFS}/usr/local/bin"
RUN cp "/build/target/cron-java" "${BUILDER_ROOTFS}/usr/local/bin/cron-java";
# hello-world demo
COPY context/hello-world.sh "/build/target/hello-world.sh"
RUN chmod -v 755 "/build/target/hello-world.sh";
RUN cp "/build/target/hello-world.sh" "${BUILDER_ROOTFS}/usr/local/bin/hello-world.sh";

# image
FROM vdisk/base-debian12:latest AS image
ARG BUILDER_ROOTFS="/builder-rootfs"
COPY --from=builder "${BUILDER_ROOTFS}" "/"
ENTRYPOINT []
CMD ["/usr/local/bin/cron-java"]
