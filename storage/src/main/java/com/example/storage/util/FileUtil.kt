package com.example.storage.util

import android.graphics.Bitmap
import java.io.*

object FileUtil {

    fun saveImage(path: String, bitmap: Bitmap) {
        try {
            val file = File(path)
            //outputStream获取文件的输出流对象
            //writer获取文件的Writer对象
            //printWriter获取文件的PrintWriter对象
            val fos: OutputStream = file.outputStream()
            //压缩格式为JPEG图像，压缩质量为80%
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, fos)
            fos.flush()
            fos.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}
