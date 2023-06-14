SUMMARY = "FastAPI framework, high performance, easy to learn, fast to code, ready for production"
HOMEPAGE = "https://fastapi.tiangolo.com/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

RDEPENDS_${PN} += "\
    python3-pydantic \
    python3-starlette \
"

inherit pypi setuptools3

SRC_URI[sha256sum] = "66da43cfe5185ea1df99552acffd201f1832c6b364e0f4136c0a99f933466ced"

