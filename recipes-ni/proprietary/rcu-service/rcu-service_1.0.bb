SUMMARY = "RCU Service"
DESCRIPTION = "Recipe to install RCU Service into RCU image"

require rcu-service-src.inc
require rcu-service-cpp.inc

inherit systemd
SYSTEMD_AUTO_ENABLE = "enable"
SYSTEMD_SERVICE_${PN} = "rcu-service.service"

do_configure_append() {
         echo $NI_ATE_CORE_PRIVATE_KEY > ${S}/certs/ni_ate_core_private.key
}

do_install() {
         install -d ${D}${bindir}
         install -m 0755 rcu-service ${D}${bindir}
         install -d ${D}/${systemd_unitdir}/system
         install -m 0644 ${S}/rcu-service.service ${D}/${systemd_unitdir}/system
         install -d ${D}/${sysconfdir}/ssl/private
         install -m 0640 ${S}/certs/ni_ate_core_private.key ${D}/${sysconfdir}/ssl/private
}
