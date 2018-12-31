package com.example.asusrog.mobilecodingchallenge;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
    private Context mContext;
    private List<Repos> mRepos;

    //Constructor
    public ImageAdapter(Context context, List<Repos> repos) {
        mContext = context;
        mRepos = repos;
    }

    @Override
    public ImageAdapter.ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflate the view into the item layout
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_layout, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ImageAdapter.ImageViewHolder holder, int position) {
        // Fill the layout with content
        Repos uploadCurrent = mRepos.get(position);
        holder.ReposName.setText(uploadCurrent.getRepos_name());
        holder.ReposDesc.setText(uploadCurrent.getRepos_desc());
        holder.NbStars.setText(uploadCurrent.getNbStars());
        holder.Username.setText(uploadCurrent.getUsername());
        Picasso.get()
                .load(uploadCurrent.getImageUrl())
                .placeholder(R.mipmap.ic_launcher)
                .fit()

                .into(holder.useravatar);
    }


    @Override
    public int getItemCount() {
        return mRepos.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        public TextView ReposName,Username,ReposDesc;
        public ImageView useravatar;
        public TextView NbStars;

        public ImageViewHolder(View itemView) {
            super(itemView);

            // Declaration of textviews and imageview

            ReposName = itemView.findViewById(R.id.Repository_name);
            ReposDesc = itemView.findViewById(R.id.Repository_description);
            useravatar = itemView.findViewById(R.id.user_avatar);
            Username = itemView.findViewById(R.id.user_name);
            NbStars = itemView.findViewById(R.id.number_Ofstars);


        }
    }
}




