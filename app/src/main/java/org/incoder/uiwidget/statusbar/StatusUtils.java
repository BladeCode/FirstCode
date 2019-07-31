package org.incoder.uiwidget.statusbar;

import android.app.Activity;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import org.incoder.uiwidget.R;

/**
 * StatusUtils
 *
 * @author : Jerry xu
 * @date : 2019/7/31  15:18
 */
public class StatusUtils {

    /**
     * Let toolbar to extend to status bar.
     *
     * @notice this method have to be used after setContentView.
     */
    public static void setToolbar(Activity activity) {
        // Set the padding to match the Status Bar height
        Toolbar toolbar = activity.findViewById(R.id.toolbar);
        toolbar.setPadding(0, getStatusBarHeight(activity), 0, 0);
    }

    /**
     * Get StatusBar height
     *
     * @return height of status bar
     */
    public static int getStatusBarHeight(Activity activity) {
        int result = 0;
        int resourceId = activity.getResources()
                .getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = activity.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * Set the status bar's light mode.
     *
     * @param window      The window.
     * @param isLightMode True to set status bar light mode, false otherwise.
     */
    public static void setStatusBarLightMode(@NonNull final Window window,
                                             final boolean isLightMode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decorView = window.getDecorView();
            if (decorView != null) {
                int vis = decorView.getSystemUiVisibility();
                if (isLightMode) {
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    vis |= View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
                    vis |= View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                    // 状态栏icon 表现为深色系
                    vis |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                } else {
                    vis &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                }
                decorView.setSystemUiVisibility(vis);
            }
        }
    }

}
