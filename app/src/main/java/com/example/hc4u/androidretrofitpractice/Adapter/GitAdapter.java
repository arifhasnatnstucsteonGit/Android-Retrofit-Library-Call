package com.example.hc4u.androidretrofitpractice.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hc4u.androidretrofitpractice.R;
import com.example.hc4u.androidretrofitpractice.models.GitModel;
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by HC4U on 10/27/2016.
 */

public class GitAdapter extends RecyclerView.Adapter<GitAdapter.GitViewHolder>{
    public ArrayList<GitModel> gitModelArrayList ;
    Context context;

    public GitAdapter(ArrayList<GitModel> gitModelArrayList) {
        this.gitModelArrayList = gitModelArrayList;
    }

    @Override
    public GitAdapter.GitViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.git_adapter, parent, false);
        GitViewHolder vh = new GitViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(GitAdapter.GitViewHolder holder, int position) {

        holder.textView.setText(gitModelArrayList.get(position).getName());
        Picasso.with(context)
                .load(gitModelArrayList.get(position).getAvatar_url())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .resize(800, 1000)
                .into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return gitModelArrayList.size();
    }

    public class GitViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public ImageView imageView;
        public GitViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);


        }
    }
}
