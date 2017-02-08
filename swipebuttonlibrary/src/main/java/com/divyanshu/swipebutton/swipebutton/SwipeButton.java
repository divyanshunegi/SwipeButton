package com.divyanshu.swipebutton.swipebutton;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by divyanshunegi on 2/8/17.
 * Project : SwipeButtonSample
 */
public class SwipeButton extends Button {
    private StateListDrawable mNormalDrawable;
    private String mNormalText;
    private float cornerRadius;

    public SwipeButton(Context context) {
        super(context);
        init(context,null);
    }

    public SwipeButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public SwipeButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SwipeButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context,attrs);
    }


    private void init(Context context, AttributeSet attrs) {
        mNormalDrawable = new StateListDrawable();
        if (attrs != null) {
            initAttributes(context, attrs);
        }
        mNormalText = getText().toString();
        setBackgroundCompat(mNormalDrawable);
    }

    @SuppressWarnings("deprecation")
    @SuppressLint("NewApi")
    private void setBackgroundCompat(StateListDrawable mNormalDrawable) {
        int pL = getPaddingLeft();
        int pT = getPaddingTop();
        int pR = getPaddingRight();
        int pB = getPaddingBottom();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            setBackground(mNormalDrawable);
        } else {
            setBackgroundDrawable(mNormalDrawable);
        }
        setPadding(pL, pT, pR, pB);

    }

    private void initAttributes(Context context, AttributeSet attrs) {

        TypedArray attr = getTypedArray(context, attrs, R.styleable.SwipeButton);
        if (attr == null) {
            return;
        }

        try {

            float defValue = getDimension(R.dimen.corner_radius);
            cornerRadius = attr.getDimension(R.styleable.SwipeButton_pb_colorPressed, defValue);

            mNormalDrawable.addState(new int[]{android.R.attr.state_pressed},
                    createPressedDrawable(attr));
            mNormalDrawable.addState(new int[]{android.R.attr.state_focused},
                    createPressedDrawable(attr));
            mNormalDrawable.addState(new int[]{android.R.attr.state_selected},
                    createPressedDrawable(attr));
            mNormalDrawable.addState(new int[]{}, createNormalDrawable(attr));

        } finally {
            attr.recycle();
        }

    }

    protected TypedArray getTypedArray(Context context, AttributeSet attributeSet, int[] attr) {
        return context.obtainStyledAttributes(attributeSet, attr, 0, 0);
    }

    protected float getDimension(int id) {
        return getResources().getDimension(id);
    }

    private Drawable createPressedDrawable(TypedArray attr) {
        GradientDrawable drawablePressed =
                (GradientDrawable) getDrawable(R.drawable.rect_pressed).mutate();
        drawablePressed.setCornerRadius(getCornerRadius());

        int blueDark = getColor(R.color.blue_pressed);
        int colorPressed = attr.getColor(R.styleable.SwipeButton_pb_colorPressed, blueDark);
        drawablePressed.setColor(colorPressed);

        return drawablePressed;
    }

    protected Drawable getDrawable(int id) {
        return getResources().getDrawable(id);
    }

    protected int getColor(int id) {
        return getResources().getColor(id);
    }

    public float getCornerRadius() {
        return cornerRadius;
    }

    private LayerDrawable createNormalDrawable(TypedArray attr) {
        LayerDrawable drawableNormal =
                (LayerDrawable) getDrawable(R.drawable.rect_normal).mutate();

        GradientDrawable drawableTop =
                (GradientDrawable) drawableNormal.getDrawable(0).mutate();
        drawableTop.setCornerRadius(getCornerRadius());

        int blueDark = getColor(R.color.blue_pressed);
        int colorPressed = attr.getColor(R.styleable.SwipeButton_pb_colorPressed, blueDark);
        drawableTop.setColor(colorPressed);

        GradientDrawable drawableBottom =
                (GradientDrawable) drawableNormal.getDrawable(1).mutate();
        drawableBottom.setCornerRadius(getCornerRadius());

        int blueNormal = getColor(R.color.blue_normal);
        int colorNormal = attr.getColor(R.styleable.SwipeButton_pb_colorNormal, blueNormal);
        drawableBottom.setColor(colorNormal);
        return drawableNormal;
    }
}
