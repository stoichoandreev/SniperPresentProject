package com.sniper.presentproject.custom;

import android.animation.ValueAnimator;
import android.graphics.*;
import android.graphics.drawable.Drawable;

public class PathDrawable extends Drawable implements ValueAnimator.AnimatorUpdateListener {
    private static final int ANIMATION_DURATION = 2500;
    private Rect rect;
    private Path [] paths;
    private Paint [] paints;
    private ValueAnimator [] animators;
    private int [] colors;

    public PathDrawable() {
        paths = new Path[]{new Path(),new Path(),new Path()};
        paints = new Paint[]{new Paint(),new Paint(),new Paint()};
        colors = new int []{Color.rgb(102,139,139),Color.rgb(255,165,0),Color.rgb(65,105,225)};

        for(int i =0 ; i < paints.length;i++){
            paints[i].setColor(colors[i]);
            paints[i].setStrokeWidth(10);
            paints[i].setStyle(Paint.Style.STROKE);
        }
    }

    public void startAnimating() {
        rect = getBounds();
        animators = new ValueAnimator[]{
                ValueAnimator.ofInt(-rect.bottom, rect.bottom),
                ValueAnimator.ofInt(-rect.bottom, rect.bottom),
                ValueAnimator.ofInt(-rect.bottom, rect.bottom)};
        for(int i =0 ; i < animators.length;i++){
            animators[i].setDuration(ANIMATION_DURATION);
            animators[i].addUpdateListener(this);
            animators[i].start();
        }
    }

    @Override
    public void draw(Canvas canvas) {
        for(int i =0 ; i < paths.length;i++){
            canvas.drawPath(paths[i], paints[i]);
        }
    }

    @Override
    public void setAlpha(int alpha) {
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animator) {
        if(animator == animators[0]) {
            paths[0].reset();
            paths[0].moveTo(rect.left, rect.bottom);
            paths[0].quadTo((rect.right - rect.left) / 2, (Integer) animator.getAnimatedValue(), rect.right, rect.bottom);
        }else if(animator == animators[1]) {
            paths[1].reset();
            paths[1].moveTo(rect.left, rect.top);
            paths[1].quadTo((rect.right - rect.left) / 2, (Integer) animator.getAnimatedValue(), rect.right, rect.bottom);
        }
        else if(animator == animators[2]) {
            paths[2].reset();
            paths[2].moveTo(rect.right, rect.left);
            paths[2].quadTo((rect.right - rect.left) / 2, (Integer) animator.getAnimatedValue(), rect.right, rect.bottom);
        }
        invalidateSelf();
    }
}
