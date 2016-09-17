TARGETS = console-setup resolvconf mountkernfs.sh ufw screen-cleanup x11-common hostname.sh plymouth-log apparmor udev keyboard-setup cryptdisks cryptdisks-early networking hwclock.sh urandom checkroot.sh lvm2 iscsid checkfs.sh mountdevsubfs.sh open-iscsi checkroot-bootclean.sh bootmisc.sh mountnfs-bootclean.sh mountnfs.sh kmod mountall.sh mountall-bootclean.sh procps
INTERACTIVE = console-setup udev keyboard-setup cryptdisks cryptdisks-early checkroot.sh checkfs.sh
udev: mountkernfs.sh
keyboard-setup: mountkernfs.sh udev
cryptdisks: checkroot.sh cryptdisks-early udev lvm2
cryptdisks-early: checkroot.sh udev
networking: resolvconf mountkernfs.sh urandom procps
hwclock.sh: mountdevsubfs.sh
urandom: hwclock.sh
checkroot.sh: hwclock.sh keyboard-setup mountdevsubfs.sh hostname.sh
lvm2: cryptdisks-early mountdevsubfs.sh udev
iscsid: networking
checkfs.sh: cryptdisks checkroot.sh lvm2
mountdevsubfs.sh: mountkernfs.sh udev
open-iscsi: networking iscsid
checkroot-bootclean.sh: checkroot.sh
bootmisc.sh: checkroot-bootclean.sh mountnfs-bootclean.sh udev mountall-bootclean.sh
mountnfs-bootclean.sh: mountnfs.sh
mountnfs.sh: networking
kmod: checkroot.sh
mountall.sh: lvm2 checkfs.sh checkroot-bootclean.sh
mountall-bootclean.sh: mountall.sh
procps: mountkernfs.sh udev
