SUMMARY = "Linaro Automated Validation Architecture"
SECTION = "devel"
DESCRIPTION = "LAVA is an automated validation architecture primarily aimed at testing deployments of systems based around the Linux kernel on ARM devices, specifically ARMv7 and later."

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0-only;md5=801f80980d171dd6425610833a22dbe6"
HOMEPAGE = "https://github.com/Linaro/lava.git"


SRCSERVER ?= "git://github.com/Linaro/lava.git"
SRCBRANCH ?= "master"
SRCREV ?= "68545ed6ed16cff397e1ccbe62e588a835f35da7"
SRCOPTIONS ?= ";protocol=https"
SRCOPTIONS_append_private = ";user=${ADLINK_USER}:${ADLINK_TOKEN}"

SRC_URI = "${SRCSERVER};branch=${SRCBRANCH}${SRCOPTIONS}"
S = "${WORKDIR}/git"

# runtime dependent libraries/tools needed for lava-common
lava-common-rdepends = " \
    python3-pip \
    python3-pyyaml \
"
#    python3-voluptuous (>=0.8.8)

lava-coordinator-rdepends = ""

# runtime dependent libraries/tools needed for lava-dispatcher
lava-dispatcher-rdepends = " \
    bmap-tools \
    python3-aiohttp \
    python3-configobj \
    python3-jinja2 \
    python3-magic \
    python3-netifaces \
    python3-pexpect (>=4.2) \
    python3-pip \
    python3-pyudev (>=0.21) \
    python3-requests \
    python3-pytz \
    python3-pyyaml \
"
#    python3-guestfs
#    python3-setproctitle (>=1.1.8)

# runtime dependent libraries/tools needed for lava-dispatcher-host
lava-dispatcher-host-rdepends = " \
    python3-pip \
    python3-pyudev (>=0.21) \
    python3-requests \
"

# runtime dependent libraries/tools needed for lava-server
lava-server-rdepends = " \
    python3-aiohttp \
    python3-django (>=1.10) \
    python3-djangorestframework \
    python3-docutils (>=0.6) \
    python3-jinja2 \
    python3-requests \
    python3-simplejson \
    python3-pip \
    python3-pytz \
    python3-pyyaml \
    python3-pyzmq \
"
#    python3-celery
#    python3-django-allauth
#    python3-django-auth-ldap (>=1.2.12)
#    python3-django-environ
#    python3-django-filters
#    python3-django-tables2 (>=1.14.2)
#    python3-djangorestframework-extensions
#    python3-djangorestframework-filters
#    python3-eventlet
#    python3-junit.xml (>=1.8)
#    python3-psycopg2
#    python3-tap
#    python3-voluptuous (>=0.8.8)
#    python3-whitenoise

# runtime dependent libraries/tools needed for lava
RDEPENDS_${PN} += " \
    ${lava-common-rdepends} \
    ${lava-dispatcher-rdepends} \
    ${lava-dispatcher-host-rdepends} \
    ${lava-server-rdepends} \
"

#
# Python builds using setuptools3
#
inherit features_check
inherit setuptools3
REQUIRED_DISTRO_FEATURES = "systemd"

DISTUTILS_BUILD_ARGS = ""
DISTUTILS_INSTALL_ARGS_append = " ${DISTUTILS_BUILD_ARGS}"

do_compile_prepend () {
    sed -e "s,version =.*,version = ${PV},g" -i ${S}/setup.cfg
    sed -e "s,max-line-length,max_line_length,g" -i ${S}/setup.cfg
}

FILES_${PN} = "${sysconfdir} ${libdir} ${datadir} ${bindir} ${base_libdir} ${localstatedir}"

