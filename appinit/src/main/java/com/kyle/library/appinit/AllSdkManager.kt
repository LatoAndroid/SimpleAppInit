package com.kyle.library.appinit

import android.app.Application
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import com.htjy.app.common_util.init_manager.base.ISdkInitInterface

/**
 * @author:jiangwei
 * @version:1.0
 * @date:5/18/21
 * @time:11:27 AM
 * @description:简单的项目初始化框架，用于sdk的同步或者异步初始化，更好的管理init和unInit
 */
class AllSdkManager private constructor() {
    private var mSdkMap = ArrayList<ISdkInitInterface>()
    private var mAsyncSdkMap = ArrayList<ISdkInitInterface>()
    private var mHandler: Handler

    /**
     * 同步初始化的数据
     */
    fun add(sdkInit: ISdkInitInterface): AllSdkManager {
        mSdkMap.add(sdkInit)
        return this
    }


    /**
     * 异步初始化的数据
     */
    fun addAsync(sdkInit: ISdkInitInterface): AllSdkManager {
        mAsyncSdkMap.add(sdkInit)
        return this
    }

    fun init(baseApp: Application) {
        for (value in mSdkMap) {
            try {
                if (!value.isInit) {
                    value.init(baseApp)
                    value.isInit = true
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        mHandler.post {
            for (value in mAsyncSdkMap) {
                try {
                    if (!value.isInit) {
                        value.init(baseApp)
                        value.isInit = true
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        Thread {


        }.start()


    }

    fun unInit() {
        for (value in mSdkMap) {
            try {
                value.unInit()
                value.isInit = false
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }

        for (value in mAsyncSdkMap) {

            try {
                value.unInit()
                value.isInit = false
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        mSdkMap.clear()
        mAsyncSdkMap.clear()
        mHandler.removeCallbacksAndMessages(null)
    }

    companion object {
        private const val TAG = "AllSdkManager"
        val instance by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            AllSdkManager()
        }
    }

    init {
        val handlerThread = HandlerThread("AllSdkManager")
        handlerThread.start()
        mHandler = Handler(handlerThread.looper)
    }

}

