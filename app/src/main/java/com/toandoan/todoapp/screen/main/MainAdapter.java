package com.toandoan.todoapp.screen.main;

import android.arch.paging.PagedListAdapter;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.DiffCallback;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.toandoan.todoapp.BR;
import com.toandoan.todoapp.R;
import com.toandoan.todoapp.data.model.Repo;
import com.toandoan.todoapp.databinding.ItemRepositoryBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by doan.van.toan on 12/26/17.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    private List<Repo> mRepos;
    private LayoutInflater mInflater;
    private RepoItemClickListener mRepoItemClickListener;

    protected MainAdapter() {
        mRepos = new ArrayList<>();
    }

    public void setRepoItemClickListener(RepoItemClickListener repoItemClickListener) {
        mRepoItemClickListener = repoItemClickListener;
    }

    public void addData(List<Repo> repos) {
        if (repos == null) {
            return;
        }
        mRepos.addAll(repos);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mInflater == null) {
            mInflater = LayoutInflater.from(parent.getContext());
        }
        ItemRepositoryBinding binding = DataBindingUtil.inflate(mInflater,
                R.layout.item_repository, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData(mRepos.get(position));
    }

    @Override
    public int getItemCount() {
        return mRepos != null ? mRepos.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemRepositoryBinding mBinding;

        public ViewHolder(ItemRepositoryBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bindData(Repo repo) {
            mBinding.setRepo(repo);
            mBinding.setPosition(String.valueOf(getAdapterPosition() + 1));
            mBinding.setListener(mRepoItemClickListener);
            mBinding.executePendingBindings();
        }
    }
}
