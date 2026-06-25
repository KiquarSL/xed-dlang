# D Support

Adds support D language.

Supported D compilers:
1. GDC (GCC-based)
2. LDC (LLVM-based)

## Installation

Install the extension through the Xed-Editor's extension marketplace, and you're ready to go! Alternatively, you can download the latest release ZIP file and install it via Settings > Extensions > Install from storage.

Check installed
```bash
# Package manager
dub --help

# Selected compiler
gdc --help
ldc --help
```

## Usage

Make directory and open.
```bash
# Generate project
dub init

# Run
dub run

# Run with diffrent compilers
dub run --compiler=gdc
dub run --compiler=ldc
```

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