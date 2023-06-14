SUMMARY = "The little ASGI library that shines"
HOMEPAGE = "https://github.com/encode/starlette"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD-3-Clause;md5=550794465ba0ec5312d6919e203a55f9"

RDEPENDS_${PN} += "\
    python3-anyio \
"

inherit pypi setuptools3

SRC_URI[sha256sum] = "e1904b5d0007aee24bdd3c43994be9b3b729f4f58e740200de1d623f8c3a8870"

