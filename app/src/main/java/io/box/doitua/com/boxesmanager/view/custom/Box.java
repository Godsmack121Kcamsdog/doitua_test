package io.box.doitua.com.boxesmanager.view.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Box extends View {

    private Paint rect = new Paint();

    public Box(Context context) {
        super(context);
    }

    public Box(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Box(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //TODO not fixed params
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0.0f,0.0f,15f,15f,rect);
    }
}
