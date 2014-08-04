package mona.android.findforme.ui.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import mona.android.findforme.R;

@TargetApi(Build.VERSION_CODES.L)
public class TakePhotoFabFrameLayout extends FrameLayout {
    private View mRevealView;
    private float mHotSpotX, mHotSpotY;
    private int mRevealViewOffColor;

    public TakePhotoFabFrameLayout(Context context) {
        this(context, null, 0, 0);
    }

    public TakePhotoFabFrameLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0, 0);
    }

    public TakePhotoFabFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public TakePhotoFabFrameLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr);

        mRevealView = new View(context);
        mRevealView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        addView(mRevealView, 0);
        mRevealViewOffColor = getResources().getColor(R.color.theme_accent_1);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
            mHotSpotX = event.getX();
            mHotSpotY = event.getY();
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Outline outline = new Outline();
        outline.setOval(0, 0, w, h);
        setOutline(outline);
        setClipToOutline(true);
    }

    /*@Override
    public void setChecked(boolean checked, boolean allowAnimate) {
        super.setChecked(checked, allowAnimate);
        if (allowAnimate) {
            ValueAnimator animator = ViewAnimationUtils.createCircularReveal(
                    mRevealView,
                    (int) mHotSpotX, (int) mHotSpotY, 0, getWidth());
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    setChecked(mChecked, false);
                }
            });
            animator.start();
            mRevealView.setVisibility(View.VISIBLE);
            mRevealView.setBackgroundColor(mChecked ? Color.WHITE : mRevealViewOffColor);
        } else {
            mRevealView.setVisibility(View.GONE);
            RippleDrawable newBackground = (RippleDrawable) getResources().getDrawable(mChecked
                    ? R.drawable.add_schedule_fab_ripple_background_on
                    : R.drawable.add_schedule_fab_ripple_background_off);
            setBackground(newBackground);
        }
    }*/

}