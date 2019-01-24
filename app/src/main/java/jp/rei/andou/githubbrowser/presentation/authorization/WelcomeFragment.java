package jp.rei.andou.githubbrowser.presentation.authorization;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import jp.rei.andou.githubbrowser.App;
import jp.rei.andou.githubbrowser.R;
import jp.rei.andou.githubbrowser.databinding.AuthorizationBinding;
import jp.rei.andou.githubbrowser.presentation.common.ConfigurableFragment;
import jp.rei.andou.githubbrowser.presentation.general.GeneralNavigator;

public class WelcomeFragment extends ConfigurableFragment {

    @Inject
    GeneralNavigator authorizationNavigator;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        AuthorizationBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.authorization, container, false
        );
        binding.setHandler(authorizationNavigator);
        return binding.getRoot();
    }

    @Override
    protected void onInjectInstance(App application) {
        application.inject(this);
    }

    @Override
    protected void onComponentDestroying(App application) {
        application.destroy(this);
    }
}
