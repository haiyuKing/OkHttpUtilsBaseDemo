# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\software\Android\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

#=====================okhttputils框架=====================
#====okhttputils====
#android Studio环境中不需要，eclipse环境中需要
#-libraryjars libs/okhttputils.jar
-dontwarn com.zhy.http.**
-keep class com.zhy.http.**{*;}
-keep interface com.zhy.http.**{*;}

#====okhttp====
#android Studio环境中不需要，eclipse环境中需要
#-libraryjars libs/okhttp-2.7.0.jar
-dontwarn okhttp3.**
-keep class okhttp3.**{*;}
-keep interface okhttp3.**{*;}

-keepattributes Signature
-keepattributes *Annotation*
-dontwarn com.squareup.okhttp.**
-keep class com.squareup.okhttp.**{*;}
-keep interface com.squareup.okhttp.**{*;}

#====okio====
#android Studio环境中不需要，eclipse环境中需要
#-libraryjars libs/okio-1.6.0.jar
-dontwarn java.nio.file.*
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
-dontwarn okio.**
-keep class okio.**{*;}
-keep interface okio.**{*;}

#====gson====
#android Studio环境中不需要，eclipse环境中需要
#-libraryjars libs/gson-2.2.4.jar
-keep class sun.misc.Unsafe{*;}
-dontwarn com.google.gson.**
-keep class com.google.gson.**{*;}
-keep class com.google.gson.stream.**{*;}
#这一段包名应该是你所有的java bean　定义的目录
-keep class com.google.gson.examples.android.model.**{*;}