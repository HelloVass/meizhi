# MeiZhi

基于 gank.io 提供 api 开发的原生（kotlin） Android App。



## Feature

* 妹纸信息流显示
* 分享
* 保存妹纸原图到本地
* 每日推荐
* feed 流支持分页加载和下拉刷新



## 项目架构

* 基于 [TheMvp](https://github.com/kymjs/TheMVP)  改进而来的 Mvp，具体可以参考 [作者的这篇博客](https://www.kymjs.com/code/2015/11/09/01/)。
* 基于 Rxjava 实现的 UI 层状态管理，这里点名表扬下我的好基友祥哥，感谢他[翻译的文章](http://blog.licrafter.com/2018/05/06/ManagingStateWithRxJava/)



## 开源库

* [NoPaginate](https://github.com/NoNews/NoPaginate)，一个适合 mvp 架构的分页加载库，有 bug，但作者没 fix，我按照作者的思路自己实现了一个 kotlin 版的，暂且叫 LoadMore 吧。
* RxJava&RxAndroid（2.x版本），懒得介绍系列
* Square&OkHttp 网络全家桶，懒得介绍系列
* Glide（4.x版本），懒得介绍系列
* rxpermissions，动态权限管理，被国内 Rom 视作空气系列，先用着，以后再造个轮子
* anko，用到 Intent 传递那块封装，偷偷懒蛮爽的
* Android support design库，懒得介绍系列
* customtabs，没有 Chrome 浏览器被视作空气系列，懒得封装 WebViewActivity，暂时先用着
* 其他的也懒得介绍系列



## minSdkVersion，怎么着也得 Android 5.x 起吧



## Kotlin语法糖&优点

这里不得不单独拿出来讲一下，不然这项目和 convert java file to kotlin file 的项目有啥区别？

* 代理（kotlin 原生支持），主要是图片加载器那块，将以前的策略模式改为代理，还不是美滋滋，具体参考图片加载那一块代码
* 集合提供的 flatMap、find 方法
* 更多介绍可能会写篇博客分享啥的，随缘吧

## 效果图
![煎牛排](http://7xsq1h.com1.z0.glb.clouddn.com/%E7%85%8E%E7%89%9B%E6%8E%92%EF%BC%8C%E8%BF%98%E4%B8%8D%E6%98%AF%E7%BE%8E%E6%BB%8B%E6%BB%8B.gif)




