package com.htjy.app.common_util.init_manager.base

import android.app.Application
/**
 * @author:jiangwei
 * @version:1.0
 * @date:5/18/21
 * @time:11:26 AM
 * @description:默认
 */
abstract class ISdkInitInterface {
    var isInit:Boolean = false
    abstract fun init(baseApp: Application)
    abstract fun unInit()
}