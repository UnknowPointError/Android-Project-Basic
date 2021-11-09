package cn.example.androidProject.cameraAlbum

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContracts as ResultContracts
import androidx.core.content.FileProvider
import androidx.exifinterface.media.ExifInterface
import cn.example.androidProject.Util.showToasts
import cn.example.androidProject.databinding.CameraAlbumActivityBinding
import java.io.File


/*************************
 * @ClassName: CameraAlbumActivity.kt
 * @Dir_Path: cn\example\androidProject\cameraAlbum\CameraAlbumActivity.kt
 * @User: Lzp
 * @Author: BarryAllen
 * @Time: 2021/11/7 22:06 周日
 * @Description:
 **************************/
class CameraAlbumActivity : AppCompatActivity() {

    companion object {
        private const val AUTHORITY = "cn.example.androidProject.cameraAlbum"
    }

    private lateinit var imageUri: Uri
    private lateinit var outputImage: File
    private val mBinding by lazy { CameraAlbumActivityBinding.inflate(layoutInflater) }
    private val camera1 = registerForActivityResult(ResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {
            val bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(imageUri))
            bitmap?.let { mBinding.imageView.setImageBitmap(rotateIfRequired(bitmap)) }
        }
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

    private val camera = registerForActivityResult(TakePicture()) {
        val bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(imageUri))
        bitmap?.let {
            mBinding.imageView.setImageBitmap(rotateIfRequired(bitmap))
            this.showToasts("拍摄的照片显示成功")
        }
    }

    inner class TakePicture : ResultContracts.TakePicturePreview() {
        override fun createIntent(context: Context, input: Void?): Intent {
            super.createIntent(context, input)
            val intent = Intent("android.media.action.IMAGE_CAPTURE")
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
            return intent
        }
    }

    private fun takePhotos() {
        outputImage = File(externalCacheDir, "output_image.jpg")
        outputImage.delete().takeIf { outputImage.exists() }
        outputImage.createNewFile()
        val isVersionSDK = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N
        imageUri =
            if (isVersionSDK) FileProvider.getUriForFile(this, AUTHORITY, outputImage)
            else Uri.fromFile(outputImage)
        camera.launch(null)
    }

    private fun rotateIfRequired(bitmap: Bitmap): Bitmap {
        val exif = ExifInterface(outputImage.path)
        return when (exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,
            ExifInterface.ORIENTATION_NORMAL)) {
            ExifInterface.ORIENTATION_ROTATE_90 -> rotateBitmap(bitmap, 90)
            ExifInterface.ORIENTATION_ROTATE_180 -> rotateBitmap(bitmap, 180)
            ExifInterface.ORIENTATION_ROTATE_270 -> rotateBitmap(bitmap, 270)
            else -> bitmap
        }
    }

    private fun rotateBitmap(bitmap: Bitmap, degree: Int): Bitmap {
        Matrix().apply {
            postRotate(degree.toFloat())
            return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, this, true)
        }
    }
}