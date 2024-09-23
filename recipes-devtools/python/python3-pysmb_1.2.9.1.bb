SUMMARY = "pysmb is an experimental SMB/CIFS library written in Python to support file sharing between Windows and Linux machines."
DESCRIPTION = "pysmb is an experimental SMB/CIFS library written in Python. It implements the client-side SMB/CIFS protocol which allows your Python application to access and transfer files to/from SMB/CIFS shared folders like your Windows file sharing and Samba folders."
HOMEPAGE = "https://miketeo.net/blog/projects/pysmb"

LICENSE = "Zlib"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Zlib;md5=87f239f408daca8a157858e192597633"

inherit python3-dir python_poetry_core

WHL_BPN="${@ d.getVar('BPN').replace(d.getVar('PYTHON_PN')+'-', '', 1)}"
WHL_BP="${@ d.getVar('BP').replace(d.getVar('PYTHON_PN')+'-', '', 1)}"
WHL_PN="${@ d.getVar('PN').replace(d.getVar('PYTHON_PN')+'-', '', 1)}"

#
# download URL from PYPI:
# https://files.pythonhosted.org/packages/93/67/75acf7972a8056cfb216f389d0fbda62f6d691fab4ca1a26c1a0d6ecd9e7/pysmb-1.2.9.1.zip
#
SRC_URI = "https://files.pythonhosted.org/packages/93/67/75acf7972a8056cfb216f389d0fbda62f6d691fab4ca1a26c1a0d6ecd9e7/${WHL_BP}.zip"

SRC_URI[sha256sum] = "ad613988d54b1317ca0466dc3546f47b2dddea16e645d755d29fb75a86903326"

DEPENDS += "unzip-native"

do_copy_dir () {
  cp -rf ${WORKDIR}/${WHL_BP}/* ${WORKDIR}/python3-${WHL_BP}
}
addtask copy_dir before do_patch after do_unpack

RDEPENDS:${PN} += "python3-tqdm"

