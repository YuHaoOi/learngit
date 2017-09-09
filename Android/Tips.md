#### 启动活动的最佳写法

```java
//好处是可以告诉第一个activity需要的参数个数
public static void actionStart(Context context, String data1, String data2) {
        Intent intent = new Intent(context, SecondActivity.class);
        intent.putExtra("param1", data1);
        intent.putExtra("param2", data2);
        context.startActivity(intent);
    }
```

```java
//在当前activity中启动第二个activity:
SecondActivity.actionStart(this, "data1", "data2");
```

#### 获取view的正确方式
```java
View headView = LayoutInflater.from(this).inflate(R.layout.more_head, (ViewGroup) findViewById(android.R.id.content), false);
```

#### 判断字符串是否是数字
```java
String test = "1234";
String test2 = "1234a";
boolean digitsOnly = TextUtils.isDigitsOnly(test); //true
boolean digitsOnly1 = TextUtils.isDigitsOnly(test2); //false
```

#### 字符串拼接
```java
//1 字符串拼接
CharSequence concat = TextUtils.concat("aaa", "bbb", "ccc");
```

#### 比较两个字符串是否相等, 做了非null判断
```java
String test = "1234";
String test2 = "1234a";
String test3 = null;
boolean equals = TextUtils.equals(test, test2); //false
boolean equals2 = TextUtils.equals(test, test3); //false
```

#### 时间格式化
```java
long time = 1479037043000l;
String s = DateUtils.formatDateTime(this, time, DateUtils.FORMAT_SHOW_DATE); //11月13日

long time = 1479037043000l;
//2016年11月13日星期日 19:37
String s = DateUtils.formatDateTime(this, time, DateUtils.FORMAT_SHOW_YEAR | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_WEEKDAY | DateUtils.FORMAT_SHOW_TIME);

long time = 1479037043000l;
boolean isToday = DateUtils.isToday(time); //false
boolean isToday2 = DateUtils.isToday(System.currentTimeMillis()); //true


CharSequence time = DateUtils.getRelativeTimeSpanString(1476513214000l); //2016年10月15日
CharSequence time = DateUtils.getRelativeTimeSpanString(System.currentTimeMillis()-1000); //0分钟
```

#### Android 彻底退出应用
```java
android.os.Process.killProcess(android.os.Process.myPid()); //方式1
System.exit(0); //方式2
```

#### Android屏幕操作
```java
//如果当期不是横屏
if (context.getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
   context.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);// 设置为横屏
} else {
   context.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);// 设置为竖屏
}

//设置全屏
//1代码设置
requestWindowFeature(Window.FEATURE_NO_TITLE); 
getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN , WindowManager.LayoutParams. FLAG_FULLSCREEN); 
//2xml设置
<activity android:theme="@android:style/Theme.NoTitleBar.Fullscreen" /> 

//进入全屏、退出全屏
private void full(boolean enable) {
    if (enable) {
        WindowManager.LayoutParams lp =  getActivity().getWindow().getAttributes();
        lp.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getActivity().getWindow().setAttributes(lp);
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    } else {
        WindowManager.LayoutParams attr = getActivity().getWindow().getAttributes();
        attr.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getActivity().getWindow().setAttributes(attr);
	getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }
}
//屏幕变化监听
@Override
public void onConfigurationChanged(Configuration newConfig) {
    super.onConfigurationChanged(newConfig);
    if(newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE){ //横屏
    } else {
    }
}

```

#### CountDownTimer倒计时
```java
CountDownTimer timer = new CountDownTimer(60000, 1000) { //总时间，间隔时间
    @Override
    public void onTick(long millisUntilFinished) { //每隔间隔时间执行一次
    }
    @Override
    public void onFinish() { //总时间后执行
    }
 };
timer.start(); //开始倒计时
```

#### Android String占位符
```xml
<!-- .2f表示的是保留三位小数的浮点数  -->
<string name="book">书名 (字符串)%1$s,作者(字符串)%2$s,编号(整数)%3$d,价格(浮点型)：%4$.2f</string>
```
代码：
```java
String book=getResources().getString(R.string.book);
String bookTest=String.format(book,"金瓶梅","西门庆",2249,88.3f);
System.out.println("bookTest="+bookTest);//bookTest=书名 (字符串)金瓶梅,作者(字符串)西门庆,编号(整数)2249,价格(浮点型)：88.30
```

#### 使用UncaughtExceptionHandler捕获全局异常
首先实现创建一个类，实现UncaughtExceptionHandler接口。代码如下：
```java
public class CrashHandler implements UncaughtExceptionHandler{

	@Override
        public void uncaughtException(Thread thread, Throwable ex){
		//处理异常
		Log.e("崩溃",thread.getName()+ex.toString());
		//发送到服务器//dialog提醒
	}

}
```
在程序的入口activity的oncreate中添加：
```java
Thread.setDefaultUncaughtExceptionHandler(new CrashHandler());
```

## 保留两位小数 
```java
BigDecimal bigDecimal = new BigDecimal(d).setScale(2, BigDecimal.ROUND_HALF_UP);//四舍五入保留小数点后两位 93.56789 = 93.57
bigDecimal.toString() //获取String数值
```
