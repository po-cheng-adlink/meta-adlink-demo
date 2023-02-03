FILESEXTRAPATHS_prepend := "${THISDIR}/linux-yocto:"

SRC_URI_append = " file://enable_eth_i225.cfg"
SRC_URI_append = " file://0001-igc-Remove-_I_PHY_ID-checking.patch"
SRC_URI_append = " file://0001-igc-Remove-phy-type-checking.patch"

