# Appium_Learning
This is for learning Appium at SDETPRO

*** Prepare Android Emulator ***
Step 1: Run SDK command lines
sdkmanager --list
sdkmanager "platforms;android-28" 
sdkmanager "system-images;android-28;default;x86"
sdkmanager "build-tools;28.0.3"
avdmanager create avd --name android_28 --package "system-images;android-28;default;x86
sdkmanager --update

Step 2: Run ADB command lines
adb -devices     - List all using devices
adb kill-server  - Kill server
adb start-server - Start server

Step 3: Run Emulator command lines
emulator -avd android_28  - Start Emulator

Step 4: Run Appium command lines
npm install -g appium  - Install Appium
appium                 - Starting Appium


*** How to running scripts ***
Step 1: Start ADB server
adb kill-server  - Kill server
adb start-server - Start server

Step 2: Start Appium (Open another Command Prompt window)
appium - Starting Appium

Step 3: Start Emulator (Open another Command Prompt window)
emulator -avd android_28  - Start Emulator

Step 4: Running scripts


*** How to using Appium inspector ***
Step 1: Start ADB server using command lines
adb kill-server  - Kill server
adb start-server - Start server

Step 2: Start Appium (Open other Command Prompt window)
appium - Starting Appium

Step 3: Start Emulator (Open other Command Prompt window)
emulator -avd android_28  - Start Emulator

Step 4: Open and using Appium Inspector (Link download: https://github.com/appium/appium-inspector/releases)

Step 5 (When debugging script and want to re-check element):
Start Appium server again and start session again on Appium Inspector
