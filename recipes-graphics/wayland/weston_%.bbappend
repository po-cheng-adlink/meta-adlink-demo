SUMMARY= "Weston, a Wayland compositor"
DESCRIPTION= "Include ADLINK wallpaper .jpg image to replace weston desktop wallpaper"
LICENSE= "CLOSED"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://adlink.jpg"

do_install:append() {

   install ${WORKDIR}/adlink.jpg ${D}${datadir}/weston
}

FILES:${PN} += "${datadir/weston}"
