package com.fonxconn.android.draganddraw.bean;

import android.graphics.PointF;

/**
 * Created by Aaron on 2017/1/12.
 */

public class Box {
    private PointF mOrigin;
    private PointF mCurrent;

    public Box(PointF origin){
        mOrigin = origin;
        mCurrent = origin;
    }

    public PointF getCurrent(){
        return mCurrent;
    }

    public void setCurrent(PointF current){
        mCurrent = current;
    }

    public PointF getOrigin(){
        return mOrigin;
    }

}
