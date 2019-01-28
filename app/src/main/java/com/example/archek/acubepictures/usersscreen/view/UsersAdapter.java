package com.example.archek.acubepictures.usersscreen.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.archek.acubepictures.R;
import com.example.archek.acubepictures.utils.pojos.User;

import java.util.ArrayList;
import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {

    private Callback callback;
    private final List<User> users = new ArrayList<>();//main list for results to adapter

    public UsersAdapter(Callback callback) {
        this.callback = callback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_user, parent, false);
        final ViewHolder holder = new ViewHolder(itemView);
        //set on click listener(launch 2nd screen)
        itemView.setOnClickListener(v -> {
            User user = users.get(holder.getAdapterPosition());
            callback.onUserClick(user);
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = users.get(position);
        holder.tvName.setText(user.getName());
    }

    @Override
    public int getItemCount() {
        return (users == null) ? 0 : users.size();
    }

    public void replaceAll(List<User> usersToReplace) {//replace all users in main list
        users.clear();
        users.addAll(usersToReplace);
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvName;

        private ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
        }
    }
    public interface Callback{
        void onUserClick(User user);
    }

}
