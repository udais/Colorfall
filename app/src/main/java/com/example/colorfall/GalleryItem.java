package com.example.colorfall;
import com.mindorks.placeholderview.Animation;
import com.mindorks.placeholderview.annotations.*;
import android.widget.ImageView;
import android.graphics.drawable.Drawable;

//WORK IN PROGRESS//
@SuppressWarnings("ALL")
@Animate(Animation.CARD_LEFT_IN_DESC)
@NonReusable
@Layout(R.layout.activity_gallery)
class GalleryItem{

    @View(R.id.imageView1)
    private ImageView imageView;

    private final Drawable mDrawable;

    @SuppressWarnings("unused")
    public GalleryItem(Drawable drawable) {

        mDrawable = drawable;
    }

    @Resolve
    private void onResolved() {

        imageView.setImageDrawable(mDrawable);
    }
}