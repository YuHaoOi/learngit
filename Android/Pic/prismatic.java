/**
 * 创建棱形
 */
public static Bitmap createPrismatic(Bitmap source){
    int width = source.getWidth();
    int height = source.getHeight();
    Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(bitmap);
    Paint paint = new Paint();
    paint.setAntiAlias(true);
    BitmapShader shader = new BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
    paint.setShader(shader);
    // 计算棱形的顶点
    Path path = new Path();
    path.moveTo(width / 2, 0);
    path.lineTo(0, height / 2);
    path.lineTo(width / 2, height);
    path.lineTo(width, height / 2);
    path.close();
    canvas.drawPath(path, paint);
    source.recycle();
    return bitmap;
}
