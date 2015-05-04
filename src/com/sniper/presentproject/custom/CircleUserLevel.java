package com.sniper.presentproject.custom;

import android.animation.Animator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.ViewPropertyAnimator;
import android.widget.ImageView;
import com.sniper.presentproject.R;

/**
 * Created by sniper on 4/15/15.
 */
public class CircleUserLevel extends ImageView implements Animator.AnimatorListener{
    private static final int ANIMATION_DELAY = 30;
    private static final int LEVELS_NUMBER = 10;
    private static final int LEVELS_STEP = 5;
    private int borderAroundCircularImage;
    private int distanceFromCircularImage;
    private static final int [] levelColors = {
            R.color.user_level_1,
            R.color.user_level_2,
            R.color.user_level_3,
            R.color.user_level_4,
            R.color.user_level_5,
            R.color.user_level_6,
            R.color.user_level_7,
            R.color.user_level_8,
            R.color.user_level_9,
            R.color.user_level_10,
            R.color.user_level_default //default color when we don't have level color
    };
    private int borderWidth;
    private int canvasSize;
    private int userLevel;
    private int bigCircleRadius;
    private int smallCircleRadius;
    private Bitmap image;
    private Paint paint;
    private Paint paintBorder;
    private Paint circle;
    private Paint userLevelTextPaint;
    private Rect textLevelBounds;

    private int circleUserLevelCenterX;
    private int circleUserLevelCenterY;
    private int circleUserLevelRadius;
    private boolean showLevelLabel;
    private String levelText;
    private boolean animateDrawingAtBegin;
    private boolean isDrawingAnimationFinish;
    private boolean isAnimationDrawingStillWork;
    private Handler animationHandler;
    private Runnable animationRunnable;
    private int drawingCounter;
    private OnClickListener listener;
    private boolean isProfileImageZoomIn;

    public CircleUserLevel(final Context context) {
        this(context, null);
    }

