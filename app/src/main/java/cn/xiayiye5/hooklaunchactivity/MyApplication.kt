package cn.xiayiye5.hooklaunchactivity

import android.app.Application
import android.content.Context
import cn.xiayiye5.hooklaunchlib.java.XiaYiYeSDK

//import cn.xiayiye5.hooklaunchlib.java.XiaYiYeSDK Java版本的导包，上面是Kotlin的导包

/**
 * Created by liuguangli on 17/12/7.
 */
class MyApplication : Application() {
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        XiaYiYeSDK.init();
    }
}