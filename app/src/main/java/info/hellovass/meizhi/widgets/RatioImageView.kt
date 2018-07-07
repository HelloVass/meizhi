package info.hellovass.meizhi.widgets

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView

class RatioImageView : ImageView {

    private var originalWidth: Int = 0

    private var originalHeight: Int = 0

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    fun setOriginalSize(width: Int = 50, height: Int = 50) {

        originalWidth = width
        originalHeight = height
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        if (originalWidth > 0 && originalWidth > 0) {

            val ratio: Float = originalWidth / originalHeight * 1.0F

            val width = MeasureSpec.getSize(widthMeasureSpec)
            var height = MeasureSpec.getSize(heightMeasureSpec)

            if (width > 0) {
                height = (width / ratio).toInt()
            }

            setMeasuredDimension(width, height)
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        }
    }
}