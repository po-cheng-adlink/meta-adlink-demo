# Copyright 2021 ADLINK
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "ADLINK packagegroup for packacking tools for all ADLINK Distributions"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

PACKAGES = "packagegroup-adlink \
            packagegroup-adlink-sensors \
            packagegroup-adlink-benchmarks \
            packagegroup-adlink-wifi \
            packagegroup-adlink-bluetooth \
            packagegroup-adlink-tools \
            packagegroup-adlink-utils \
            packagegroup-adlink-debug \
            packagegroup-adlink-ci \
            packagegroup-adlink-net \
            packagegroup-adlink-bios \
"

#
# packagegroup-adlink contain stuff needed for adlink build images
#
RDEPENDS:packagegroup-adlink = " \
    packagegroup-adlink-tools \
    packagegroup-adlink-net \
    ${@bb.utils.contains('DISTRO_FEATURES', 'sensors', 'packagegroup-adlink-sensors', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'benchmarks', 'packagegroup-adlink-benchmarks', '', d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'wifi', 'packagegroup-adlink-wifi', '', d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'bluetooth', 'packagegroup-adlink-bluetooth', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'utils', 'packagegroup-adlink-utils', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'debug', 'packagegroup-adlink-debug', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'ci', 'packagegroup-adlink-ci', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'bios', 'packagegroup-adlink-bios', '', d)} \
"

#
# packages added by adlink sensors
#
SUMMARY:packagegroup-adlink-sensors = "Adlink Sensors Support"
RDEPENDS:packagegroup-adlink-sensors = " \
    lmsensors-fancontrol \
    lmsensors-libsensors \
    lmsensors-pwmconfig \
    lmsensors-sensord \
    lmsensors-sensors \
    lmsensors-sensorsconfconvert \
    lmsensors-sensorsdetect \
"

SUMMARY:packagegroup-adlink-benchmarks = "Adlink Benchmarks Support"
RDEPENDS:packagegroup-adlink-benchmarks = " \
    glmark2 \
    memtester \
    fio \
    iozone3 \
    lmbench \
    stress-ng \
    stressapptest \
    sysbench \
    phoronix-test-suite \
"

#
# packages added by adlink tools for wifi
#
SUMMARY:packagegroup-adlink-wifi = "Adlink wifi Support"
RDEPENDS:packagegroup-adlink-wifi = " \
    iperf3 \
    iw \
    rfkill \
    connman \
    wpa-supplicant \
    dhcpcd \
    kea \
    hostapd \
"

#
# packages added by adlink tools for bluetooth
#
SUMMARY:packagegroup-adlink-bluetooth = "Adlink bluetooth Support"
RDEPENDS:packagegroup-adlink-bluetooth = " \
    rfkill \
    bluez5 \
"

#
# packages added by adlink tools
#
PKG_TPM := "${@'packagegroup-security-tpm2' if 'meta-tpm' in d.getVar('BBLAYERS') else ''}"
PKG_SEMA := "${@'sema' if 'meta-adlink-sema' in d.getVar('BBLAYERS') else ''}"
SUMMARY:packagegroup-adlink-tools = "Adlink Tools Support"
RDEPENDS:packagegroup-adlink-tools = " \
    mraa \
    mraa-dev \
    mraa-doc \
    mraa-utils \
    upm \
    upm-dev \
    python3-upm \
    python3-mraa \
    ${PKG_SEMA} \
    ${PKG_TPM} \
"

SUMMARY:packagegroup-adlink-utils = "Adlink Utils Support"
RDEPENDS:packagegroup-adlink-utils = " \
    alsa-utils \
    alsa-tools \
    bash \
    bzip2 \
    pbzip2 \
    coreutils \
    cmake \
    cpufrequtils \
    curl \
    dmidecode \
    dtc \
    e2fsprogs-mke2fs \
    e2fsprogs-resize2fs \
    edid-decode \
    evtest \
    fbset \
    fb-test \
    fbida \
    git \
    gzip \
    haveged \
    hdparm \
    htop \
    i2c-tools \
    ifupdown \
    imagemagick \
    libstdc++ \
    libgpiod \
    make \
    mbw \
    minicom \
    mmc-utils \
    parted \
    picocom \
    python3 \
    spitools \
    v4l-utils \
    usbutils \
    wget \
    ${@bb.utils.contains('PACKAGE_CLASSES', 'package_rpm', 'dnf', '', d)} \
    ${@bb.utils.contains('IMAGE_FEATURES', 'ssh-server-openssh', 'packagegroup-core-ssh-openssh openssh openssh-sftp-server', '', d)} \
"

#
# packages added by adlink basic network tools
#
SUMMARY:packagegroup-adlink-net = "Adlink basic network tools"
RDEPENDS:packagegroup-adlink-net = " \
    dnsmasq \
    can-utils \
    libsocketcan \
    inetutils \
    iperf3 \
    iptables \
    iproute2 \
    iproute2-tc \
    bridge-utils \
    net-tools \
    ethtool \
    mdio-tools \
    mdio-netlink \
"

#
# packages added by adlink continuous integration
#
SUMMARY:packagegroup-adlink-ci = "Adlink Continuous Integration Support"
RDEPENDS:packagegroup-adlink-ci = " \
    python3 \
    python3-robotframework \
"

#
# packages added by adlink debugging support
#
SUMMARY:packagegroup-adlink-debug = "Adlink Debugging Support"
RDEPENDS:packagegroup-adlink-debug = " \
    gdb \
    lsof \
    strace \
    tcpdump \
    phytool \
    binutils \
"

#
# packages added by adlink bios support
#
AMI_TOOLS = ""
AMI_TOOLS:intel-corei7-64 = "afulnx scelnx"
SUMMARY:packagegroup-adlink-bios = "Adlink BIOS Support"
RDEPENDS:packagegroup-adlink-bios = " \
    flashrom \
    ${AMI_TOOLS} \
"

