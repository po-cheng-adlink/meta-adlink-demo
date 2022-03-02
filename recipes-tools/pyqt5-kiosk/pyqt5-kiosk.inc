SUMMARY  = "Python Scripts for Adlink's pyqt5 kiosk framework"
DESCRIPTION = "Python scripts to run on target board to display a kiosk-like desktop environment"
HOMEPAGE = "https://github.com/ADLINK/pyqt5-kioskframe.git"
LICENSE = "LGPLv3.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/LGPL-3.0-or-later;md5=c51d3eef3be114124d11349ca0d7e117"

SRC_URI = "${SRCSERVER};branch=${SRCBRANCH}"
S = "${WORKDIR}/git"

RDEPENDS_${PN} = " \
	bash \
	cairo \
	libudev \
	${PYTHON_PN}-debugger \
	${PYTHON_PN}-threading \
	${PYTHON_PN}-datetime \
	${PYTHON_PN}-pickle \
	${PYTHON_PN}-jsonpatch \
	${PYTHON_PN}-pygobject \
	${PYTHON_PN}-dbus \
	${PYTHON_PN}-lxml \
	${PYTHON_PN}-pyudev \
	${PYTHON_PN}-pyserial \
	${PYTHON_PN}-psutil \
	${PYTHON_PN}-pyusb \
	${PYTHON_PN}-html \
	${PYTHON_PN}-pyqt5 \
	${PYTHON_PN}-pycairo \
	"

inherit setuptools3

REQUIRED_DISTRO_FEATURES = "systemd"

BBCLASSEXTEND = "nativesdk"

do_install_append() {
	install -d ${D}${systemd_unitdir}/system/
	install -d ${D}${systemd_unitdir}/system/dbus.target.wants/
	install -d ${D}${systemd_unitdir}/system/sockets.target.wants/
	install -d ${D}${systemd_unitdir}/system/multi-user.target.wants/
	install -d ${D}${sysconfdir}/profile.d/
	install -d ${D}${sysconfdir}/systemd/system/multi-user.target.wants/
	install -d ${D}${sbindir}

	# kiosk xml config file
	install -m 0644 ${S}/kiosk/kiosk.xml ${D}${sysconfdir}

	# kiosk installerd systemd service files
	install -m 0644 ${S}/kiosk/kioskd.service ${D}${systemd_unitdir}/system/
	ln -sf ${systemd_unitdir}/system/kioskd.service ${D}${sysconfdir}/systemd/system/multi-user.target.wants/kioskd.service

	# guiclientd.sh
	install -m 755 ${S}/kiosk/guiclientd.sh ${D}${sbindir}/guiclientd.sh

	# guiclientd.conf
	# DBUS_SESSION_BUS_ADDRESS=unix:path=/var/run/dbus/session_bus_socket
	# QT_QPA_PLATFORM="wayland"
	# XDG_RUNTIME_DIR="/run/user/0"
	echo "DBUS_SESSION_BUS_ADDRESS=unix:path=/var/run/dbus/session_bus_socket" > ${D}${sysconfdir}/systemd/guiclientd.conf
	echo "QT_QPA_PLATFORM=wayland" >> ${D}${sysconfdir}/systemd/guiclientd.conf
	echo "XDG_RUNTIME_DIR=/run/user/0" >> ${D}${sysconfdir}/systemd/guiclientd.conf

	# kiosk guiclientd systemd service files
	if [ "${@bb.utils.filter('DISTRO_FEATURES', 'wayland', d)}" ]; then
		sed -e 's,@ENV_FILE@,${sysconfdir}/systemd/guiclientd.conf,g' -i ${S}/kiosk/guiclientd.service
		sed -e 's,@EXEC_START@,${bindir}/python3 ${libdir}/python3.7/site-packages/kiosk/guiview.py,g' -i ${S}/kiosk/guiclientd.service
	else
		sed -e 's,@ENV_FILE@,,g' -i ${S}/kiosk/guiclientd.service
		sed -e 's,@EXEC_START@,/usr/sbin/guiclientd.sh,g' -i ${S}/kiosk/guiclientd.service
	fi
	install -m 0644 ${S}/kiosk/guiclientd.service ${D}${systemd_unitdir}/system/
	ln -sf ${systemd_unitdir}/system/guiclientd.service ${D}${sysconfdir}/systemd/system/multi-user.target.wants/guiclientd.service

	# dbus session socket
	install -m 0644 ${S}/kiosk/dbus-sess.socket ${D}${systemd_unitdir}/system/
	ln -sf ${systemd_unitdir}/system/dbus-sess.socket ${D}${systemd_unitdir}/system/dbus.target.wants/dbus-sess.socket
	ln -sf ${systemd_unitdir}/system/dbus-sess.socket ${D}${systemd_unitdir}/system/sockets.target.wants/dbus-sess.socket

	# dbus session service
	install -m 0644 ${S}/kiosk/dbus-sess.service ${D}${systemd_unitdir}/system/
	ln -sf ${systemd_unitdir}/system/dbus-sess.service ${D}${systemd_unitdir}/system/multi-user.target.wants/dbus-sess.service

	# export DBUS_SESSION_BUS_ADDDRESS=unix:path=/var/run/dbus/session_bus_socket through dbus-export.sh in /etc/profile
	install -m 0755 ${S}/kiosk/dbus-export.sh ${D}${sysconfdir}/profile.d/dbus-export.sh
}

FILES_${PN} += " \
	${sbindir}/guiclientd.sh \
	${sysconfdir}/kiosk.xml \
	${systemd_unitdir}/system/kioskd.service \
	${sysconfdir}/systemd/system/multi-user.target.wants/kioskd.service \
	${sysconfdir}/systemd/system/guiclientd.conf \
	${systemd_unitdir}/system/guiclientd.service \
	${sysconfdir}/systemd/system/multi-user.target.wants/guiclientd.service \
	${systemd_unitdir}/system/dbus-sess.socket \
	${systemd_unitdir}/system/dbus.target.wants/dbus-sess.socket \
	${systemd_unitdir}/system/sockets.target.wants/dbus-sess.socket \
	${systemd_unitdir}/system/dbus-sess.service \
	${systemd_unitdir}/system/multi-user.target.wants/dbus-sess.service \
	${sysconfdir}/profile.d/dbus-export.sh \
"

