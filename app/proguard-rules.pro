# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\Users\Administrator\AppData\Local\Android\sdk/tools/proguard/proguard-android.txt
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
-keep class com.baidu.** {*;}
-keep class vi.com.** {*;}
-dontwarn com.baidu.**

-dontwarn com.taobao.**
-dontwarn anet.channel.**
-dontwarn anetwork.channel.**
-dontwarn org.android.**
-dontwarn org.apache.thrift.**
-dontwarn com.xiaomi.**
-dontwarn com.huawei.**

-keepattributes *Annotation*

-keep class com.taobao.** {*;}
-keep class org.android.** {*;}
-keep class anet.channel.** {*;}
-keep class com.umeng.** {*;}
-keep class com.xiaomi.** {*;}
-keep class com.huawei.** {*;}
-keep class org.apache.thrift.** {*;}

-keep class com.alibaba.sdk.android.**{*;}
-keep class com.ut.**{*;}
-keep class com.ta.**{*;}

-keep public class **.R$*{
   public static final int *;
}

#（可选）避免Log打印输出
-assumenosideeffects class android.util.Log {
   public static *** v(...);
   public static *** d(...);
   public static *** i(...);
   public static *** w(...);
 }

     -keep public interface com.facebook.**
     -keep public interface com.tencent.**
     -keep public interface com.umeng.socialize.**
     -keep public interface com.umeng.socialize.sensor.**
     -keep public interface com.umeng.scrshot.**
     -keep class com.android.dingtalk.share.ddsharemodule.** { *; }
     -keep public class com.umeng.socialize.* {*;}


     -keep class com.facebook.**
     -keep class com.facebook.** { *; }
     -keep class com.umeng.scrshot.**
     -keep public class com.tencent.** {*;}
     -keep class com.umeng.socialize.sensor.**
     -keep class com.umeng.socialize.handler.**
     -keep class com.umeng.socialize.handler.*
     -keep class com.umeng.weixin.handler.**
     -keep class com.umeng.weixin.handler.*
     -keep class com.umeng.qq.handler.**
     -keep class com.umeng.qq.handler.*
     -keep class UMMoreHandler{*;}
     -keep class com.tencent.mm.sdk.modelmsg.WXMediaMessage {*;}
     -keep class com.tencent.mm.sdk.modelmsg.** implements   com.tencent.mm.sdk.modelmsg.WXMediaMessage$IMediaObject {*;}
     -keep class im.yixin.sdk.api.YXMessage {*;}
     -keep class im.yixin.sdk.api.** implements im.yixin.sdk.api.YXMessage$YXMessageData{*;}
     -keep class com.tencent.mm.sdk.** {
      *;
     }
     -keep class com.tencent.mm.opensdk.** {
    *;
     }
     -dontwarn twitter4j.**
     -keep class twitter4j.** { *; }

     -keep class com.tencent.** {*;}
     -dontwarn com.tencent.**
     -keep public class com.umeng.com.umeng.soexample.R$*{
     public static final int *;
     }
     -keep public class com.linkedin.android.mobilesdk.R$*{
     public static final int *;
         }
     -keepclassmembers enum * {
     public static **[] values();
     public static ** valueOf(java.lang.String);
     }

     -keep class com.tencent.open.TDialog$*
     -keep class com.tencent.open.TDialog$* {*;}
     -keep class com.tencent.open.PKDialog
     -keep class com.tencent.open.PKDialog {*;}
     -keep class com.tencent.open.PKDialog$*
     -keep class com.tencent.open.PKDialog$* {*;}

     -keep class com.sina.** {*;}
     -dontwarn com.sina.**
     -keep class  com.alipay.share.sdk.** {
        *;
     }
     -keepnames class * implements android.os.Parcelable {
     public static final ** CREATOR;
     }

     -keep class com.linkedin.** { *; }
     -keepattributes Signature