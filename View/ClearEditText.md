![image](https://github.com/YuHaoOi/learngit/blob/master/pics/clear_edittext.png)

```xml
<com.jlkf.jdsaleside.home.widgets.ClearEditText
    android:id="@+id/info_ev"
    android:background="@null"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:gravity="center_vertical"
    android:maxLength="10"
    android:hint="请输入贷款金额"
    android:paddingRight="@dimen/theme_padding"
    android:layout_marginLeft="105dp"
    android:textColorHint="@color/theme_text_grey"
    android:textSize="@dimen/theme_text_small"
    android:textColor="@color/theme_text" />
```

```java
public class ClearEditText extends AppCompatEditText implements View.OnFocusChangeListener, TextWatcher {

    private Drawable mClearDrawable;

    public ClearEditText(Context context) {
        super(context);
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mClearDrawable = getCompoundDrawables()[2];
        if (mClearDrawable == null){
            mClearDrawable = ContextCompat.getDrawable(context, R.mipmap.icon_clear);
        }
        mClearDrawable.setBounds(0,0,mClearDrawable.getIntrinsicWidth(),mClearDrawable.getIntrinsicHeight());
        setClearIconVisible(false);
        setOnFocusChangeListener(this);
        addTextChangedListener(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (getCompoundDrawables()[2] != null){
            if (event.getAction() == MotionEvent.ACTION_UP){
                boolean touchable = event.getX() > (getWidth() - getPaddingRight() - mClearDrawable.getIntrinsicWidth())
                        && event.getX() < (getWidth()- getPaddingRight());
                if (touchable){
                    setText("");
                }
            }
        }
        return super.onTouchEvent(event);
    }

    protected void setClearIconVisible(boolean visible) {
        Drawable right = visible ? mClearDrawable : null;
        setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], right, getCompoundDrawables()[3]);
    }

    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        if (hasFocus){
            setClearIconVisible(getText().length() > 0);
        } else {
            setClearIconVisible(false);
        }

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        setClearIconVisible(charSequence.length() > 0);
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}

```
