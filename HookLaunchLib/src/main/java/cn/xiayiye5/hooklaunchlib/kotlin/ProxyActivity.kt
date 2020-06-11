package cn.xiayiye5.hooklaunchlib.kotlin

import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import cn.xiayiye5.hooklaunchlib.R

/**
 * @author xiayiye
 */
class ProxyActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_proxy)
        Handler().postDelayed({ startToMain() }, 3000)
    }

    private fun startToMain() {
        val intent = Intent(Intent.ACTION_MAIN, null)
        intent.addCategory(Intent.CATEGORY_LAUNCHER)
        intent.addCategory(Intent.CATEGORY_LAUNCHER)
        val packageInfos =
            packageManager.queryIntentActivities(intent, 0)
        val currentPkg = packageName
        for (info in packageInfos) {
            val launcherActivityName = info.activityInfo.name
            val packageName = info.activityInfo.packageName
            if (packageName == currentPkg) {
                intent.component = ComponentName(packageName, launcherActivityName)
                startActivity(intent)
            }
        }
        finish()
    }
}