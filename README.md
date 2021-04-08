# Trusted Form - Android-Demo

## Setup 

1. Download and install newest Android Studio: https://developer.android.com/studio
2. On the initial `Welcome to Android Studio` screen: click `Configure > SDK Manager` option
3. On the `SDK Manager > SDK Platforms` tab: check `Android 10.0 (Q)` option, click `Apply` and follow the instructions if necessary.
![SDK Manager](/images/1-as-sdk-manager-1.png )

4. On the `SDK Manager > SDK Tools` tab: check the `Show Package Details` checkbox, select `Android SDK Build-Tools 30.0.2` and click `Apply`
5. Back on the `Welcome to Android Studio` click `Open an Exisiting Project` and select the Demo's folder
6. After the AS window loads click the 'Gradle Sync' button to set up all dependencies (highlighted on the screenshot below)
![Gradle sync](/images/2-as-sync.png)

## AVD Deployment
**Note: The following assumes you do NOT have an AVD preconfigured If you already have your AVD configured then skip to step 8**
7. Open the AVD Manager by clicking Tools > AVD Manager.
![Your AVDs](/images/3-avd-manager_2x.png)

8. Click Create Virtual Device, at the bottom of the AVD Manager dialog
   ** Note: The Select Hardware page appears.Notice that only some hardware profiles are indicated to include Play Store. This indicates that these profiles are fully CTS compliant and may use system images that include the Play Store app.**
9. Select a hardware profile, and then click Next. If you don't see the hardware profile you want, you can create or import a hardware profile.
![Create AVD](/images/4-avd-manager-device_2x.png)

  The System Image page appears. 
![AVD System Image](/images/5-avd-system-image.png) 

10. Select a system image for **API level 30** , and then click Next.
11. Change AVD properties as needed, and then click Finish.
    Click Show Advanced Settings to show more settings, such as the skin.
    The new AVD appears in the Your Virtual Devices page or the Select Deployment Target dialog.
![AVD](/images/6-avd-manager-system_2x.png)


## Physical Device 
  ** If you are deploying to AVD then skip step 12 **  
12. Connect your Android device through USB - you should be able to select it from Android Studio devices list (see screenshot). If it's not there you can try running the `Tools > Troubleshoot Device Connections` tool (aka `Connection Assistant`). You could also use an emulator, but on Android it's a bit tricky topic - let me know if you need help with setting one up.
![Physical Device](/images/7-as-physical-device.png)

13. You can build and run the project with the `Run` button:

![Run](/images/as-run.png)

Usually Android developers are aware of the above dependencies and know how to check them in the projects config (or Android Studio screams about them), so apart from using the TrustedForm SDK there's nothing special about our Demo project and in most cases it should run basically "out of the box"


