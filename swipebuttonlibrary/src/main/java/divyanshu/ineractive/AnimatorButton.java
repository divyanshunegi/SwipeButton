package divyanshu.ineractive;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by divyanshunegi on 2/8/17.
 * Project : SwipeButtonSample
 */
public abstract class AnimatorButton extends DumbButton {

    public AnimatorButton(Context context) {
        super(context);
    }

    public AnimatorButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AnimatorButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
         }
        return super.onTouchEvent(event);
    }
}
