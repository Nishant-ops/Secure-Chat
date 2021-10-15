package com.example.securechat.users;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.securechat.R;

import java.util.List;

public class Users_Adapter extends ArrayAdapter<Users> {
    private List<Users> mList;
    public Users_Adapter(Context context, List<Users>list)
    {
        super(context,0,list);
        mList=list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView==null)
        {
           convertView= LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

        Users users=mList.get(position);

        TextView Username=(TextView) convertView.findViewById(R.id.username_list_view);
        String username=users.getUsername();
        Username.setText(username);
        return convertView;
    }
}
