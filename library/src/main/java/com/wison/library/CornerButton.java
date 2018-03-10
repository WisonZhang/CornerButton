package com.wison.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

public class CornerButton extends AppCompatButton {

    // 默认按钮颜色
    private static final int DEFAULT_COLOR = Color.WHITE;

    // 默认边框颜色
    private static final int DEFAULT_STROKE_COLOR = Color.TRANSPARENT;

    // 默认边框宽度
    private static final float DEFAULT_STROKE_WIDTH = 0;

    // 默认圆角大小
    private static final float DEFAULT_CORNER = 0;

    // 正常展示颜色
    private int normalColor;

    // 按下颜色
    private int pressedColor;

    // 边框颜色
    private int strokeColor;

    // 边框大小
    private float strokeWidth;

    // 圆角大小
    private float corner;

    private GradientDrawable normalDrawable;
    private GradientDrawable pressedDrawable;

    public CornerButton(Context context) {
        this(context, null);
    }

    public CornerButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CornerButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(attrs);
        init();
    }

    private void initAttrs(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CornerButton);
            normalColor = typedArray.getColor(R.styleable.CornerButton_normal_color, DEFAULT_COLOR);
            pressedColor = typedArray.getColor(R.styleable.CornerButton_pressed_color, 0);
            strokeWidth = typedArray.getDimension(R.styleable.CornerButton_stroke_width, DEFAULT_STROKE_WIDTH);
            strokeColor = typedArray.getColor(R.styleable.CornerButton_stroke_color, DEFAULT_STROKE_COLOR);
            corner = typedArray.getDimension(R.styleable.CornerButton_corner, DEFAULT_CORNER);
            typedArray.recycle();
        }
    }

    private void init() {
        setClickable(true);
        normalDrawable = new GradientDrawable();
        pressedDrawable = new GradientDrawable();

        if (normalColor == 0) {
            normalColor = DEFAULT_COLOR;
        }

        if (pressedColor == 0) {
            makePressColor(normalColor);
        }

        if (strokeColor == 0) {
            strokeColor = DEFAULT_STROKE_COLOR;
        }

        normalDrawable.setColor(normalColor);
        pressedDrawable.setColor(pressedColor);

        normalDrawable.setCornerRadius(corner);
        pressedDrawable.setCornerRadius(corner);

        normalDrawable.setStroke((int) strokeWidth, strokeColor);
        pressedDrawable.setStroke((int) strokeWidth, strokeColor);

        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, pressedDrawable);
        stateListDrawable.addState(new int[]{}, normalDrawable);

        setBackgroundDrawable(stateListDrawable);
    }

    private void makePressColor(int color) {
        int line = 25;

        int red = (color & 0xff0000) >> 16;
        int green = (color & 0x00ff00) >> 8;
        int blue = (color & 0x0000ff);

        if (red > line) {
            red = red - line;
        }
        if (green > line) {
            green = green - line;
        }
        if (blue > line) {
            blue = blue - line;
        }

        pressedColor = Color.rgb(red, green, blue);
    }

    /**
     * 设置正常颜色
     */
    public void setNormalColor(int normalColor) {
        if (normalColor == 0) {
            return;
        }
        this.normalColor = normalColor;
        normalDrawable.setColor(normalColor);
    }

    /**
     * 设置按下颜色
     */
    public void setPressedColor(int pressedColor) {
        if (pressedColor == 0) {
            return;
        }
        this.pressedColor = pressedColor;
        pressedDrawable.setColor(pressedColor);
    }

    /**
     * 设置边框颜色
     */
    public void setStrokeColor(int strokeColor) {
        if (strokeColor == 0) {
            return;
        }
        this.strokeColor = strokeColor;
        normalDrawable.setStroke((int) strokeWidth, strokeColor);
        pressedDrawable.setStroke((int) strokeWidth, strokeColor);
    }

    /**
     * 设置边框大小
     */
    public void setStrokeWidth(float strokeWidth) {
        this.strokeWidth = strokeWidth;
        normalDrawable.setStroke((int) strokeWidth, strokeColor);
        pressedDrawable.setStroke((int) strokeWidth, strokeColor);
    }

    /**
     * 设置圆角大小
     */
    public void setCorner(float corner) {
        this.corner = corner;
        normalDrawable.setCornerRadius(corner);
        pressedDrawable.setCornerRadius(corner);
    }

}
