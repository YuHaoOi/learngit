package com.jlkf.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by DuoNuo on 2017/10/9.
 */

public class BitmapUtil {

    /**
     * 设置bitmap options属性，降低图片的质量，像素不会减少
     */
    public static void compressBitmapToFile(Bitmap bitmap, File file){
        // 0-100 100为不压缩
        int options = 100;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void compressBitmapToFiles(String filePath, File file){
        int inSampleSize = 2;
        BitmapFactory.Options options = new BitmapFactory.Options();
        // 数值越高，图片像素越低
        options.inSampleSize = inSampleSize;
        options.inJustDecodeBounds = true;
        //Android中图片有四种属性，分别是：
        //ALPHA_8：每个像素占用1byte内存
        //ARGB_4444：每个像素占用2byte内存
        //ARGB_8888：每个像素占用4byte内存 （默认）
        //RGB_565：每个像素占用2byte内存
        //Android默认的颜色模式为ARGB_8888，这个颜色模式色彩最细腻，显示质量最高。但同样的，占用的内存也最大。 所以在对图片效果不是特别高的情况下使用RGB_565（565没有透明度属性）
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        // 这里一定要将其设置回false，因为之前我们将其设置成了true
        // 设置inJustDecodeBounds为true后，decodeFile并不分配空间，
        // 即，BitmapFactory解码出来的Bitmap为Null,但可计算出原始图片的长度和宽度
        options.inJustDecodeBounds = false;
        //生成图片
        Bitmap bitmap = BitmapFactory.decodeFile(filePath, options);
        //存入本地
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(baos.toByteArray( ));
            fos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置缩略图
     *
     * @param bitMap
     * @param needRecycle
     * @return
     */
    public static Bitmap createBitmapThumbnail(Bitmap bitMap, boolean needRecycle) {
        int width = bitMap.getWidth();
        int height = bitMap.getHeight();
        // 设置想要的大小
        int newWidth = 80;
        int newHeight = 80;
        // 计算缩放比例
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // 得到新的图片
        Bitmap newBitMap = Bitmap.createBitmap(bitMap, 0, 0, width, height,
                matrix, true);
        if (needRecycle) bitMap.recycle();
        return newBitMap;
    }

}
