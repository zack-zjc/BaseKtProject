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

-optimizationpasses 7
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontpreverify
-verbose
#添加java关键字做混淆字典
#-obfuscationdictionary proguard-dictionary.txt
#-classobfuscationdictionary proguard-dictionary.txt
#-packageobfuscationdictionary proguard-dictionary.txt
-keeppackagenames java.util.**
-dontwarn java.util.**
-dontwarn sun.reflect.**
-dontwarn sun.misc.**
-dontwarn org.apache.log4j.**
-dontwarn gov.nist.javax.sip.**
-dontwarn javax.**
-dontwarn org.joda.**
-dontwarn org.w3c.dom.**
-dontwarn com.jcraft.**
-dontwarn sun.security.**
-dontwarn org.jaudiotagger.**
-dontwarn java.nio.file.**
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
# 混淆采用的算法
-optimizations !code/simplification/cast,!field/*,!class/merging/*
# 保留注解等不混淆
-keepattributes Exceptions,InnerClasses,Deprecated,Signature,SourceFile,LineNumberTable,*Annotation*,EnclosingMethod
# dump.txt文件列出apk包内所有class的内部结构
-dump class_files.txt
# seeds.txt文件列出未混淆的类和成员
-printseeds seeds.txt
# usage.txt文件列出从apk中删除的代码
-printusage unused.txt
# mapping.txt文件列出混淆前后的映射
-printmapping mapping.txt
#设置源文件中给定的字符串常量
-renamesourcefileattribute SourceFile
#优化时允许访问并修改类和类的成员的 访问修饰符，可能作用域会变大
-mergeinterfacesaggressively
#过度加载，多个属性和方法使用相同的名字，只是参数和返回类型不同 可能各种异常
-overloadaggressively
# For retrolambda
-dontwarn java.lang.invoke.*
-keep class org.json.** {*;}
-dontwarn org.bouncycastle.jsse.BCSSLParameters
-dontwarn org.bouncycastle.jsse.BCSSLSocket
-dontwarn org.bouncycastle.jsse.provider.BouncyCastleJsseProvider
-dontwarn org.conscrypt.Conscrypt$Version
-dontwarn org.conscrypt.Conscrypt
-dontwarn org.conscrypt.ConscryptHostnameVerifier
-dontwarn org.openjsse.javax.net.ssl.SSLParameters
-dontwarn org.openjsse.javax.net.ssl.SSLSocket
-dontwarn org.openjsse.net.ssl.OpenJSSE
#关闭 Log日志
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
    public static *** i(...);
    public static *** e(...);
    public static *** w(...);
}
# 保留所有的本地native方法不被混淆
-keepclasseswithmembernames class * {
    native <methods>;
}
# 保留在Activity中的方法参数是view的方法，从而我们在layout里面编写onClick就不会被影响
-keepclassmembers class * extends android.app.Activity {
    public void *(android.view.View);
}
# 枚举类不能被混淆
-keepclassmembers enum * {
public static **[] values();
public static ** valueOf(java.lang.String);
}
# 保留Parcelable序列化的类不被混淆
-keep class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}
# 保留Serializable序列化的类不被混淆
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
# 对于R（资源）下的所有类及其方法，都不能被混淆
-keep class **.R$* {
    *;
}
# webview混淆
-keepclassmembers class fqcn.of.javascript.interface.for.webview {
    public *;
}
-keepclassmembers class * extends android.webkit.webViewClient {
    public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
    public boolean *(android.webkit.WebView, java.lang.String);
}
-keepclassmembers class * extends android.webkit.webViewClient {
    public void *(android.webkit.webView, jav.lang.String);
}
#避免混淆自定义控件类的 get/set 方法和构造函数
-keep public class * extends android.view.View{
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
