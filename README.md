# SimpleAppInit
## 简单的应用初始化框架，支持在多个位置任意添加初始化，支持异步初始化，支持统一注销，不支持初始化依赖关系，如果需要，请使用 alibaba alpha  或者慕课网-性能优化相关视频
### 使用方法

#### 引入
```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}

dependencies {
    	implementation 'com.github.LatoAndroid:SimpleAppInit:1.0.0'
    }
```

#### 使用
```
class JpushInit: ISdkInitInterface() {
    override fun init(baseApp: Application) {
        Log.d(TAG,"极光初始化 ${Thread.currentThread().name}")
    }

    override fun unInit() {
        Log.d(TAG,"极光unInit ${Thread.currentThread().name}")
    }

    companion object {
        private const val TAG = "JpushInit"
    }
}


在application或者其他类中
AllSdkManager.instance.add(JpushInit()).addAsync(BuglyInit()).init(application)

AllSdkManager.instance.unInit()

```
