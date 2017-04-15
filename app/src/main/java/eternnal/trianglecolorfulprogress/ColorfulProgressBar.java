/**
 * Copyright (C)  2016 深圳市狗尾草智能科技有限公司
 * Gowild_Holoera_App_Android
 * ColorfulProgressBar.java
 */
package eternnal.trianglecolorfulprogress;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

/**
 * @author duliu3
 * @version 1.0
 *          <p><strong>Features draft description.主要功能介绍</strong></p>
 * @since 2017/4/15 11:27
 */
public class ColorfulProgressBar extends View {

    // ===========================================================
    // Constants
    // ===========================================================

    private static final String TAG = ColorfulProgressBar.class.getSimpleName();
    private float mLineWidth;
    private float mLineThickness;
    private int mDefaultHeight;
    private int mDefaultWidth;
    private Paint mLinePaint;
    private Paint mTrianglePaint;
    private int mTriangleLength;
    private float mProgressLength;
    private int mColorBlue;
    private int mColorRed;
    private int mColorWhite;
    private float mTriangleCenterLength;
    private int mProgress;
    private int mBorderPadding;

    public ColorfulProgressBar(Context context) {
        this(context, null);
    }

    public ColorfulProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColorfulProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    // ===========================================================
    // Static Fields
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================


    // ===========================================================
    // Constructors
    // ===========================================================


    // ===========================================================
    // Getter or Setter
    // ===========================================================


    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        LinearGradient gradient = new LinearGradient(0, 0, getMeasuredWidth(),
                getMeasuredHeight(), new int[]{mColorBlue, Color.WHITE, mColorRed}, new float[]{0f, 0.7f, 1f}, Shader.TileMode.MIRROR);
        //        LinearGradient gradient = new LinearGradient(0, 0, 400, 400, , getResources().getColor(R.color.gowild_grade_redFA4254), Shader.TileMode.MIRROR);
        mLinePaint.setShader(gradient);
        canvas.drawLine(mBorderPadding, 0, getBarLength() + mBorderPadding, 0, mLinePaint);
        Path path = new Path();
        path.moveTo(mProgressLength + mBorderPadding, mLineThickness);
        path.lineTo(getRightBottomX(), getBottomY());
        path.lineTo(getLeftBottomX(), getBottomY());
        path.close();
        canvas.drawPath(path, mTrianglePaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (mProgress != 0) {
            mProgressLength = (float) mProgress / 100 * getBarLength();
            invalidate();
        }
        super.onSizeChanged(w, h, oldw, oldh);
    }


    private float getRightBottomX() {
        return mTriangleLength / 2 + mProgressLength + mBorderPadding;
    }

    private float getLeftBottomX() {
        return mProgressLength - mTriangleLength / 2 + mBorderPadding;
    }

    private float getBottomY() {
        return mLineThickness + mTriangleCenterLength;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width;
        int height;
        //1.0 获得父容器的尺寸信息
        final int measureWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        final int measureHeightMode = MeasureSpec.getMode(heightMeasureSpec);
        final int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        final int measureHeight = MeasureSpec.getSize(heightMeasureSpec);
        //2.0 处理用户设置不同的宽高类型
        //2.1 当用户设置宽度 match_parent 的时候,手动设置默认值
        if (measureWidthMode == MeasureSpec.AT_MOST) {
            width = measureWidth;
        } else if (measureWidthMode == MeasureSpec.EXACTLY) {
            width = measureWidth;
        } else {
            width = mDefaultWidth;
        }
        //2.2 当用户设置高度 match_parent 的时候,手动设置默认值
        if (measureHeightMode == MeasureSpec.AT_MOST) {
            height = Math.min(mDefaultHeight, measureHeight);
        } else if (measureHeightMode == MeasureSpec.EXACTLY) {
            height = measureHeight;
        } else {
            height = mDefaultHeight;
        }
        setMeasuredDimension(width, height);
    }

    // ===========================================================
    // Methods
    // ===========================================================


    private void init() {
        mColorBlue = Color.BLUE;
        mColorRed = Color.RED;
        mColorWhite = Color.WHITE;

        final DisplayMetrics metrics = getResources().getDisplayMetrics();
        mDefaultHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                40,
                metrics);
        mDefaultWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                270,
                metrics);
        mTriangleLength = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                10,
                metrics);
        mBorderPadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                5,
                metrics);
        //        mTriangleCenterLength = (float) Math.sqrt((Math.pow(mTriangleLength, 2) - Math.pow(mTriangleLength / 2, 2)));
        mTriangleCenterLength = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                7,
                metrics);
        mProgressLength = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                0,
                metrics);
        mLineWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 250, metrics);
        mLineThickness = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, metrics);

        mLinePaint = new Paint();
        mLinePaint.setAntiAlias(true);
        mLinePaint.setStrokeWidth(mLineThickness);

        mTrianglePaint = new Paint();
        /*去锯齿*/
        mTrianglePaint.setAntiAlias(true);
        /*设置paint的颜色*/
        mTrianglePaint.setColor(mColorBlue);
        /*设置paint的　style　为STROKE：空心*/
        mTrianglePaint.setStyle(Paint.Style.FILL);
    }

    public void setProgress(int progress) {
        if (progress < 0 | progress > 100) {
            return;
        }

        mProgress = progress;
        invalidate();
    }

    public int getProgress() {
        return mProgress;
    }

    public float getBarLength() {
        return getMeasuredWidth() - mBorderPadding * 2;
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
