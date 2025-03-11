do_install:append () {
	bbnote "Enable sudo from /etc/sudoers"
	sed -i 's/^# %sudo/%sudo/g' ${D}${sysconfdir}/sudoers
}
