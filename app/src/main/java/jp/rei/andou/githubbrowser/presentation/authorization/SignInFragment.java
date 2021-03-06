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
import jp.rei.andou.githubbrowser.presentation.common.ConfigurableFragment;
import jp.rei.andou.githubbrowser.presentation.general.Navigation;
import jp.rei.andou.githubbrowser.presentation.main.MainActivity;

public class SignInFragment extends ConfigurableFragment {

    @Inject
    SignInViewModel viewModel;

    @Override
    protected void onInjectInstance(App application) {
        application.inject(this);
    }

    @Override
    protected void onComponentDestroying(App application) {
        application.destroySignInComponent();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        viewModel.getErrorToastMessages()
                .observe(this,
                        message -> {
                            if (message == null) {
                                return;
                            }
                            showToast(message.getContent());
                        }
                );
        viewModel.getNavigationEvents().observe(this, navigationSingleEvent -> {
            Navigation navigation = navigationSingleEvent.getContent();
            if (navigation == null) {
                return;
            }
            switch (navigation) {

                case BROWSER: {
                    ((MainActivity) getActivity()).routeToBrowserScreen();
                    break;
                }
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        SignInBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.sign_in, container, false
        );
        binding.setViewModel((SignInViewModelImpl) viewModel);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    private void showToast(Integer message) {
        if (message == null) {
            return;
        }
        Toast.makeText(
                getContext(),
                message,
                Toast.LENGTH_LONG
        ).show();
    }

}
