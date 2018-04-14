BroadcastReceiver 广播

## 广播分类
### 标准广播
* 异步执行
* 无先后顺序(广播接收器几乎在同一时间接收到广播消息)
* 无法被截断
### 有序广播
* 同步执行
* 同一时刻只会有一个广播接收器能够收到这条广播消息
* 有先后顺序
* 可以截断正在传播的广播
## 接收广播
静态注册，动态注册，都是指对需要对监听对象广播进行注册，都需要创建继承`BroadcastReceiver`的类，来实现监听到对应广播需要做的逻辑处理
### 静态注册
`AndroidManifest.xml`文件中申明
应用未启动也可以监听
### 动态注册
特点：可以自由的控制注册和注销，比较灵活，但是必须要在程序启动后才能接收到广播
创建继承`BroadcastReceiver`的类，并复写`onReceive()`方法
## 广播类型
### 系统广播
任何应用都可以通过监听获取到系统发出的广播，但需要加入权限
### 本地广播
仅在自身应用中传播的广播，较安全
