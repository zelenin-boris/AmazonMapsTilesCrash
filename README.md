# AmazonMapsTilesCrash
https://forums.developer.amazon.com/forums/thread.jspa?threadID=9617


This app reproduces crashes of Amazon Maps on android device. Tested on Amazon Kindle Fire HD 7" Tablet 8GB - Wi-Fi (SQ46CW).

APK file - https://github.com/zelenin-boris/AmazonMapsTilesCrash/blob/master/app/crash.apk?raw=true

### Crash 1
##### Emulates removing TileOverlay while loading tiles. Instant crash.

```
12-14 17:23:35.744 17400-17450/com.crash W/Maps-Polaris: [1;33m[******************************** ABORT ********************************][0m
12-14 17:23:35.744 17400-17450/com.crash W/Maps-Polaris: [1;33m        File: jni/layers/UPNTileOverlayProviderAndroid.cpp[0m
12-14 17:23:35.744 17400-17450/com.crash W/Maps-Polaris: [1;33m        Line: 50[0m
12-14 17:23:35.744 17400-17450/com.crash W/Maps-Polaris: [1;33m    Function: virtual UPNTileOverlayGetTileResult UPNTileOverlayProviderAndroid::getTile(long int, int, int, int, UPNByteBuffer*)[0m
12-14 17:23:35.744 17400-17450/com.crash W/Maps-Polaris: [0;33m      Reason: Exception from java caught while trying JNI call: 
                                                         java.lang.NullPointerException: Attempt to invoke interface method 'boolean com.amazon.geo.mapsv2.model.TileProviderWrapper.isUrlTileProvider()' on a null object reference
                                                             com.amazon.geo.mapsv2.model.TileOverlayManager.getTile(TileOverlayManager.java:318)[0m
12-14 17:23:35.763 17400-17450/com.crash I/Maps-Polaris: *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ***
12-14 17:23:35.763 17400-17450/com.crash I/Maps-Polaris:           #00  pc 004c60d0  /system/lib/libpolaris_v2.so
12-14 17:23:35.764 17400-17450/com.crash I/Maps-Polaris:           #01  pc 004c6108  /system/lib/libpolaris_v2.so (_pabort(char const*, int, char const*, char const*, ...)+28x)
12-14 17:23:35.764 17400-17450/com.crash I/Maps-Polaris:           #02  pc 00296338  /system/lib/libpolaris_v2.so (UPNTileOverlayProviderAndroid::getTile(long, int, int, int, UPNByteBuffer*)+556x)
12-14 17:23:35.764 17400-17450/com.crash I/Maps-Polaris:           #03  pc 00334ce8  /system/lib/libpolaris_v2.so (UPNTileOverlayAppDropProvider::getTile(DropId const&, UPNByteBuffer*) const+100x)
12-14 17:23:35.764 17400-17450/com.crash I/Maps-Polaris:           #04  pc 00421ee8  /system/lib/libpolaris_v2.so (UPNDownloader::performTileOverlayRequest(UPNDownloadRequest*)+328x)
12-14 17:23:35.764 17400-17450/com.crash I/Maps-Polaris:           #05  pc 00428578  /system/lib/libpolaris_v2.so (UPNDownloader::executeRequest(std::unique_ptr<UPNDownloadRequest, std::default_delete<UPNDownloadRequest> >, bool)+1424x)
12-14 17:23:35.764 17400-17450/com.crash I/Maps-Polaris:           #06  pc 00434dec  /system/lib/libpolaris_v2.so (UPNDownloaderPool::runThread(UPNDownloader*)+248x)
12-14 17:23:35.765 17400-17450/com.crash I/Maps-Polaris:           #07  pc 00503044  /system/lib/libpolaris_v2.so
12-14 17:23:35.765 17400-17450/com.crash I/Maps-Polaris:           #08  pc 000164e2  /system/lib/libc.so
12-14 17:23:35.765 17400-17450/com.crash I/Maps-Polaris:           #09  pc 00014502  /system/lib/libc.so
12-14 17:23:35.765 17400-17450/com.crash W/Maps-Polaris: [1;33m[***********************************************************************][0m
12-14 17:23:35.766 17400-17450/com.crash A/libc: Fatal signal 11 (SIGSEGV), code 1, fault addr 0xdecafbad in tid 17450 (Thread-2272)
```

### Crash 2
##### Emulates TileOverlay scheduled refresh. Crash after 10 - 15 refreshes. Tested with 3, 10, 30 and 60 seconds timer.

