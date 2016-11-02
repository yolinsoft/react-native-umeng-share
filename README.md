# react native友盟分享插件

目前支持微信好友、微信朋友圈、微信收藏、新浪微博、QQ好友和QQ空间分享

安卓版已完工，IOS版正在开发中

## 自动安装

> npm install react-native-umeng-share --save    
  
    
> react-native link react-native-umeng-share

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

## 微信集成问题

1. 点击分享面板的微信分享，出现闪退现象，无法到微信分享好友列表？

    出现这个效果 多数是由于微信开放平台填写的包名和签名和你实际打包签名的apk的包名和签名不一致造成的！可以使用```tools```文件里面的签名工具获取包签名 ( 微信开放平台填写的签名需要大写转小写去掉冒号 ) 改好了之后可以卸载下app然后重新安装下进行测试， 这个问题就很快搞定了

2. 点击微信分享面板的微信分享，然后就一直在转圈圈，不能进入微信好友列表，也不闪退？

    这个是因为图片过大造成的

3. 以下的事情，你不要做 

    不要用文档里面提供的微信appid和secret

    测试的时候要用打包签名的进行测试，以打包签名的为准 别说为啥我之前就好使现在怎么就不行的事儿，  如果出现 一定先检查下签名 

## 示例

```
import UmengShare from 'react-native-umeng-share';

...

UmengShare.setWeixin("wx86178e0f4c497b8c", "22f46af56d313852cf1957edf394b6b1");
UmengShare.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
UmengShare.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad");
UmengShare.openShare("我是标题", "我是内容", "http://www.yolinsoft.com", {uri:"http://www.yolinsoft.com/static/image/common/logo.png"})
```



