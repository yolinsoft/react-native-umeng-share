# react native友盟分享插件

目前支持微信、微博和qq分享，安卓版已完工，IOS版正在开发中

## 自动安装

> npm install react-native-umeng-share --save

## 手动安装

### android

* 在 ```android/setting.gradle``` 中添加

```
include ':react-native-umeng-share'
project(':react-native-umeng-share').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-umeng-share/android')
```

* 在 ```android/app/build.gradle``` 中添加

```
dependencies {
    ...
    compile project(':react-native-umeng-share')
    ...
}
```

* 在 ```android/app/src/main/java/.../MainApplication.java``` 中添加

    - 文件顶部添加 ```import com.yolinsoft.umengshare.UmengSharePackage;```
    - 在getPackages()方法中添加 ```new UmengSharePackage()```
    
## 示例

```
import UmengShare from 'react-native-umeng-share';

...

UmengShare.setWeixin("wx6a00d89c23ee8bab", "3baf1193c85774b3fd9d18447d76cab0");
UmengShare.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
UmengShare.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad");
UmengShare.openShare("我是标题", "我是内容", "http://www.yolinsoft.com", {uri:"http://www.yolinsoft.com/static/image/common/logo.png"})
```



