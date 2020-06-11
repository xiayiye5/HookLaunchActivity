package cn.xiayiye5.hooklaunchactivity

import android.app.Activity
import android.os.Bundle

/**
 * @author xiayiye5
 * 2020年6月11日10:33:23
 * 说明：此默认启动页一定要继承 Activity不然会报错，切记不能继承 AppCompatActivity会无法启动报错
 */
class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}