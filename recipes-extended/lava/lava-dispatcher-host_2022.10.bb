LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0-only;md5=801f80980d171dd6425610833a22dbe6"
HOMEPAGE = "https://github.com/Linaro/lava.git"

include lava_2022.10.bb

SRCSERVER = "git://GitLab.Adlinktech.com/EV/lava.git"
SRCBRANCH = "2022.10"
SRCOPTIONS = ";protocol=http"

#
# packagegroup-lava contain libraries/tools needed for lava-dispatcher-host
#
RDEPENDS_${PN} += "python3 ${lava-dispatcher-host-rdepends} lava-common python3-uvicorn python3-fastapi"

DISTUTILS_BUILD_ARGS = "lava-dispatcher-host"

do_install_append () {
	# enable the lava-dispatcher-host socket/service
	install -d ${D}${sysconfdir}/systemd/system/sockets.target.wants/
	install -d ${D}${sysconfdir}/systemd/system/multi-user.target.wants/
	ln -sf ${systemd_unitdir}/system/lava-dispatcher-host.socket ${D}${sysconfdir}/systemd/system/sockets.target.wants/lava-dispatcher-host.socket
	ln -sf ${systemd_unitdir}/system/lava-dispatcher-host.service ${D}${sysconfdir}/systemd/system/multi-user.target.wants/lava-dispatcher-host.service
	ln -sf ${systemd_unitdir}/system/lava-dispatcher-rest.service ${D}${sysconfdir}/systemd/system/multi-user.target.wants/lava-dispatcher-rest.service
}

FILES_${PN} = "${sysconfdir} ${libdir} ${datadir} ${bindir} ${base_libdir} ${localstatedir}"

