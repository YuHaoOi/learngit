class fun {
    /**
     * 创建圆形图片
     */
    public static Bitmap createCircle(Bitmap source){
        int width = source.getWidth();
        int height = source.getHeight();
        // 获取边长小的值作为裁剪后的正方形图片的边长
        int size = Math.min(width, height);
        int radius = size / 2;
        // 计算正方形的左上角坐标
        int x = (width - size) / 2;
        int y = (height - size) / 2;
        // 从原图片上裁剪指定大小的正方形生成一张新的图片
        Bitmap transformBitmap = Bitmap.createBitmap(source, x, y, size, size);
        // 创建一张空图片作为画布的背景
        Bitmap bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        // 创建画布
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        // 从剪完后的图片去除内容
        // BitmapShader shader = new BitmapShader(transformBitmap,
        // Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        // paint.setShader(shader);
        // 把内容画成一个圆形
        canvas.drawCircle(radius, radius, radius, paint);
        //取两张图片的交集
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(transformBitmap, 0, 0, paint);
        // 释放资源
        source.recycle();
        return bitmap;
    }
}
