package jp.rei.andou.githubbrowser.presentation.authorization;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import javax.inject.Inject;

import jp.rei.andou.githubbrowser.App;
import jp.rei.andou.githubbrowser.R;
import jp.rei.andou.githubbrowser.databinding.SignInBinding;
import jp.rei.andou.githubbrowser.presentation.common.AutoConfigFragment;

public class SignInFragment extends AutoConfigFragment {

    @Inject
    AuthorizationNavigator authorizationNavigator;
    @Inject
    SignInViewModel viewModel;

    @Override
    protected void onInjectInstance(App application) {
        application.inject(this);
    }

    @Override
    protected void onComponentDestroying(App application) {
        application.destroy(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        SignInBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.sign_in, container, false
        );
        binding.setHandler(authorizationNavigator);
        binding.setViewModel((SignInViewModelImpl) viewModel);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.getErrorToastMessages()
                .observe(this,
                        message -> Toast.makeText(
                                getContext(),
                                message.getContent(),
                                Toast.LENGTH_LONG
                        ).show()
                );
    }
}
