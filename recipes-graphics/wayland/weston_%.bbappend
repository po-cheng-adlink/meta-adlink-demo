FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

WESTON_BACKGROUND_IMAGE ?= "adlink.jpg"

SRC_URI += "file://${WESTON_BACKGROUND_IMAGE}"

do_install:append() {
   install ${WORKDIR}/${WESTON_BACKGROUND_IMAGE} ${D}${datadir}/weston
}

SRC_URI += " \
	${@bb.utils.contains('IMAGE_FEATURES', 'remote', 'file://0001-bump-libweston-backend-vnc-to-bump-to-v0.7.0.patch', '', d)} \
"

PACKAGECONFIG:append = " ${@bb.utils.contains('IMAGE_FEATURES', 'remote', 'vnc rdp', '', d)}"

FILES:${PN} += "${datadir/weston} ${sysconfdir}/pam.d/weston-remote-access"
