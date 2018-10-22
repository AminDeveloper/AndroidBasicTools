package ir.mo.amin.hosseini.basictools

import android.graphics.Bitmap
import android.os.Bundle
import ir.mo.amin.hosseini.commontools.Activities.BaseActivity
import ir.mo.amin.hosseini.imagetools.ImagePickerHelpers.ImagePickerHelper
import kotlinx.android.synthetic.main.image_tools_test.*
import java.io.File

/**
 *
 */
class ImageToolsTest : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.image_tools_test)
        button.setOnClickListener { showNotification() }
    }


    private fun showNotification() {
        ImagePickerHelper(this).getImage({ file: File, bitmap: Bitmap ->
            imageView.setImageBitmap(bitmap)
        })
    }


}