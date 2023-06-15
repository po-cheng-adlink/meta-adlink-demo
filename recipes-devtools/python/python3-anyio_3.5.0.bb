SUMMARY = "High level compatibility layer for multiple asynchronous event loop implementations"
HOMEPAGE = "https://github.com/agronholm/anyio"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit pypi setuptools3

RDEPENDS_${PN} += "python3-sniffio"

do_compile_prepend () {
        sed -e "s|use_scm_version=True|version='${PV}'|g" -i ${S}/setup.py
        sed -e "s|setuptools_scm|setuptools|g" -i ${S}/setup.py
}

SRC_URI[sha256sum] = "a0aeffe2fb1fdf374a8e4b471444f0f3ac4fb9f5a5b542b48824475e0042a5a6"

