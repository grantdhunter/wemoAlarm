package ca.granthunterdev.wemoalarm;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import java.util.jar.Attributes;

/**
 * Created by Grant on 3/25/2015.
 *
 * Thanks to derekbrameyer and his android-betterpickers for help making this class.
 * https://github.com/derekbrameyer/android-betterpickers/blob/master/library/src/main/java/com/doomonafireball/betterpickers/recurrencepicker/LinearLayoutWithMaxWidth.java
 */
public class LayoutMaxWidth extends LinearLayout {
    public LayoutMaxWidth(Context context) {
        super(context);
    }

    public LayoutMaxWidth(Context context, AttributeSet attribute) {
        super(context, attribute);
    }

    public LayoutMaxWidth(Context context, AttributeSet attribute, int defStyle){
        super(context, attribute, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        DayButton.setButtonWidth(View.MeasureSpec.getSize(widthMeasureSpec)/7);
    }
}
