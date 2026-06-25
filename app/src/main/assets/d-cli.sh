#!/bin/bash

set -e

source "utils" 2>/dev/null

info 'Preparing...'
apt update

install() {
  info 'Installing DUB (Package manager)...'
  apt install -y dub

  info "Installing D compilers"

  read -r -p "Install GDC? (GCC-based D compiler) [y/n] " res
  case "$res" in
    y|Y|yes|Yes) install_gdc ;;
    *) echo "Skipping GDC installation..." ;;
  esac

  read -r -p "Install LDC? (LLVM-based D compiler) [y/n] " res
  case "$res" in
    y|Y|yes|Yes) install_ldc ;;
    *) echo "Skipping LDC installation..." ;;
  esac

  info 'D installed successfully.'
}

install_gdc() {
    info "Installing GDC..."
    apt install -y gdc build-essential
    info 'GDC installed successfully.'
}

install_ldc() {
    info "Installing LDC..."
    apt install -y ldc build-essential
    info 'LDC installed successfully.'
}

uninstall() {
  info 'Uninstalling D...'
  apt remove -y dub

  read -r -p "Uninstall GDC? [y/n] " res
  case "$res" in
    y|Y|yes|Yes) apt remove -y gdc ;;
    *) echo "Skipping GDC uninstallation..." ;;
  esac

  read -r -p "Uninstall LDC? [y/n] " res
  case "$res" in
    y|Y|yes|Yes) apt remove -y ldc ;;
    *) echo "Skipping LDC uninstallation..." ;;
  esac

  info 'D uninstalled successfully.'
}

update() {
  info 'Updating DUB...'
  apt install dub -y
  info 'DUB updated successfully.'
}

case "$1" in
  --uninstall) uninstall ;;
  --update) update ;;
  *) install ;;
esac