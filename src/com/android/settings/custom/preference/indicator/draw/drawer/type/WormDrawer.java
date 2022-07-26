package com.android.settings.custom.preference.indicator.draw.drawer.type;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import androidx.annotation.NonNull;
import com.android.settings.custom.preference.indicator.animation.data.Value;
import com.android.settings.custom.preference.indicator.animation.data.type.WormAnimationValue;
import com.android.settings.custom.preference.indicator.draw.data.Indicator;
import com.android.settings.custom.preference.indicator.draw.data.Orientation;

public class WormDrawer extends BaseDrawer {

    public RectF rect;

    public WormDrawer(@NonNull Paint paint, @NonNull Indicator indicator) {
        super(paint, indicator);
        rect = new RectF();
    }

    public void draw(
            @NonNull Canvas canvas,
            @NonNull Value value,
            int coordinateX,
            int coordinateY) {

        if (!(value instanceof WormAnimationValue)) {
            return;
        }

        WormAnimationValue v = (WormAnimationValue) value;
        int rectStart = v.getRectStart();
        int rectEnd = v.getRectEnd();

        int radius = indicator.getRadius();
        int unselectedColor = indicator.getUnselectedColor();
        int selectedColor = indicator.getSelectedColor();

        if (indicator.getOrientation() == Orientation.HORIZONTAL) {
            rect.left = rectStart;
            rect.right = rectEnd;
            rect.top = coordinateY - radius;
            rect.bottom = coordinateY + radius;

        } else {
            rect.left = coordinateX - radius;
            rect.right = coordinateX + radius;
            rect.top = rectStart;
            rect.bottom = rectEnd;
        }

        paint.setColor(unselectedColor);
        canvas.drawCircle(coordinateX, coordinateY, radius, paint);

        paint.setColor(selectedColor);
        canvas.drawRoundRect(rect, radius, radius, paint);
    }
}
