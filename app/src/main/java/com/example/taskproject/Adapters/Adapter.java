package com.example.taskproject.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskproject.Modals.Category;
import com.example.taskproject.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Category> mCategoryList;

    public Adapter(Context mContext,ArrayList<Category> mCategoryList){
        this.mContext = mContext;
        this.mCategoryList = mCategoryList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.category_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Category currentCategory = mCategoryList.get(position);

        //String gSlno = currentCategory.getmSlno();
        //String gCategory = currentCategory.getmCategory();
        //String gImage = currentCategory.getmImage();

        holder.mSl_tv.setText("Sl No: "+ mCategoryList.get(position).getmSlno());
        holder.mTv_category.setText("Category:"+ mCategoryList.get(position).getmCategory());
        Picasso.get().load(mCategoryList.get(position).getmImage()).into(holder.mImageView);


    }

    @Override
    public int getItemCount() {
        return mCategoryList.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView mSl_tv;
        public TextView mTv_category;
        public ImageView mImageView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mSl_tv = itemView.findViewById(R.id.sl_tv);
            mTv_category =itemView.findViewById(R.id.tv_category);
            mImageView =itemView.findViewById(R.id.iamgeview);
        }
    }
    public void addItems(ArrayList<Category> dataList){
        mCategoryList.addAll(dataList);
        notifyDataSetChanged();
    }
}
