# D Support

Adds support D language.

Todo:
- [ ] Downloading language and package manager
- [ ] Downloading language server (serde-d)
- [x] Support syntax highlight for .d files

### Installation

Install the extension through the Xed-Editor's extension marketplace, and you're ready to go! Alternatively, you can download the latest release ZIP file and install it via Settings > Extensions > Install from storage.

## Build

Debug build:
```bash
./gradlew assembleDebug
./gradlew :app:createFinalZip
```

Release build:
```bash
./gradlew assembleRelease
./gradlew :app:createFinalZip
```