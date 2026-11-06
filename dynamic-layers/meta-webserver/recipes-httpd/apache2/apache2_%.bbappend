include ${@bb.utils.contains('IMAGE_FEATURES', 'lamp', 'conf-setup.inc', '', d)}

APACHE2_TMPFILES_CONF ?= ""
APACHE2_TMPFILES_CONF:sota = "\
d /var/www 0755 root root -\n\
d /var/www/html 0755 root root -\n\
"

do_install:append () {
  if [ -n "${APACHE2_TMPFILES_CONF}" ]; then
    install -d ${D}${sysconfdir}/tmpfiles.d/
    echo "${APACHE2_TMPFILES_CONF}" | tee -a ${D}${sysconfdir}/tmpfiles.d/apache2-ostree.conf
  fi
}

FILES:${PN} += "${sysconfdir}/tmpfiles.d/"
