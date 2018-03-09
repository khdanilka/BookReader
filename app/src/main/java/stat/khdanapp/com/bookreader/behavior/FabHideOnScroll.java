package stat.khdanapp.com.bookreader.behavior;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class FabHideOnScroll extends FloatingActionButton.Behavior {

    public FabHideOnScroll(Context context, AttributeSet attrs) {
        super();
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout,
                                       @NonNull FloatingActionButton child,
                                       @NonNull View directTargetChild,
                                       @NonNull View target,
                                       int axes,
                                       int type)
    {
        Log.d("TEST"," onStartNestedScroll");
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }


    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout,
                               @NonNull FloatingActionButton child,
                               @NonNull View target,
                               int dxConsumed,
                               int dyConsumed,
                               int dxUnconsumed,
                               int dyUnconsumed,
                               int type)
    {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type);

        Log.d("TEST"," onNestedScroll " + dyConsumed);
//        if (child.getVisibility() == View.VISIBLE && dyConsumed > 0) {
//            child.hide();
//        } else if (child.getVisibility() == View.GONE && dyConsumed < 0) {
//            child.show();
//        }
        if (dyConsumed > 0) {
            CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams)
                    child.getLayoutParams();
            int fab_bottomMargin = layoutParams.bottomMargin;
            child.animate().translationX(child.getHeight() + fab_bottomMargin).setInterpolator(new
                    LinearInterpolator()).start();
        } else if (dyConsumed < 0) {
            child.animate().translationX(0).setInterpolator(new LinearInterpolator()).start();
        }


    }

}