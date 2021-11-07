package cn.example.androidProject.cameraAlbum

import android.graphics.Bitmap
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import cn.example.androidProject.databinding.CameraAlbumActivityBinding
import java.io.File


private const val TAKE_PHOTO = 1
private const val AUTHORITY = "cn.example.androidProject.cameraAlbum"

/*************************
 * @ClassName: CameraAlbumActivity.kt
 * @Dir_Path: cn\example\androidProject\cameraAlbum\CameraAlbumActivity.kt
 * @User: Lzp
 * @Author: BarryAllen
 * @Time: 2021/11/7 22:06 周日
 * @Description:
 **************************/
class CameraAlbumActivity : AppCompatActivity() {

    private val mBinding by lazy { CameraAlbumActivityBinding.inflate(layoutInflater) }
    private lateinit var imageUri: Uri
    private lateinit var outputImage: File
    private val camera = registerForActivityResult(ActivityResultContracts.TakePicturePreview()) {
        Log.e("aaa","width = ${it.width}, height = ${it.height} ")
        it?.let { mBinding.imageView.setImageBitmap(it) }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        initComponent()

    }

    private fun initComponent() {
        mBinding.apply {
            takePhoto.setOnClickListener { takePhotos() }
        }
    }

    private fun takePhotos() {
        outputImage = File(externalCacheDir, "output_image.jpg")
        outputImage.delete().takeIf { outputImage.exists() }
        outputImage.createNewFile()
        imageUri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            FileProvider.getUriForFile(this, AUTHORITY, outputImage)
        } else {
            Uri.fromFile(outputImage)
        }
        camera.launch(null)
    }

    private fun rotateIfRequired(bitmap: Bitmap): Bitmap {
        val exif = ExifInterface(outputImage.path)
        return when (exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)) {
            ExifInterface.ORIENTATION_ROTATE_90 -> rotateBitmap(bitmap, 90)
            ExifInterface.ORIENTATION_ROTATE_180 -> rotateBitmap(bitmap, 180)
            ExifInterface.ORIENTATION_ROTATE_270 -> rotateBitmap(bitmap, 270)
            else -> bitmap
        }
    }

    private fun rotateBitmap(bitmap: Bitmap, degree: Int): Bitmap {
        Matrix().apply {
            postRotate(degree.toFloat())
            return Bitmap.createBitmap(bitmap, 0, 0, 500, 450 , this, true)
        }
    }
}