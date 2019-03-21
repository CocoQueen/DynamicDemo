package debug;

import android.app.Application;
import android.util.Log;

/**
 * @author：Coco date：2019/3/13
 * version：1.0
 * description:ModuleBApplication
 */
public class ModuleBApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("application", "onCreate: b");
    }
}
