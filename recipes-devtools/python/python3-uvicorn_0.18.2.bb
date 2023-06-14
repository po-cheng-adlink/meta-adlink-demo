SUMMARY = "The lightning-fast ASGI server"
HOMEPAGE = "https://www.uvicorn.org/"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD-3-Clause;md5=550794465ba0ec5312d6919e203a55f9"

RDEPENDS_${PN} += "python3-h11"

inherit pypi setuptools3

SRC_URI[sha256sum] = "cade07c403c397f9fe275492a48c1b869efd175d5d8a692df649e6e7e2ed8f4e"

