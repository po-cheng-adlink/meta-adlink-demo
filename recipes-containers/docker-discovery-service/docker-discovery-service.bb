SUMMARY = "Package a systemd docker-discovery.service for avahi container"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI = "file://docker-discovery.service.template"

DOCKER_COMPOSE_FILE ?= "docker-compose.yml"

# setup dependency to docker-datapart's do_deploy task
do_install[depends] = "docker-datapart:do_deploy"
do_compile[noexec] = "1"

do_install () {
	# add the service to systemd
	install -d ${D}${systemd_unitdir}/system/
	install -d ${D}${sysconfdir}/systemd/system/multi-user.target.wants/
	install -m 0644 ${WORKDIR}/docker-discovery.service.template ${D}${systemd_unitdir}/system/docker-discovery.service
	sed -e "s|\@DOCKER_COMPOSE_FILE\@|${DOCKER_COMPOSE_FILE}|g" -i ${D}${systemd_unitdir}/system/docker-discovery.service
	# enable the service
	ln -sf ${systemd_unitdir}/system/docker-discovery.service ${D}${sysconfdir}/systemd/system/multi-user.target.wants/docker-discovery.service
}

FILES_${PN} += "${sysconfdir}/systemd/system/multi-user.target.wants/ ${systemd_unitdir}/system/"
