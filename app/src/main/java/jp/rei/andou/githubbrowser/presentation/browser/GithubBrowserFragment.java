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
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.jakewharton.rxrelay2.PublishRelay;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import jp.rei.andou.githubbrowser.App;
import jp.rei.andou.githubbrowser.R;
import jp.rei.andou.githubbrowser.data.entities.NetworkState;
import jp.rei.andou.githubbrowser.databinding.BrowserBinding;
import jp.rei.andou.githubbrowser.presentation.adapters.RepositoriesAdapter;
import jp.rei.andou.githubbrowser.presentation.common.ConfigurableFragment;
import jp.rei.andou.githubbrowser.presentation.general.Navigation;
import jp.rei.andou.githubbrowser.presentation.main.MainActivity;

public class GithubBrowserFragment extends ConfigurableFragment {

    @Inject
    BrowserViewModel browserViewModel;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private PublishRelay<String> searchQuerySubject;
    private RepositoriesAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
        this.searchQuerySubject = browserViewModel.getSearchQuerySubject();
        browserViewModel.getNavigationEvents().observe(this, navigationSingleEvent -> {
            Navigation navigation = navigationSingleEvent.getContent();
            if (navigation == null) {
                return;
            }
            switch (navigation) {
                case WELCOME: {
                    ((MainActivity) getActivity()).routeToAuthorizationScreen();
                    break;
                }

                case SIGN_IN: {
                    ((MainActivity) getActivity()).routeToSignInScreen();
                    break;
                }
            }
        });
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
        adapter = new RepositoriesAdapter();
        browserViewModel.getPagedListLiveData().observe(this, adapter::submitList);
        binding.repositories.setHasFixedSize(true);
        binding.repositories.setAdapter(adapter);
        adapter.registerRetryCallback(() -> browserViewModel.retrySearch());
        browserViewModel.getNetworkStateLiveData().observe(this, state -> {
            if (state == null) return;
            adapter.setNetworkState(state);
            if (state.equals(NetworkState.INITIAL_LOADING)) {
                binding.loading.setVisibility(View.VISIBLE);
            } else {
                binding.loading.setVisibility(View.GONE);
            }
        });
        return binding.getRoot();
    }

    @Override
    protected void onInjectInstance(App application) {
        application.inject(this);
    }

    @Override
    protected void onComponentDestroying(App application) {
        compositeDisposable.dispose();
        adapter.unregisterRetryCallback();
        application.destroyBrowserComponent();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.browser_menu, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        MenuItem menuItem = menu.findItem(R.id.login_logout);
        if (browserViewModel.isUserSessionIsAlive()) {
            menuItem.setTitle(R.string.logout);
        } else {
            menuItem.setTitle(R.string.login);
        }
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch(id) {
            case R.id.login_logout: {
                browserViewModel.navigateUser();
                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }
}
