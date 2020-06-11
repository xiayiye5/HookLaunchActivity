package cn.xiayiye5.hooklaunchlib.kotlin

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.os.Bundle
import android.util.Log
import java.lang.reflect.Field

/**
 * @author xiayiye
 */
class InstrumentationProxy //通过构造函数来传递对象
    (var target: Instrumentation) :
    Instrumentation() {
    private var hasHook = false
    override fun callActivityOnCreate(activity: Activity, icicle: Bundle) {
        // TODO Auto-generated method stub
        Log.d(
            TAG,
            " callActivityOnCreate: activity" + activity.intent.action
        )
        if (isMainActivity(activity) && !hasHook) {
            changeActivityHasCalled(activity)
            activity.startActivity(
                Intent(
                    activity,
                    ProxyActivity::class.java
                )
            )
            activity.finish()
            hasHook = true
        } else {
            target.callActivityOnCreate(activity, icicle)
        }
    }

    private fun isMainActivity(activity: Activity): Boolean {
        return Intent.ACTION_MAIN == activity.intent.action &&
                activity.intent.categories.contains(Intent.CATEGORY_LAUNCHER)
    }

    private fun changeActivityHasCalled(activity: Activity) {
        val nameField: Field
        try {
            // 获取到对象对应的class对象
            val clazz: Class<out Activity> = Activity::class.java
            nameField = clazz.getDeclaredField("mCalled")
            // 获取私有成员变量:name
            // 设置操作权限为true
            nameField.isAccessible = true
            nameField[activity] = true
        } catch (e: NoSuchFieldException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        } catch (e: IllegalArgumentException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }
    }

    companion object {
        const val TAG = "InstrumentationProxy"
    }

}