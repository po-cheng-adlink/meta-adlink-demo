# Add background setting to [shell] section and update chromium launcher

WESTON_BACKGROUND_IMAGE ?= "adlink.jpg"

update_file() {
    if ! grep -q "$1" $3; then
        bbfatal $1 not found in $3
    fi
    sed -i -e "s,$1,$2," $3
}

update_background() {
	bbnote "Replace $1 to $2 in $3"
	update_file "\[shell\]" "\[shell\]\nbackground-image=/usr/share/weston/${WESTON_BACKGROUND_IMAGE}\nbackground-type=scale" ${D}${sysconfdir}/xdg/weston/weston.ini
}

do_install:append() {
	case "${MACHINE}" in
	lec-*)
		update_background
		;;
	sp2-*)
		update_background
		;;
	esac
}

