SUMMARY = "Package a systemd pdu-startup.service to turn off power relays"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI = "file://pdu-startup.service.template"

do_compile[noexec] = "1"

do_install () {
	# add the service to systemd
	install -d ${D}${systemd_unitdir}/system/
	install -d ${D}${sysconfdir}/systemd/system/multi-user.target.wants/
	install -m 0644 ${WORKDIR}/pdu-startup.service.template ${D}${systemd_unitdir}/system/pdu-startup.service
	# enable the service
	ln -sf ${systemd_unitdir}/system/pdu-startup.service ${D}${sysconfdir}/systemd/system/multi-user.target.wants/pdu-startup.service
}

FILES_${PN} += "${sysconfdir}/systemd/system/multi-user.target.wants/ ${systemd_unitdir}/system/"
