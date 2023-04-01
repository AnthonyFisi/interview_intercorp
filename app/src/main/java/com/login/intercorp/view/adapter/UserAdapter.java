package com.login.intercorp.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.login.intercorp.R;
import com.login.intercorp.model.UserModel;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private List<UserModel> listData;

    public UserAdapter(List<UserModel> listData){
        this.listData = listData;
    }

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_user,parent,false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {
        final UserModel userModel = listData.get(position);
        holder.name.setText(userModel.getName());
        holder.lastName.setText(userModel.getLastName());
        holder.birthdate.setText(userModel.getBirthdate());

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView name;
        public TextView lastName;
        public TextView birthdate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.name = itemView.findViewById(R.id.name);
            this.lastName = itemView.findViewById(R.id.lastName);
            this.birthdate = itemView.findViewById(R.id.birthdate);
        }
    }
}
