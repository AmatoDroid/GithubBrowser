package jp.rei.andou.githubbrowser.presentation.databinding;

import android.databinding.BindingAdapter;
import android.support.design.widget.TextInputLayout;

public class BindingAdapters {

    @BindingAdapter("errorMessage")
    public static void applyError(TextInputLayout textInputLayout, String error) {
        if (error == null) return;
        textInputLayout.setError(error);
    }

}