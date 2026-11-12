OSTREE_TMPFILES_CONF ?= ""

do_install:append () {
    if [ "$(ls -A ${D}/opt)" ]; then
        # 1. Store default opt/* to /var/local/opt
        install -d ${D}/var/local/opt
        cp -R --no-dereference --preserve=mode,links -v ${D}/opt/* ${D}/var/local/opt/
        rm -rf ${D}/opt

        # 2. Install a tmpfiles.d rule to symlink /opt/* to /var/local/* at boot
        install -d ${D}${nonarch_libdir}/tmpfiles.d
        echo ${OSTREE_TMPFILES_CONF} | tee -a ${D}${nonarch_libdir}/tmpfiles.d/${PN}-ostree.conf
    fi
}

FILES:${PN} += "/var/local/opt/ ${nonarch_libdir}/tmpfiles.d/"