```
12-14 16:22:29.597 20214-20214/com.crash E/App: Start crash2()
12-14 16:22:29.611 20214-20214/com.crash E/App: Refresh 1
12-14 16:23:29.613 20214-20214/com.crash E/App: Refresh 2
12-14 16:24:29.619 20214-20214/com.crash E/App: Refresh 3
12-14 16:25:29.622 20214-20214/com.crash E/App: Refresh 4
12-14 16:26:29.624 20214-20214/com.crash E/App: Refresh 5
12-14 16:27:29.629 20214-20214/com.crash E/App: Refresh 6
12-14 16:27:30.225 488-1179/? I/ActivityManager: Process com.amazon.ags.app (pid 20738) has died
12-14 16:27:30.505 488-503/? I/ActivityManager: Process com.amazon.device.messaging (pid 20774) has died
12-14 16:27:30.644 488-712/? I/ActivityManager: Process android.process.acore (pid 18705) has died
12-14 16:27:30.694 488-1178/? I/ActivityManager: Process com.amazon.identity.auth.device.authorization (pid 20421) has died
12-14 16:28:29.631 20214-20214/com.crash E/App: Refresh 7
12-14 16:28:30.095 488-536/? I/ActivityManager: Process com.amazon.venezia (pid 20165) has died
12-14 16:28:30.157 488-1115/? I/ActivityManager: Process com.amazon.kindleautomatictimezone (pid 23275) has died
12-14 16:28:30.247 488-504/? I/ActivityManager: Process com.android.defcontainer (pid 20134) has died
12-14 16:28:30.437 488-1115/? I/ActivityManager: Process com.amazon.platform.fdrw (pid 20700) has died
12-14 16:28:30.439 488-711/? I/ActivityManager: Process com.amazon.device.settings (pid 17878) has died
12-14 16:28:30.517 488-1105/? I/ActivityManager: Process com.amazon.avod (pid 23506) has died
12-14 16:28:30.624 488-503/? I/ActivityManager: Process com.amazon.alta.h2clientservice (pid 23568) has died
12-14 16:29:29.636 20214-20214/com.crash E/App: Refresh 8
12-14 16:30:29.642 20214-20214/com.crash E/App: Refresh 9
12-14 16:30:30.236 488-536/? I/ActivityManager: Process com.amazon.kindle.cms (pid 17858) has died
12-14 16:30:30.396 488-701/? I/ActivityManager: Process com.amazon.whisperlink.core.android (pid 20318) has died
12-14 16:30:30.435 488-1109/? I/ActivityManager: Process com.audible.application.kindle (pid 18453) has died
12-14 16:30:30.495 488-711/? I/ActivityManager: Process android.process.media (pid 19897) has died
12-14 16:30:30.534 488-504/? I/ActivityManager: Process com.amazon.kindleautomatictimezone (pid 27476) has died
12-14 16:30:30.779 488-701/? I/ActivityManager: Process com.amazon.firelauncher (pid 17812) has died
12-14 16:30:41.854 488-1105/? I/ActivityManager: Process com.amazon.device.settings (pid 28110) has died
12-14 16:31:29.648 20214-20214/com.crash E/App: Refresh 10
12-14 16:31:30.087 488-1179/? I/ActivityManager: Process com.amazon.whisperlink.core.android (pid 28077) has died
12-14 16:31:30.204 488-1178/? I/ActivityManager: Process android.process.media (pid 28498) has died
12-14 16:32:11.145 488-711/? I/ActivityManager: Process com.amazon.device.settings (pid 29523) has died
12-14 16:32:11.295 488-1179/? I/ActivityManager: Process com.amazon.whisperlink.core.android (pid 29499) has died
12-14 16:32:29.654 20214-20214/com.crash E/App: Refresh 11
12-14 16:33:29.661 20214-20214/com.crash E/App: Refresh 12
12-14 16:33:30.205 488-1060/? I/ActivityManager: Process android.process.media (pid 30720) has died
12-14 16:33:30.705 488-536/? I/ActivityManager: Process com.nuance.swype.input (pid 17985) has died
12-14 16:33:30.751 488-1100/? I/ActivityManager: Process com.nuance.swype.input:SwypeConnect (pid 18029) has died
12-14 16:33:30.884 488-1178/? I/ActivityManager: Process com.amazon.platform (pid 21962) has died
12-14 16:34:29.668 20214-20214/com.crash E/App: Refresh 13
12-14 16:34:30.024 488-503/? I/ActivityManager: Process com.amazon.device.settings (pid 30916) has died
12-14 16:34:30.304 488-711/? I/ActivityManager: Process com.amazon.platform (pid 30897) has died
12-14 16:34:30.376 488-536/? I/ActivityManager: Process com.here.odnp.service:remote (pid 19289) has died
12-14 16:34:41.519 488-1105/? I/ActivityManager: Process com.here.odnp.service:remote (pid 31770) has died
12-14 16:34:51.624 488-711/? I/ActivityManager: Process com.amazon.kindleautomatictimezone (pid 31935) has died
12-14 16:34:52.135 488-536/? I/ActivityManager: Process com.amazon.client.metrics (pid 17833) has died
12-14 16:34:52.954 488-701/? I/ActivityManager: Process com.amazon.client.metrics (pid 32052) has died
12-14 16:34:53.084 488-701/? I/ActivityManager: Process com.amazon.device.settings (pid 32020) has died
12-14 16:34:53.126 488-1178/? I/ActivityManager: Process com.amazon.whisperlink.core.android (pid 31993) has died
12-14 16:35:11.134 488-712/? I/ActivityManager: Process com.amazon.device.settings (pid 32436) has died
12-14 16:35:11.174 488-701/? I/ActivityManager: Process com.amazon.platform (pid 32407) has died
12-14 16:35:22.136 488-1178/? I/ActivityManager: Process com.here.odnp.service:remote (pid 32670) has died
12-14 16:35:29.693 20214-20214/com.crash E/App: Refresh 14
12-14 16:35:30.249 488-504/? I/ActivityManager: Process com.amazon.client.metrics (pid 32430) has died
12-14 16:35:30.284 488-701/? I/ActivityManager: Process com.amazon.device.backup (pid 18822) has died
12-14 16:35:30.305 488-504/? I/ActivityManager: Process com.amazon.geo.mapsv2.services (pid 20266) has died
12-14 16:35:30.830 488-1179/? I/ActivityManager: Process com.crash (pid 20214) has died
```
