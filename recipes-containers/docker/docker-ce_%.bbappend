FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += " \
file://docker.service.template \
file://docker.init.template \
"

DOCKER_PARTITION_MOUNT_PATH ?= "/var/lib/docker"

MOUNT_PREFIX = ""

do_install_append () {
	if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
		install -m 644 ${WORKDIR}/docker.service.template ${D}/${systemd_unitdir}/system/docker.service
		sed -e "s|\@DOCKER_PARTITION_MOUNT_PATH\@|${MOUNT_PREFIX}${DOCKER_PARTITION_MOUNT_PATH}|g" -i ${D}/${systemd_unitdir}/system/docker.service
	else
		install -m 0755 ${WORKDIR}/docker.init.template ${D}${sysconfdir}/init.d/docker.init
		sed -e "s|\@DOCKER_PARTITION_MOUNT_PATH\@|${MOUNT_PREFIX}${DOCKER_PARTITION_MOUNT_PATH}|g" -i ${D}${sysconfdir}/init.d/docker.init
	fi
}
