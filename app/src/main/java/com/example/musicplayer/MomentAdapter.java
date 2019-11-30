package com.example.musicplayer;

import android.icu.text.LocaleDisplayNames;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Baldwin on 19/11/29.
 */

public class MomentAdapter extends RecyclerView.Adapter<MomentAdapter.ViewHolder> {
    private List<Moment> mMomentList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        View momentView;
        TextView describe,nickname;
        ImageView thumbnail,profilePhoto;

        public ViewHolder(View view){
            super(view);
            momentView = view;
            describe = (TextView) view.findViewById(R.id.describe);
            nickname = (TextView) view.findViewById(R.id.nickname);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            profilePhoto = (ImageView) view.findViewById(R.id.profilePhoto);
        }
    }

    public MomentAdapter(List<Moment> momentsList){
        this.mMomentList = momentsList;
    }

    @Override
    public int getItemCount() {
        return mMomentList.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Moment moment = mMomentList.get(position);
        holder.describe.setText(moment.getDescribe());
        holder.nickname.setText(moment.getNickname());

        //获取item高度，计算图片等比例缩放后的高度，为imageView设置参数
//        RelativeLayout.LayoutParams layoutParams =
//                (RelativeLayout.LayoutParams) holder.thumbnail.getLayoutParams();
//        float itemWidth = (ScreenUtils.getScreenWidth(holder.itemView.getContext()) - 5*3)/2;
//        layoutParams.width = (int) itemWidth;
//        float scale = (itemWidth+0f)/holder.thumbnail.;


        holder.thumbnail.setImageResource(moment.getThumbnailId());
        holder.profilePhoto.setImageResource(moment.getProfilePhotoId());

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.moment_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }
}
