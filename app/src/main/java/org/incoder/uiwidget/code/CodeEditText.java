package org.incoder.uiwidget.code;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;

import org.incoder.uiwidget.R;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;

/**
 * 验证码输入自定义控件
 *
 * @author : Jerry xu
 * @date : 2019/7/30 10:33
 */
public class CodeEditText extends android.support.v7.widget.AppCompatEditText {

    /**
     * 空心
     */
    private static final int TYPE_HOLLOW = 1;
    /**
     * 实心
     */
    private static final int TYPE_SOLID = 2;
    /**
     * 下划线
     */
    private static final int TYPE_UNDERLINE = 3;

    /**
     * 边界画笔
     */
    private Paint borderPaint;
    /**
     * 实心块画笔
     */
    private Paint blockPaint;
    private Paint textPaint;
    private Paint cursorPaint;

    private RectF borderRectF;
    private RectF boxRectF;//小方块、小矩形

    /**
     * 方框宽，高
     */
    private int boxWidth, boxHeight;
    /**
     * 方框之间间隙
     */
    private int spacing;
    /**
     * 方框圆角
     */
    private int corner;
    /**
     * 验证码最大位数
     */
    private int maxLength;
    /**
     * 边界粗细
     */
    private int borderWidth;
    /**
     * 是否是密码类型
     */
    private boolean password;
    /**
     * 显示光标
     */
    private boolean showCursor;
    /**
     * 光标闪动间隔
     */
    private int cursorDuration;
    /**
     * 光标宽度
     */
    private int cursorWidth;
    /**
     * 光标颜色
     */
    private int cursorColor;
    /**
     * 实心方式、空心方式
     */
    private int type;
    private int borderColor;
    private int blockColor;
    private int textColor;

    private boolean isCursorShowing;

    private CharSequence contentText;

    private TextChangedListener textChangedListener;

    private Timer timer;
    private ScheduledExecutorService mExecutorService;
    private TimerTask timerTask;

    public CodeEditText(Context context) {
        this(context, null);
    }

