#!/bin/bash
exit 0 # Not implemented

set -e

source "$LOCAL/bin/utils"

info 'Preparing...'
apt update && apt upgrade -y

get_arch() {
  case "$(uname -m)" in
    x86_64)
      echo "x86_64"
      ;;
    aarch64 | arm64)
      echo "aarch64"
      ;;
    *)
      error "Unsupported architecture: $(uname -m)"
      exit 1
      ;;
  esac
}

install() {
  info 'Installing D...'
  info 'D installed successfully.'
}

uninstall() {
  info 'Uninstalling D...'
  info 'D installed successfully.'
}

update() {
  info 'Updating D...'

  if [ ! -x "$INSTALL_DIR/dlang" ]; then
    error "D is not installed."
    exit 1
  fi

  "$INSTALL_DIR/typst" update

  info 'Typst updated successfully.'
  exit 0
}

case "$1" in
  --uninstall) uninstall ;;
  --update) update ;;
  *) install ;;
esac