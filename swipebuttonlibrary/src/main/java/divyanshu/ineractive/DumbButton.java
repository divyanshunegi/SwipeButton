package divyanshu.ineractive;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

import com.divyanshu.swipebutton.swipebutton.R;

import divyanshu.ineractive.custom_view.PolygonalDrwable;

import static android.R.attr.drawableLeft;

/**
 * Created by divyanshunegi on 2/8/17.
 * Project : SwipeButtonSample
 */
public class DumbButton extends AppCompatButton {
    private StateListDrawable mNormalDrawable;
    private String mNormalText;
    private float cornerRadius;

    public DumbButton(Context context) {
        super(context);
        init(context,null);
    }

    public DumbButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public DumbButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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
        setPadding(pL+100, pT, pR, pB);

    }

    private void initAttributes(Context context, AttributeSet attrs) {

        TypedArray attr = getTypedArray(context, attrs, R.styleable.DumbButton);
        if (attr == null) {
            return;
        }
        try {

            float defValue = getDimension(R.dimen.corner_radius);
            cornerRadius = attr.getDimension(R.styleable.DumbButton_cornerRadius, defValue);

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
        int colorPressed = attr.getColor(R.styleable.DumbButton_colorPressed, blueDark);
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

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private LayerDrawable createNormalDrawable(TypedArray attr) {

        LayerDrawable drawableNormal =
                (LayerDrawable) getDrawable(R.drawable.rect_normal).mutate();

        drawableNormal.setDrawableByLayerId(R.id.iconArrow,new PolygonalDrwable(Color.WHITE,10,100,100));

        GradientDrawable drawableTop =
                (GradientDrawable) drawableNormal.getDrawable(0).mutate();
        drawableTop.setCornerRadius(getCornerRadius());

        int blueDark = getColor(R.color.blue_pressed);
        int colorPressed = attr.getColor(R.styleable.DumbButton_colorPressed, blueDark);
        drawableTop.setColor(colorPressed);

//        Drawable drawableLeft =
//                (Drawable) drawableNormal.getDrawable(1).mutate();
//        drawableLeft.setBounds(20,30,20,30);
        drawableNormal.invalidateSelf();
        //drawableLeft.setCornerRadius(getCornerRadius());

        int bluered = getColor(R.color.red_error);
        int colorred = attr.getColor(R.styleable.DumbButton_colorPressed, bluered);
//        drawableLeft.setColor(bluered);

        return drawableNormal;
    }
}