    public CircleUserLevel(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.circularImageViewStyle);
    }

    public CircleUserLevel(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.setClickable(true);
        this.setEnabled(true);
        this.setFocusable(true);
        this.setFocusableInTouchMode(true);

        // load the styled attributes and set their properties
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.CircleUserLevel, defStyle, 0);

        setUserLevel(
                (attributes.getInteger(R.styleable.CircleUserLevel_cul_user_level,1) == 0) ?
                        1 :
                        attributes.getInteger(R.styleable.CircleUserLevel_cul_user_level,1),
                false );
        showLevelLabel =  attributes.getBoolean(R.styleable.CircleUserLevel_cul_show_level_label, false);
        animateDrawingAtBegin =  attributes.getBoolean(R.styleable.CircleUserLevel_cul_animate_at_begin, false);
        levelText = getContext().getResources().getString(R.string.level);

        attributes.recycle();
        // init paint
        paint = new Paint();
        paint.setAntiAlias(true);

        paintBorder = new Paint();
        paintBorder.setAntiAlias(true);

        paintBorder.setColor(Color.TRANSPARENT);

        circle = new Paint();
        circle.setStyle(Paint.Style.FILL);
        circle.setColor(Color.BLACK);

        userLevelTextPaint = new Paint();
        userLevelTextPaint.setTextAlign(Paint.Align.CENTER);
        userLevelTextPaint.setColor(Color.WHITE);
        userLevelTextPaint.setAntiAlias(true);
        textLevelBounds = new Rect();

        animationHandler = new Handler();
    }

    @Override
    public void onDraw(Canvas canvas) {
        if(drawingCounter > (LEVELS_NUMBER * LEVELS_STEP)) drawingCounter = 0;

        borderAroundCircularImage = (int)(canvas.getWidth() * ((float)3/100));
        distanceFromCircularImage = (int)(borderAroundCircularImage * ((float)1/3));

        borderWidth = borderAroundCircularImage;
//        borderWidth = (int)(borderAroundCircularImage * getDensity());
        bigCircleRadius = (borderWidth - (distanceFromCircularImage /2));
//        bigCircleRadius = ((borderWidth - (int)(distanceFromCircularImage * getDensity())) /2);
        smallCircleRadius = bigCircleRadius/2;
        // load the bitmap
        image = drawableToBitmap(getDrawable());

        // init shader
        if (image != null) {
            canvasSize = canvas.getWidth();

            if(canvas.getHeight() < canvasSize) canvasSize = canvas.getHeight();

            BitmapShader shader = new BitmapShader(Bitmap.createScaledBitmap(image, canvasSize, canvasSize, false), Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            paint.setShader(shader);

            // circleCenter is the x or y of the view's center
            // radius is the radius in pixels of the circle to be drawn
            // paint contains the shader that will texture the shape
            int circleCenter = (canvasSize - (borderWidth * 2)) / 2;
            canvas.drawCircle(circleCenter + borderWidth, circleCenter + borderWidth, ((canvasSize - (borderWidth * 9)) / 2) + borderWidth - 4.0f, paintBorder);
            canvas.drawCircle(circleCenter + borderWidth, circleCenter + borderWidth, ((canvasSize - (borderWidth * 9)) / 2) - 4.0f, paint);

            circleUserLevelCenterX = circleCenter + borderWidth;
            circleUserLevelCenterY = circleCenter + borderWidth;
            circleUserLevelRadius = (int)(((canvasSize - (borderWidth * 8)) / 2) + borderWidth - 4.0f- distanceFromCircularImage);
//            circleUserLevelRadius = (int)(((canvasSize - (borderWidth * 4)) / 2) + borderWidth - 4.0f- (distanceFromCircularImage * getDensity()));
        }

        if(animateDrawingAtBegin && !isAnimationDrawingStillWork){
            animationRunnable = getAnimationRunnable();
            isAnimationDrawingStillWork = true;
            animationHandler.postDelayed(animationRunnable,ANIMATION_DELAY);
        }else if(!animateDrawingAtBegin){
            drawingCounter = (LEVELS_NUMBER * LEVELS_STEP);
        }


        int circleStartXPosition = getNewCircleXPosition(0);//canvas.getWidth() / 2;
        int circleStartYPosition = getNewCircleYPosition(0);//(int)((borderWidth - (distanceFromCircularImage * getDensity()) / 2));
        int levelCircleXPosition = 0, levelCircleYPosition = 0, levelCircleColor = -1;
        int levelColorCounter = 0;
        for(int i = 0; i < drawingCounter;i++){
            circle.setColor(getContext().getResources().getColor(levelColors[levelColorCounter]));
            if (i % LEVELS_STEP == 0) {
                canvas.drawCircle(circleStartXPosition, circleStartYPosition, bigCircleRadius, circle);
            } else {
                canvas.drawCircle(circleStartXPosition, circleStartYPosition, smallCircleRadius, circle);
            }

            if(userLevel < levelColorCounter && levelColorCounter != (levelColors.length - 1)){
                levelColorCounter = (levelColors.length - 1);
            }else if(userLevel == levelColorCounter && ((i % LEVELS_STEP) == (LEVELS_STEP - 1))){
                levelCircleXPosition = circleStartXPosition;
                levelCircleYPosition = circleStartYPosition;
                levelCircleColor = levelColors[levelColorCounter];
                levelColorCounter++;
            }else if(userLevel > levelColorCounter && ((i % LEVELS_STEP) == (LEVELS_STEP - 1))) levelColorCounter++;

            circleStartXPosition = getNewCircleXPosition(i+1);
            circleStartYPosition = getNewCircleYPosition(i+1);
        }

        if(levelCircleColor >= 0) { //draw labels and user level circle only if we have them
            //draw circle  showing user level text
            circle.setColor(getContext().getResources().getColor(levelCircleColor));
            canvas.drawCircle(levelCircleXPosition, levelCircleYPosition, (bigCircleRadius * 4), circle);
            // draw text in user level circle

            userLevelTextPaint.getTextBounds(getUserLevelLikeString(), 0, Integer.toString(userLevel).length(), textLevelBounds);
            userLevelTextPaint.setTextSize((bigCircleRadius * 4));

            if (showLevelLabel) {
                //draw first level label
                userLevelTextPaint.getTextBounds(levelText, 0, levelText.length(), textLevelBounds);
                userLevelTextPaint.setTextSize((bigCircleRadius * 2));
                canvas.drawText(levelText, levelCircleXPosition, (levelCircleYPosition + userLevelTextPaint.getTextSize()), userLevelTextPaint);
                //after that draw lavel number with bold text
                userLevelTextPaint.setTextSize((bigCircleRadius * 4));
                userLevelTextPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
                canvas.drawText(getUserLevelLikeString(), levelCircleXPosition, levelCircleYPosition, userLevelTextPaint);
            } else {
                userLevelTextPaint.setTextSize((bigCircleRadius * 6));
                userLevelTextPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
                canvas.drawText(getUserLevelLikeString(), levelCircleXPosition, levelCircleYPosition + (userLevelTextPaint.getTextSize() / 4), userLevelTextPaint);
            }
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = measureWidth(widthMeasureSpec);
        int height = measureHeight(heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    private int measureWidth(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            // The parent has determined an exact size for the child.
            result = specSize;
        } else if (specMode == MeasureSpec.AT_MOST) {
            // The child can be as large as it wants up to the specified size.
            result = specSize;
        } else {
            // The parent has not imposed any constraint on the child.
            result = canvasSize;
        }
        return result;
    }

    private int measureHeight(int measureSpecHeight) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpecHeight);
        int specSize = MeasureSpec.getSize(measureSpecHeight);

        if (specMode == MeasureSpec.EXACTLY) {
            // We were told how big to be
            result = specSize;
        } else if (specMode == MeasureSpec.AT_MOST) {
            // The child can be as large as it wants up to the specified size.
            result = specSize;
        } else {
            // Measure the text (beware: ascent is a negative number)
            result = canvasSize;
        }

        return (result + 2);
    }

    public Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable == null) {
            return null;
        } else if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }
    private Runnable getAnimationRunnable(){
        return new Runnable() {
            @Override
            public void run() {
                if(isDrawingAnimationFinish){
                    animationHandler.removeCallbacks(this);
                    isAnimationDrawingStillWork = false;
                    drawingCounter = 0;
                    animateDrawingAtBegin = false;
//                    drawUserLevelLabels(canvas);
                }else {
//                    drawAllUserLevelCircles(canvas);
                    drawingCounter++;
                    //finish drawing if counter has been reached last level circle
                    if(drawingCounter == (LEVELS_NUMBER * LEVELS_STEP)) isDrawingAnimationFinish = true;

                    animationHandler.postDelayed(this, ANIMATION_DELAY);
                    invalidate();
                }
            }
        };
    }

    private int getNewCircleXPosition(int counter){
        return (int)(circleUserLevelCenterX + Math.sin(Math.toRadians(counter * ((float)360 / (LEVELS_NUMBER * LEVELS_STEP))))*circleUserLevelRadius);
    }
    private int getNewCircleYPosition(int counter){
        return (int)(circleUserLevelCenterY + Math.cos(Math.toRadians(counter * ((float) 360 / (LEVELS_NUMBER * LEVELS_STEP))))*circleUserLevelRadius);
    }
    private float getDensity(){
        return  (getContext().getResources().getDisplayMetrics().density + 0.5f);
    }
    public void setUserLevel(int level,boolean invalid) {
        userLevel = (level >= (levelColors.length -1)) ? ((levelColors.length -2)) : (level - 1);//we remove 1 because userLevel colors array
        if(invalid) invalidate();
    }
    public boolean isShowLevelLabel(){
        return showLevelLabel;
    }
    public void isShowLevelLabel(boolean show){
        showLevelLabel = show;
        invalidate();
    }
    public int getUserLevel() {
        return (userLevel+1);////we add 1 when return result because we want correct results
    }
    public String getUserLevelLikeString() {
        return Integer.toString(userLevel + 1);////we add 1 when return result because we want correct results
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_UP) {
            if(listener != null){
                this.bringToFront();
                this.onAnimationEnd();
                ViewPropertyAnimator animator = (isProfileImageZoomIn) ?
                        (this.animate().scaleX(1.0f).scaleY(1.0f)) :
                        (this.animate().scaleX(1.5f).scaleY(1.5f));
                animator.start();
                isProfileImageZoomIn = !isProfileImageZoomIn;
                animator.setListener(this);
            }
        }
        return super.dispatchTouchEvent(event);
    }


    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if(event.getAction() == KeyEvent.ACTION_UP && (event.getKeyCode() == KeyEvent.KEYCODE_DPAD_CENTER || event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
            if(listener != null){
                listener.onClick(this);
            }
        }
        return super.dispatchKeyEvent(event);
    }
    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {
        listener.onClick(this);
    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }
}
