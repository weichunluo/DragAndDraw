package com.fonxconn.android.draganddraw.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.fonxconn.android.draganddraw.bean.Box;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aaron on 2017/1/12.
 */

public class BoxDrawingView extends View {
    private static final String TAG = "BoxDrawingView";
    private Box mCurrentBox;
    private List<Box> mBoxen = new ArrayList<Box>();
    private Paint mBoxPaint;
    private Paint mBackgroudPaint;

    //Used when creating the view in code
    public BoxDrawingView(Context context) {
        super(context);
    }

    //Used when inflating this view from XML
    public BoxDrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);

        //Paint the boxes a nice semitransparent red (ARGB)
        mBoxPaint = new Paint();
        mBoxPaint.setColor(0x22ff0000);

        //Paint the background off-white
        mBackgroudPaint = new Paint();
        mBackgroudPaint.setColor(0xfff8efe0);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        PointF current = new PointF(event.getX(), event.getY());
        String action = "";

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                action = "ACTION_DOWN";
                //Reset drawing state
                mCurrentBox = new Box(current);
                mBoxen.add(mCurrentBox);
                break;
            case MotionEvent.ACTION_UP:
                action = "ACTION_UP";
                mCurrentBox = null;
                break;
            case MotionEvent.ACTION_MOVE:
                action = "ACTION_MOVE";
                if (mCurrentBox != null) {
                    mCurrentBox.setCurrent(current);
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                action = "ACTION_CANCEL";
                mCurrentBox = null;
                break;
        }
        Log.i(TAG, "at x=" + current.x + ", y=" + current.y);
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //Fill the background
        canvas.drawPaint(mBackgroudPaint);

        for (Box box : mBoxen) {
            float left = Math.min(box.getCurrent().x,box.getOrigin().x);
            float right = Math.max(box.getOrigin().x,box.getCurrent().x);
            float top  = Math.min(box.getOrigin().y,box.getCurrent().y);
            float bottom = Math.max(box.getOrigin().y,box.getCurrent().y);

            canvas.drawRect(left,top,right,bottom,mBoxPaint);
        }
    }
}
