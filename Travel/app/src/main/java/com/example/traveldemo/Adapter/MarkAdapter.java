package com.example.traveldemo.Adapter;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.traveldemo.Entity.Order;
import com.example.traveldemo.Entity.User;
import com.example.traveldemo.R;
import com.example.traveldemo.ViewModel.UserViewModel;

import java.util.ArrayList;
import java.util.List;

public class MarkAdapter extends RecyclerView.Adapter<MarkAdapter.MarkViewHolder>{
    private List<Order>orderByPlan = new ArrayList<>();
    private FragmentActivity fragmentActivity;
    private LifecycleOwner lifecycleOwner;
    private UserViewModel userViewModel;
    private LiveData<List<User>>findUserByLogin;


    public void setOrderByPlan(List<Order> orderByPlan) {
        this.orderByPlan = orderByPlan;
    }

    public MarkAdapter(FragmentActivity fragmentActivity, LifecycleOwner lifecycleOwner) {
        this.fragmentActivity = fragmentActivity;
        this.lifecycleOwner = lifecycleOwner;
    }

    @NonNull
    @Override
    public MarkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.cell_mark,parent,false);
        return new MarkAdapter.MarkViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MarkViewHolder holder, int position) {
        Order order = orderByPlan.get(position);
        holder.textView_mark.setText(order.getOrder_mark());

        userViewModel = ViewModelProviders.of(fragmentActivity).get(UserViewModel.class);
        findUserByLogin = userViewModel.findUserByLoginname(order.getUser_loginname());
        findUserByLogin.observe(lifecycleOwner, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                holder.textView_userlogin.setText(users.get(0).getUser_name());
                Glide.with(fragmentActivity)
                        .load(users.get(0).getImage())
                        .placeholder(R.drawable.user_image)
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                return false;
                            }
                        })
                        .into(holder.imageView);
            }
        });




    }

    @Override
    public int getItemCount() {
        return orderByPlan.size();
    }

    class MarkViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView_userlogin,textView_mark;
        public MarkViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView_mark_userimage);
            textView_mark = itemView.findViewById(R.id.textView_mark_mark);
            textView_userlogin = itemView.findViewById(R.id.textView_mark_username);
        }
    }
}
