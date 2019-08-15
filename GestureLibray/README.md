# GestureLibray
###手势解锁库
####效果图：
#####设置与验证
![](http://7xkj5q.com1.z0.glb.clouddn.com/16-4-10/55183879.jpg)
#####修改与清除
![](http://7xkj5q.com1.z0.glb.clouddn.com/16-4-10/87889841.jpg)
####项目编译环境：Android studio
####密码说明:输入的密码添加了Base64加密
####属性说明
#####颜色与尺寸
* color_up_ring 未按下时九宫格按钮颜色
* color_on_ring 按下状态下圈的颜色
* color_error_ring 密码错误时的颜色
* outer_ring_spacing_width 宫格之间的间距（圆的大小会根据设置的间距动态适配）
* inner_ring_width 中间最小的圆的大小
* inner_background_width 中间透明的圆的大小
#####其他
* 设置是否显示绘制方向: setShow(true);/setShow(false);
* 设置密码允许输错次数: setErrorNumber(3);
* 设置密码最少长度:setPasswordMinLength(3);
* 设置密码后是否立即保存到本地:setSavePin(true);
* 设置保存到本地的key值:setSaveLockKey(“passwordkey”)；
* 设置模式:setMode(LockMode mode);
* 设置LockMode.CLEAR_PASSWORD模式下是否清除本地密码数据:setClearPasssword(boolean clearPasssword);

#####LockMode
* 设置密码:LockMode.SETTING_PASSWORD
* 修改密码:LockMode.EDIT_PASSWORD
* 验证密码:LockMode.VERIFY_PASSWORD
* 清除密码:LockMode.CLEAR_PASSWORD

####Gradle
Add this to your project's build.gradle file
```xml
dependencies {

   compile 'com.leo.gesturelibray:GestureLibray:1.0.9'

}
```
###Version：1.1.1

#####更新说明：
######Version：1.1.1
* 增加setClearPasssword(boolean clearPasssword); 用于判断LockMode.CLEAR_PASSWORD模式下是否清除密码。
* fix bug



