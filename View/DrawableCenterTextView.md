```xml
<com.jlkf.myapplication.DrawableCenterTextView
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:drawableLeft="@mipmap/icon_map_search"
    android:drawablePadding="5dp"
    android:text="Hello World!" />
```


```java
public class DrawableCenterTextView extends TextView {

    public DrawableCenterTextView(Context context, AttributeSet attrs,
                                  int defStyle) {
        super(context, attrs, defStyle);
    }

    public DrawableCenterTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawableCenterTextView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable[] drawables = getCompoundDrawables(); //获取四个方向的drawable
        if (drawables != null) {
            Drawable drawableLeft = drawables[0];
            if (drawableLeft != null) {
                float textWidth = getPaint().measureText(getText().toString());
                int drawablePadding = getCompoundDrawablePadding(); 
                int drawableWidth = 0;
                drawableWidth = drawableLeft.getIntrinsicWidth(); //返回drawable的宽度,dp为单位
                float bodyWidth = textWidth + drawableWidth + drawablePadding; //显示的内容的宽度
                canvas.translate((getWidth() - bodyWidth) / 2, 0);  //画笔向x轴平移的位数
            }
        }
        super.onDraw(canvas);
    }
}
```
