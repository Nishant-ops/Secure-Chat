package com.example.securechat.users;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.securechat.ChatActivity;
import com.example.securechat.R;

import java.util.ArrayList;
import java.util.List;

public class Users_Adapter extends RecyclerView.Adapter<Users_Adapter.ViewHolder>{
ArrayList<Users> list;
Context context;

    public Users_Adapter(ArrayList<Users> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.show_user,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      Users users=list.get(position);
      holder.username.setText(users.getUsername());
      System.out.println(users.getUsername());
      System.out.println("email =>" + users.getEmail());
      holder.itemView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent intent=new Intent(context, ChatActivity.class);
              intent.putExtra("userId",users.getUid());
              intent.putExtra("username",users.getUsername());
              context.startActivity(intent);
          }
      });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {

        TextView username,LastMessage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username= itemView.findViewById(R.id.chat_name);
            LastMessage = itemView.findViewById(R.id.last_message);
        }


    }

}
