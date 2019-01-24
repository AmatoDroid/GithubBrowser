package jp.rei.andou.githubbrowser.presentation.adapters;

import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import jp.rei.andou.githubbrowser.data.entities.NetworkState;
import jp.rei.andou.githubbrowser.data.entities.Repo;
import jp.rei.andou.githubbrowser.databinding.NetworkFailureItemBinding;
import jp.rei.andou.githubbrowser.databinding.RepositoryItemBinding;
import jp.rei.andou.githubbrowser.presentation.browser.RetryCallback;


//todo refactor to mv* approach
public class RepositoriesAdapter extends PagedListAdapter<Repo, RecyclerView.ViewHolder> {

    private static final int TYPE_PROGRESS = 0;
    private static final int TYPE_ITEM = 1;
    private NetworkState networkState;
    private RetryCallback retryCallback;


    public RepositoriesAdapter() {
        super(Repo.DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        if(viewType == TYPE_PROGRESS) {
            NetworkFailureItemBinding headerBinding = NetworkFailureItemBinding.inflate(layoutInflater, parent, false);
            return new NetworkStateItemViewHolder(headerBinding);

        } else {
            RepositoryItemBinding itemBinding = RepositoryItemBinding.inflate(layoutInflater, parent, false);
            return new RepoItemViewHolder(itemBinding);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof RepoItemViewHolder) {
            ((RepoItemViewHolder)holder).bindTo(getItem(position));
        } else {
            ((NetworkStateItemViewHolder) holder).bindTo(networkState);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (hasExtraRow() && position == getItemCount() - 1) {
            return TYPE_PROGRESS;
        } else {
            return TYPE_ITEM;
        }
    }


    private boolean hasExtraRow() {
        return networkState != null && networkState != NetworkState.SUCCESS;
    }

    public void setNetworkState(NetworkState newNetworkState) {
        NetworkState previousState = this.networkState;
        boolean previousExtraRow = hasExtraRow();
        this.networkState = newNetworkState;
        boolean newExtraRow = hasExtraRow();
        if (previousExtraRow != newExtraRow) {
            if (previousExtraRow) {
                notifyItemRemoved(getItemCount());
            } else {
                notifyItemInserted(getItemCount());
            }
        } else if (newExtraRow && previousState != newNetworkState) {
            notifyItemChanged(getItemCount() - 1);
        }
    }

    public void registerRetryCallback(RetryCallback callback) {
        this.retryCallback = callback;
    }

    public void unregisterRetryCallback() {
        this.retryCallback = null;
    }


    public class RepoItemViewHolder extends RecyclerView.ViewHolder {

        private RepositoryItemBinding binding;

        public RepoItemViewHolder(RepositoryItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindTo(Repo repo) {
           binding.setRepository(repo);
        }
    }

    public class NetworkStateItemViewHolder extends RecyclerView.ViewHolder {

        private NetworkFailureItemBinding binding;
        public NetworkStateItemViewHolder(NetworkFailureItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.binding.setRetryHandler(retryCallback);
        }

        public void bindTo(NetworkState networkState) {
            binding.setNetworkState(networkState);
        }
    }
}

