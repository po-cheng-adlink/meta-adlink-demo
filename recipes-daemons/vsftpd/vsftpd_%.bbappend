do_install:append () {
    sed -e 's,root,#root,g' -i ${D}${sysconfdir}/vsftpd.ftpusers
    sed -e 's,root,#root,g' -i ${D}${sysconfdir}/vsftpd.user_list
}

