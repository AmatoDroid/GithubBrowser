package jp.rei.andou.githubbrowser.presentation.browser;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jakewharton.rxrelay2.PublishRelay;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import jp.rei.andou.githubbrowser.App;
import jp.rei.andou.githubbrowser.R;
import jp.rei.andou.githubbrowser.databinding.BrowserBinding;
import jp.rei.andou.githubbrowser.presentation.common.ConfigurableFragment;
import jp.rei.andou.githubbrowser.presentation.general.GeneralNavigator;

public class GithubBrowserFragment extends ConfigurableFragment {

    @Inject
    GeneralNavigator generalNavigator;
    @Inject
    BrowserViewModel browserViewModel;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //todo 2: design layouts and set navigators to databindings layouts
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        BrowserBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.browser, container, false
        );
        binding.setLifecycleOwner(this);
        if (getActivity() != null) {
            getActivity().setActionBar(binding.toolbar);
        }
        return binding.getRoot();
    }

    @Override
    protected void onInjectInstance(App application) {
        application.inject(this);
    }

    @Override
    protected void onComponentDestroying(App application) {
        compositeDisposable.dispose();
        application.destroy(this);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.browser_menu, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        PublishRelay<String> searchQuerySubject = browserViewModel.getSearchQuerySubject();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String newText) {
                searchQuerySubject.accept(newText);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                searchQuerySubject.accept(query);
                return false;
            }
        });
    }
}
