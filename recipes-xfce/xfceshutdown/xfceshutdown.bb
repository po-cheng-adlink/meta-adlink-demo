LICENSE = "CLOSED"

# shutdown for xfce4
SRC_URI="file://shutdown.desktop"


S = "${WORKDIR}"

# Tasks
do_install() {
    
        install -d -m 0755 ${D}/usr/share/applications

        cp -a ${WORKDIR}/shutdown.desktop ${D}/usr/share/applications
       
}


python populate_packages:prepend (){
}

do_package() {
}

do_package_write_rpm() {
}

do_package_qa() {
}
do_populate_packages() {
}

FILES:${PN} += "${D}/usr/share/applications/"

#INSANE_SKIP:${PN} = "already-stripped dev-so"
#INSANE_SKIP:${PN} = "already-stripped"
