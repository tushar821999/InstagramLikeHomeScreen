package com.androidgits.mediaplayer.adapter;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidgits.mediaplayer.R;
import com.androidgits.mediaplayer.model.Post;
import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Lenovo on 5/11/2018.
 */

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder>{

    private int likeState = 0;
    private int saveState = 0;
    private Context mcontext;
    private List<Post> mData;

    public PostAdapter(Context mcontext, List<Post> mData) {
        this.mcontext = mcontext;
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater mInflater = LayoutInflater.from(mcontext);
        View view = mInflater.inflate(R.layout.post_row,parent,false);
        final MyViewHolder viewHolder = new MyViewHolder(view);

        viewHolder.SaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (saveState == 0){
                    saveState++;
                    viewHolder.SaveBtn.setImageResource(R.drawable.fillsave);
                    Toast.makeText(mcontext, "Post Saved Into Collection", Toast.LENGTH_SHORT).show();
                }
                else{
                    saveState--;
                    viewHolder.SaveBtn.setImageResource(R.drawable.save);
                    Toast.makeText(mcontext,"Post Removed From Collection",Toast.LENGTH_SHORT).show();
                }

            }
        });

        viewHolder.LikeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (likeState == 0){
                    likeState++;
                    viewHolder.LikeBtn.setImageResource(R.drawable.filllike);
                    Toast.makeText(mcontext, "You Like This Post", Toast.LENGTH_SHORT).show();
                }
                else {
                    likeState--;
                    viewHolder.LikeBtn.setImageResource(R.drawable.heart);
                    Toast.makeText(mcontext, "You DisLike This Post", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.UserName.setText(mData.get(position).getUsername());
        holder.Likes.setText(mData.get(position).getLikes());
        holder.Status.setText(mData.get(position).getStatus());
        holder.TimeStamp.setText(mData.get(position).getTimeStamp());

        Glide.with(mcontext).load(mData.get(position).getImage_url()).into(holder.ProfileImg);
        Glide.with(mcontext).load(mData.get(position).getImage_url()).into(holder.MainImg);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView MainImg;
        CircleImageView ProfileImg;
        TextView UserName,Likes,Status,TimeStamp;
        ImageView ThreeDots,LikeBtn,CommmentBtn,ShareBtn,SaveBtn;
        public MyViewHolder(View itemView) {
            super(itemView);
            ProfileImg = (CircleImageView) itemView.findViewById(R.id.profile_image);
            MainImg = (ImageView) itemView.findViewById(R.id.profile);
            UserName = (TextView) itemView.findViewById(R.id.username);
            Likes = (TextView) itemView.findViewById(R.id.likes);
            Status = (TextView) itemView.findViewById(R.id.status);
            TimeStamp = (TextView) itemView.findViewById(R.id.timestamp);
            ThreeDots = (ImageView) itemView.findViewById(R.id.threeDots);
            LikeBtn = (ImageView) itemView.findViewById(R.id.like);
            CommmentBtn = (ImageView) itemView.findViewById(R.id.comment);
            ShareBtn = (ImageView) itemView.findViewById(R.id.share);
            SaveBtn = (ImageView) itemView.findViewById(R.id.save);
        }
    }
}
