package jp.rei.andou.githubbrowser.presentation.databinding;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class BindingAdapters {

    @BindingAdapter("errorMessage")
    public static void applyError(TextInputLayout textInputLayout, String error) {
        if (error == null) return;
        textInputLayout.setError(error);
    }

    @BindingAdapter({"imageUrl", "error"})
    public static void loadImage(ImageView view, String url, Drawable error) {
        Picasso.get().load(url).error(error).centerCrop().into(view);
    }

    @BindingAdapter("android:visibility")
    public static void applyVisibility(View view, boolean condition) {
        view.setVisibility(condition ? View.VISIBLE : View.INVISIBLE);
    }

}