SUMMARY = "Barcode scanning SDK for Python"
DESCRIPTION = "The Python Barcode SDK is a wrapper for Dynamsoft C++ Barcode SDK. \
It comes with all the general features of Dynamsoft Barcode Reader, bringing \
convenience for Python developers."

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI[sha256sum] = "493f9508ec28486377cbf16b5f3f3d6e12c10fb11539e3042ed3825e01869e7d"

inherit pypi setuptools3

INSANE_SKIP:${PN} = "already-stripped"

