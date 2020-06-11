package cn.xiayiye5.hooklaunchlib.java;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.Handler;

import java.util.List;

import cn.xiayiye5.hooklaunchlib.R;

/**
 * @author xiayiye
 */
public class ProxyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proxy);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startToMain();
            }
        }, 3000);
    }

    private void startToMain() {
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> packageInfos = getPackageManager().queryIntentActivities(intent, 0);
        String currentPkg = getPackageName();
        for (ResolveInfo info : packageInfos) {
            String launcherActivityName = info.activityInfo.name;
            String packageName = info.activityInfo.packageName;
            if (packageName.equals(currentPkg)) {
                intent.setComponent(new ComponentName(packageName, launcherActivityName));
                startActivity(intent);
            }
        }
        finish();
    }
}
