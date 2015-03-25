package ca.granthunterdev.wemoalarm;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ToggleButton;

/**
 * Created by Grant on 3/25/2015.
 * <p/>
 * Thanks to derekbrameyer and his android-betterpickers for help making this class.
 * https://github.com/derekbrameyer/android-betterpickers/blob/master/library/src/main/java/com/doomonafireball/betterpickers/recurrencepicker/WeekButton.java#L41
 */
public class DayButton extends ToggleButton {

    private static int mWidth;

    public DayButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public DayButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DayButton(Context context) {
        super(context);
    }

    public static void setButtonWidth(int width) {
        mWidth = width;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int measuredHeight = getMeasuredHeight();
        int measuredWidth = getMeasuredWidth();

        if (measuredHeight > 0 && measuredWidth > 0) {
            if (measuredHeight > measuredWidth) {
                if (View.MeasureSpec.getMode(ViewCompat.getMeasuredHeightAndState(this)) != MeasureSpec.EXACTLY) {
                    measuredHeight = measuredWidth;
                } else if (measuredHeight < measuredWidth) {
                    if (View.MeasureSpec.getMode(ViewCompat.getMeasuredWidthAndState(this)) != MeasureSpec.EXACTLY) {
                        measuredWidth = measuredHeight;
                    }
                }
            }
        }
        setMeasuredDimension(measuredWidth, measuredHeight);
    }
}
