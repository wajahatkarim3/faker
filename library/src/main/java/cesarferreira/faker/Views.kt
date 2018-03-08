package cesarferreira.faker

import android.content.Context
import android.widget.ImageView
import com.squareup.picasso.Picasso

private val defaultPlaceholder = R.color.defaultPlaceholder
private val defaultError = R.color.errorPlaceholder

fun ImageView.loadFromUrl(url: String, placeholder: Int = defaultPlaceholder, error: Int = defaultError, callback: OnImageLoadListener? = null) =
        loadImage(
                context = this.context.applicationContext,
                imageView = this,
                url = url,
                placeholder = placeholder,
                error = error,
                callback = callback
        )

fun ImageView.loadRandomImage(callback: OnImageLoadListener? = null) {
    this.post({
        loadImage(
                context = this.context.applicationContext,
                imageView = this,
                url = getRandomImage(this.measuredWidth, this.measuredHeight),
                placeholder = defaultPlaceholder,
                error = defaultError,
                callback = callback
        )
    })
}

fun ImageView.loadRandomImage(width: Int, height: Int, callback: OnImageLoadListener? = null) =
        loadImage(
                context = this.context.applicationContext,
                imageView = this,
                url = getRandomImage(width, height),
                placeholder = defaultPlaceholder,
                error = defaultError,
                callback = callback
        )


private fun loadImage(context: Context, imageView: ImageView, url: String, placeholder: Int, error: Int, callback: OnImageLoadListener?) =
        Picasso.with(context)
                .load(url)
                .placeholder(placeholder)
                .error(error)
                .into(imageView, callback)

private fun valueWithSalt(value: Int) = value + (0..20).random()
private fun getRandomImage(width: Int, height: Int) = "https://picsum.photos/${valueWithSalt(width)}/${valueWithSalt(height)}"
