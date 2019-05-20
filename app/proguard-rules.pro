-dontwarn com.google.**
-dontwarn io.blackbox_vision.materialcalendarview.**
-dontwarn com.crashlytics.**
## New rules for EventBus 3.0.x ##
# http://greenrobot.org/eventbus/documentation/proguard/

-keepattributes *Annotation*
-keepclassmembers class ** {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }


 -keep class cn.pedant.SweetAlert.Rotate3dAnimation {
    public <init>(...);
 }