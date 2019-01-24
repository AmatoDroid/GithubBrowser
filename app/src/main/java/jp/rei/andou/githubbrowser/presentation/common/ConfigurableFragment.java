package jp.rei.andou.githubbrowser.presentation.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import jp.rei.andou.githubbrowser.App;

//todo replace with dagger.android implementation
public abstract class ConfigurableFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActivity() != null) {
            onInjectInstance(((App) getActivity().getApplication()));
        }
    }

    protected abstract void onInjectInstance(App application);

    protected abstract void onComponentDestroying(App application);

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (getActivity() == null) return;
        if (getActivity().isFinishing()) {
            onComponentDestroying((App) getActivity().getApplication());
            return;
        }
        boolean anyParentIsRemoving = false;

        Fragment parent = getParentFragment();
        while (!anyParentIsRemoving && parent != null) {
            anyParentIsRemoving = parent.isRemoving();
            parent = parent.getParentFragment();
        }

        if (isRemoving() || anyParentIsRemoving) {
            onComponentDestroying((App) getActivity().getApplication());
        }
    }


}
