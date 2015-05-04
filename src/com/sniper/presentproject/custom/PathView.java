package com.sniper.presentproject.custom;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.*;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import com.sniper.presentproject.PresentApplication;
import com.sniper.presentproject.R;

public class PathView extends View implements Animator.AnimatorListener{
    private static final int ANIMATION_DURATION = 3200;
    private static final int DEFAULT_PATH_STROKE_WITH = 20;
    private static final int DEFAULT_PATH_STROKE_COLOR = Color.argb(90,47,79,79);
    private static final int VIEW_PADDING = 20;

    private int viewWidth;
    private int viewHeight;
    private float viewPadding;
    private Path path;
    private Paint paint;
//    private MaskFilter  mEmboss;
    private MaskFilter blurFilter;
    private float length;
    private ObjectAnimator animator;
    private PathMeasure measure;
    private PathViewAnimationListener listener;
    private boolean blurPaint;
    private PathDrawable pathDrawable;

    public interface PathViewAnimationListener {
        void onPathViewAnimationStart();
        void onPathViewAnimationFinish();
        void onPathViewAnimationCancel();
        void onPathViewAnimationRepeat();
    }
    public PathView(Context context) {
        this(context, null);
    }

    public PathView(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.circularImageViewStyle);
    }

    public PathView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.PathView, defStyleAttr, 0);

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setColor(attributes.getColor(R.styleable.PathView_path_stroke_color, DEFAULT_PATH_STROKE_COLOR));
        paint.setStrokeWidth(attributes.getInteger(R.styleable.PathView_path_stroke_width, DEFAULT_PATH_STROKE_WITH));

        blurPaint = attributes.getBoolean(R.styleable.PathView_blur_stroke, true);

        attributes.recycle();

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);

//        this.mEmboss = new EmbossMaskFilter(new float[] { 1, 1, 1 }, 0.4f, 6, 3.5f);
        blurFilter = new BlurMaskFilter(8, BlurMaskFilter.Blur.NORMAL);

        if(blurPaint) paint.setMaskFilter(blurFilter);

        path = new Path();
        animator = ObjectAnimator.ofFloat(PathView.this, "phase", 1.0f, 0.0f);
        animator.addListener(this);
        viewPadding = (VIEW_PADDING * ((PresentApplication)getContext().getApplicationContext()).density);

        pathDrawable  = new PathDrawable();
    }
    @Override
    protected void onSizeChanged(int xNew, int yNew, int xOld, int yOld){
        super.onSizeChanged(xNew, yNew, xOld, yOld);

        viewWidth = xNew;
        viewHeight = yNew;
        init();

        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            setBackgroundDrawable(pathDrawable);
        } else {
            setBackground(pathDrawable);
        }
    }
    public void init() {
        // This Path is going to draw "S" letter , starting from top right corner of the view
        path.moveTo((viewWidth - viewPadding), (viewPadding * 2));

        path.lineTo((viewWidth - viewPadding), viewPadding);
        path.lineTo(viewPadding, viewPadding);
        path.lineTo(viewPadding, (viewHeight/2));
        path.lineTo((viewWidth - viewPadding), (viewHeight/2));
        path.lineTo((viewWidth - viewPadding), (viewHeight - viewPadding));
        path.lineTo(viewPadding, (viewHeight - viewPadding));
        path.lineTo(viewPadding, (viewHeight - (viewPadding * 2)));

        // Measure the path
        measure = new PathMeasure(path, false);
//        PathMeasure measure = new PathMeasure(path, false);
        length = measure.getLength();

//        float[] intervals = new float[]{length, length};


        animator.setDuration(ANIMATION_DURATION);
        animator.start();
    }

    //is called by animator object
    public void setPhase(float phase) {
        Log.d("pathview","setPhase called with:" + String.valueOf(phase));
        paint.setPathEffect(createPathEffect(length, phase, 0.0f));
        invalidate();
    }

    private static PathEffect createPathEffect(float pathLength, float phase, float offset) {
        return new DashPathEffect(new float[] { pathLength, pathLength },
                Math.max(phase * pathLength, offset));
    }

    @Override
    public void onDraw(Canvas c) {
        super.onDraw(c);
        c.drawPath(path, paint);
    }
    public void setEventListener(PathViewAnimationListener mEventListener) {
        this.listener = mEventListener;
    }
    @Override
    public void onAnimationStart(Animator animation) {
        if (listener != null) {
            listener.onPathViewAnimationStart();
        }
    }

    @Override
    public void onAnimationEnd(Animator animation) {
        pathDrawable.startAnimating();
        if (listener != null) {
            listener.onPathViewAnimationFinish();
        }
    }

    @Override
    public void onAnimationCancel(Animator animation) {
        if (listener != null) {
            listener.onPathViewAnimationCancel();
        }
    }

    @Override
    public void onAnimationRepeat(Animator animation) {
        if (listener != null) {
            listener.onPathViewAnimationRepeat();
        }
    }
}
