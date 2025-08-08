FROM docker:dind
RUN apk add --update util-linux shadow e2fsprogs && rm -rf /var/cache/apk/*
ADD entry.sh /entry.sh
RUN chmod a+x /entry.sh

VOLUME /docker-partition
ENV DOCKER_HOST=unix:///var/run/docker.sock

CMD /entry.sh
