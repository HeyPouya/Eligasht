# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
-keep class cz.msebera.android.httpclient.** { *; }
-keep class com.loopj.android.http.** { *; }
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
-keep public class ir.eligasht.eligashttest.POJOs.*
-keep public class ir.eligasht.eligashttest.POJOs.**
-dontwarn okhttp3.**
-dontwarn okio.**
-keep class sun.misc.Unsafe { *; }

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}
-keepclassmembers class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator CREATOR;
}
-keepattributes Signature
# Retain declared checked exceptions for use by a Proxy instance.
-keepattributes Exceptions

-dontwarn javax.annotation.Nullable
-dontwarn javax.annotation.ParametersAreNonnullByDefault
-keep public class com.google.android.gms.* { public *; }
-dontwarn com.google.android.gms.**
-keepattributes SourceFile,LineNumberTable
-keepattributes *Annotation*
 -keep class com.google.gson.** { *; }
 -keep class uk.co.senab.photoview.** { *; }
 -dontwarn uk.co.senab.photoview.**
 -dontwarn com.squareup.okhttp.**
 # Platform calls Class.forName on types which do not exist on Android to determine platform.
 -dontnote retrofit2.Platform
 # Platform used when running on Java 8 VMs. Will not be used at runtime.
 -dontwarn retrofit2.Platform$Java8
 # Retain generic type information for use by reflection by converters and adapters.
 -keepattributes Signature
 # Retain declared checked exceptions for use by a Proxy instance.
 -keepattributes Exceptions
-dontwarn javax.annotation.**
-dontwarn am.appwise.components.ni.*