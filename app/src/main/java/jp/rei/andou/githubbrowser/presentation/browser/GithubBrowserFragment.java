package jp.rei.andou.githubbrowser.presentation.browser;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
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
import jp.rei.andou.githubbrowser.presentation.adapters.RepositoriesAdapter;
import jp.rei.andou.githubbrowser.presentation.common.ConfigurableFragment;
import jp.rei.andou.githubbrowser.presentation.general.GeneralNavigator;

public class GithubBrowserFragment extends ConfigurableFragment {

    @Inject
    GeneralNavigator generalNavigator;
    @Inject
    BrowserViewModel browserViewModel;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private PublishRelay<String> searchQuerySubject;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        this.searchQuerySubject = browserViewModel.getSearchQuerySubject();
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
            ((AppCompatActivity) getActivity()).setSupportActionBar(binding.toolbar);
        }
        binding.repositories.setLayoutManager(new LinearLayoutManager(getContext()));
        RepositoriesAdapter adapter = new RepositoriesAdapter();
        browserViewModel.getPagedListLiveData().observe(this, adapter::submitList);
        binding.repositories.setAdapter(adapter);
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
        inflater.inflate(R.menu.browser_menu, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String newText) {
                if (searchQuerySubject != null) {
                    searchQuerySubject.accept(newText);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                if (searchQuerySubject != null) {
                    searchQuerySubject.accept(query);
                }
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }
}
