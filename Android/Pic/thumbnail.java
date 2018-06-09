/**
 * 设置缩略图
 */
public static Bitmap createBitmapThumbnail(Bitmap bitMap, boolean needRecycle) {
    int width = bitMap.getWidth();
    int height = bitMap.getHeight();
    // 设置想要的大小
    int newWidth = 200;
    int newHeight = 200;
    // 计算缩放比例
    float scaleWidth = ((float) newWidth) / width;
    float scaleHeight = ((float) newHeight) / height;
    // 取得想要缩放的matrix参数
    Matrix matrix = new Matrix();
    matrix.postScale(scaleWidth, scaleHeight);
    // 得到新的图片
    Bitmap newBitMap = Bitmap.createBitmap(bitMap, 0, 0, width, height, matrix, true);
    if (needRecycle) {
        bitMap.recycle();
    }
    return newBitMap;
}
