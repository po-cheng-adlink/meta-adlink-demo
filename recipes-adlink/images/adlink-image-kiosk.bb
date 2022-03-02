# Copyright (C) 2022 ADLINK
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Small image capable of booting a device with a simple kiosk desktop env"

TOUCH = "tslib tslib-calibrate tslib-tests"
QTPLUGINS = "qtsvg-plugins qtbase-plugins"

# reassigned to RDEPNDS in image.bbclass
PACKAGE_INSTALL = " \
	pyqt5-kiosk \
	packagegroup-core-boot \
	${VIRTUAL-RUNTIME_base-utils} \
	kernel-modules \
	udev \
	mmc-utils \
	connman \
	${QTPLUGINS} \
	${@bb.utils.contains("DISTRO_FEATURES", "wayland", "qtwayland-plugins weston-init", "", d)} \
	${@bb.utils.contains("DISTRO_FEATURES", "x11 wayland", "weston-xwayland", "", d)} \
	${@bb.utils.contains("MACHINE_FEATURES", "touchscreen", "${TOUCH}", "", d)} \
	"

# remove unneeded packagegroups
IMAGE_INSTALL_remove = "packagegroup-adlink"

# Do not pollute the initrd image with rootfs features
IMAGE_FEATURES = "empty-root-password"
IMAGE_LINGUAS = ""
NO_RECOMMENDATIONS = "1"
LICENSE = "MIT"

inherit core-image

IMAGE_ROOTFS_SIZE = "0"
IMAGE_ROOTFS_EXTRA_SPACE = "0"
IMAGE_OVERHEAD_FACTOR = "1.0"

XZ_INTEGRITY_CHECK = "crc32"
