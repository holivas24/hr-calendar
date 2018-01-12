#!/bin/bash

if [[ $EUID -ne 0 ]]; then
   echo "This script must be run as root" 1>&2
   exit 1
fi

read -p 'Numero de instancia: ' INSTANCE

add-apt-repository ppa:webupd8team/java
apt-get update
apt-get install oracle-java8-installer mysql-server git python apache2
cd /opt
echo "Instalando Play framework 1.3.4"
git clone https://github.com/holivas24/play.git
cd /opt/hr-calendar
echo "Instalando servicio hrcalendar"
cp hr-calendar hrcalendar$INSTANCE
cp hrcalendar$INSTANCE /usr/sbin
rm hrcalendar$INSTANCE
chmod 755 /usr/sbin/hrcalendar$INSTANCE
echo "Crear base de datos: CREATE DATABASE hrcalendar;"
mysql -u root -p
chmod 755 restoreDB.sh
sh restoreDB.sh
echo "El equipo se reiniciara para finalizar la instalacion"
hrcalendar$INSTANCE restart
reboot