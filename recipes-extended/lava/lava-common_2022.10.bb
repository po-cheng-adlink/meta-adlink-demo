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
RDEPENDS_${PN} += "${lava-common-rdepends}"

DISTUTILS_BUILD_ARGS = "lava-common"

FILES_${PN} = "${sysconfdir} ${libdir} ${datadir} ${bindir} ${base_libdir} ${localstatedir}"

