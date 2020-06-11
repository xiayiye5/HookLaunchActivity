package cn.xiayiye5.hooklaunchlib.kotlin

import android.app.Instrumentation
import android.util.Log
import java.lang.reflect.InvocationTargetException

/**
 * Created by liuguangli on 17/12/7.
 */
object XiaYiYeSDK {
    const val TAG = "MyApplication"
    const val ACTIVIT_THREAD = "android.app.ActivityThread"
    const val CURRENT_ACTIVITY_THREAD = "currentActivityThread"
    const val INSTRUMENTATION = "mInstrumentation"
    fun init() {
        try {
            //获取当前的ActivityThread对象
            val activityThreadClass =
                Class.forName(ACTIVIT_THREAD)
            val currentActivityThreadMethod =
                activityThreadClass.getDeclaredMethod(CURRENT_ACTIVITY_THREAD)
            currentActivityThreadMethod.isAccessible = true
            val currentActivityThread = currentActivityThreadMethod.invoke(null)
            //拿到在ActivityThread类里面的原始mInstrumentation对象
            val mInstrumentationField =
                activityThreadClass.getDeclaredField(INSTRUMENTATION)
            mInstrumentationField.isAccessible = true
            val mInstrumentation =
                mInstrumentationField[currentActivityThread] as Instrumentation
            //构建我们的代理对象
            val evilInstrumentation: Instrumentation =
                InstrumentationProxy(mInstrumentation)
            //通过反射，换掉字段，注意，这里是反射的代码，不是Instrumentation里面的方法
            mInstrumentationField[currentActivityThread] = evilInstrumentation
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: NoSuchMethodException) {
            e.printStackTrace()
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        } catch (e: InvocationTargetException) {
            e.printStackTrace()
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        }
        //做个标记，方便后面查看
        Log.d(TAG, "has init SDK ")
    }
}