    public CodeEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CodeEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setLongClickable(false);
        setTextIsSelectable(false);
        setCustomSelectionActionModeCallback(new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode actionMode) {

            }
        });

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CodeEditText);

        password = ta.getBoolean(R.styleable.CodeEditText_password, false);
        showCursor = ta.getBoolean(R.styleable.CodeEditText_showCursor, true);
        borderColor = ta.getColor(R.styleable.CodeEditText_borderColor, Color.GRAY);
        blockColor = ta.getColor(R.styleable.CodeEditText_blockColor, Color.LTGRAY);
        textColor = ta.getColor(R.styleable.CodeEditText_textColor, Color.DKGRAY);
        cursorColor = ta.getColor(R.styleable.CodeEditText_cursorColor, ContextCompat.getColor(getContext(), R.color.colorAccent));
        corner = (int) ta.getDimension(R.styleable.CodeEditText_corner, 0);
        spacing = (int) ta.getDimension(R.styleable.CodeEditText_blockSpacing, 0);
        type = ta.getInt(R.styleable.CodeEditText_separateType, TYPE_HOLLOW);
        maxLength = ta.getInt(R.styleable.CodeEditText_maxLength, 6);
        cursorDuration = ta.getInt(R.styleable.CodeEditText_cursorDuration, 500);
        cursorWidth = (int) ta.getDimension(R.styleable.CodeEditText_cursorWidth, 2);
        borderWidth = (int) ta.getDimension(R.styleable.CodeEditText_borderWidth, 2);
        ta.recycle();
        init();

    }

    public void setSpacing(int spacing) {
        this.spacing = spacing;
        postInvalidate();
    }

    public void setCorner(int corner) {
        this.corner = corner;
        postInvalidate();
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
        postInvalidate();
    }

    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
        postInvalidate();
    }

    public void setPassword(boolean password) {
        this.password = password;
        postInvalidate();
    }

    public void setShowCursor(boolean showCursor) {
        this.showCursor = showCursor;
        postInvalidate();
    }

    public void setCursorDuration(int cursorDuration) {
        this.cursorDuration = cursorDuration;
        postInvalidate();
    }

    public void setCursorWidth(int cursorWidth) {
        this.cursorWidth = cursorWidth;
        postInvalidate();
    }

    public void setCursorColor(int cursorColor) {
        this.cursorColor = cursorColor;
        postInvalidate();
    }

    public void setType(int type) {
        this.type = type;
        postInvalidate();
    }

    public void setBorderColor(int borderColor) {
        this.borderColor = borderColor;
        postInvalidate();
    }

    public void setBlockColor(int blockColor) {
        this.blockColor = blockColor;
        postInvalidate();
    }

    public void clearText() {
        setText("");
    }

    @Override
    public void setTextColor(int textColor) {
        this.textColor = textColor;
        postInvalidate();
    }

    private void init() {
        this.setFocusableInTouchMode(true);
        this.setFocusable(true);
        this.requestFocus();
        this.setCursorVisible(false);
        this.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});

        new Handler().postDelayed(() -> {
            InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
        }, 500);

        blockPaint = new Paint();
        blockPaint.setAntiAlias(true);
        blockPaint.setColor(blockColor);
        blockPaint.setStyle(Paint.Style.FILL);
        blockPaint.setStrokeWidth(1);

        // 输入文字绘制
        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setColor(textColor);
        textPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        textPaint.setStrokeWidth(1);

        // 边框绘制
        borderPaint = new Paint();
        borderPaint.setAntiAlias(true);
        borderPaint.setColor(borderColor);
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setStrokeWidth(borderWidth);

        // 光标绘制
        cursorPaint = new Paint();
        cursorPaint.setAntiAlias(true);
        cursorPaint.setColor(cursorColor);
        cursorPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        cursorPaint.setStrokeWidth(cursorWidth);

        borderRectF = new RectF();
        boxRectF = new RectF();

        if (type == TYPE_HOLLOW) {
            spacing = 0;
        }

        timerTask = new TimerTask() {
            @Override
            public void run() {
                isCursorShowing = !isCursorShowing;
                postInvalidate();
            }
        };
        timer = new Timer();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        boxWidth = (w - spacing * (maxLength + 1)) / maxLength;
        boxHeight = h;
        borderRectF.set(0, 0, w, h);
        textPaint.setTextSize(boxWidth / 2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawRect(canvas);
        drawText(canvas, contentText);
        drawCursor(canvas);
    }

    /**
     * 绘制矩形
     *
     * @param canvas canvas
     */
    private void drawRect(Canvas canvas) {
        for (int i = 0; i < maxLength; i++) {

            boxRectF.set(spacing * (i + 1) + boxWidth * i, 0,
                    spacing * (i + 1) + boxWidth * i + boxWidth, boxHeight);

            if (type == TYPE_SOLID) {
                canvas.drawRoundRect(boxRectF, corner, corner, blockPaint);
            } else if (type == TYPE_UNDERLINE) {
                canvas.drawLine(boxRectF.left, boxRectF.bottom, boxRectF.right, boxRectF.bottom, borderPaint);
            } else if (type == TYPE_HOLLOW) {
                if (i == 0 || i == maxLength) {
                    continue;
                }
                canvas.drawLine(boxRectF.left, boxRectF.top, boxRectF.left, boxRectF.bottom, borderPaint);
            }
        }
        // 绘制圆角矩形
        if (type == TYPE_HOLLOW) {
            canvas.drawRoundRect(borderRectF, corner, corner, borderPaint);
        }
    }

    /**
     * 绘制文字
     *
     * @param canvas       canvas
     * @param charSequence charSequence
     */
    private void drawText(Canvas canvas, CharSequence charSequence) {
        for (int i = 0; i < charSequence.length(); i++) {
            int startX = spacing * (i + 1) + boxWidth * i;
            int startY = 0;
            int baseX = (int) (startX + boxWidth / 2 - textPaint.measureText(String.valueOf(charSequence.charAt(i))) / 2);
            int baseY = (int) (startY + boxHeight / 2 - (textPaint.descent() + textPaint.ascent()) / 2);
            int centerX = startX + boxWidth / 2;
            int centerY = startY + boxHeight / 2;
            int radius = Math.min(boxWidth, boxHeight) / 6;
            if (password) {
                canvas.drawCircle(centerX, centerY, radius, textPaint);
            } else {
                canvas.drawText(String.valueOf(charSequence.charAt(i)), baseX, baseY, textPaint);
            }
        }
    }

    /**
     * 绘制光标
     *
     * @param canvas canvas
     */
    private void drawCursor(Canvas canvas) {
        if (!isCursorShowing && showCursor && contentText.length() < maxLength && hasFocus()) {
            int cursorPosition = contentText.length() + 1;
            int startX = spacing * cursorPosition + boxWidth * (cursorPosition - 1) + boxWidth / 2;
            int startY = boxHeight / 4;
            int endX = startX;
            int endY = boxHeight - boxHeight / 4;
            canvas.drawLine(startX, startY, endX, endY, cursorPaint);
        }
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        contentText = text;
        invalidate();

        if (textChangedListener != null) {
            if (text.length() == maxLength) {
                textChangedListener.textCompleted(text);
            } else {
                textChangedListener.textChanged(text);
            }
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        // cursorFlashTime为光标闪动的间隔时间
        timer.scheduleAtFixedRate(timerTask, 0, cursorDuration);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        timer.cancel();
    }

    @Override
    public boolean onTextContextMenuItem(int id) {
        return true;
    }

    @Override
    protected void onSelectionChanged(int selStart, int selEnd) {
        CharSequence text = getText();
        if (text != null) {
            if (selStart != text.length() || selEnd != text.length()) {
                setSelection(text.length(), text.length());
                return;
            }
        }
        super.onSelectionChanged(selStart, selEnd);
    }


    public void setTextChangedListener(TextChangedListener listener) {
        textChangedListener = listener;
    }

    /**
     * 密码监听者
     */
    public interface TextChangedListener {

        /**
         * 输入/删除监听
         *
         * @param changeText 输入/删除的字符
         */
        void textChanged(CharSequence changeText);

        /**
         * 输入完成
         *
         * @param text 输入的字符
         */
        void textCompleted(CharSequence text);
    }

}
