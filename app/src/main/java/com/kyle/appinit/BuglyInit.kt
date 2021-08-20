package com.kyle.appinit

import android.app.Application
import android.util.Log
import com.htjy.app.common_util.init_manager.base.ISdkInitInterface

/**
 * @author:jiangwei
 * @version:1.0
 * @date:8/20/21$
 * @time:9:44 PM$
 * @description:默认
 */
class BuglyInit: ISdkInitInterface() {
    override fun init(baseApp: Application) {
        Log.d(TAG,"Bugly初始化 ${Thread.currentThread().name}")
    }

    override fun unInit() {
        Log.d(TAG,"Bugly unInit ${Thread.currentThread().name}")
    }

    companion object {
        private const val TAG = "BuglyInit"
    }
}