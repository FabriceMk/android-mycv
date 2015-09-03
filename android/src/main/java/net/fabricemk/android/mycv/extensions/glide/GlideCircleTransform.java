package net.fabricemk.android.mycv.extensions.glide;

import android.content.Context;
import android.graphics.Bitmap;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import net.fabricemk.android.mycv.tools.DrawingTools;

/**
 * Transformation class for the Glide library for having rounded images
 */
public class GlideCircleTransform extends BitmapTransformation {
    public GlideCircleTransform(Context context) {
        super(context);
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap source, int outWidth, int outHeight) {
        return DrawingTools.getCircularBitmapImage(pool, source);
    }

    @Override
    public String getId() {
        return "Glide_Circle_Transformation";
    }
}