package com.example.securechat.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.adapter.FragmentViewHolder;

import com.example.securechat.fragements.CallFragment;
import com.example.securechat.fragements.ChatFragment;
import com.example.securechat.fragements.StatusFragment;

public class FragmentAdapter extends FragmentStateAdapter {
    public FragmentAdapter(FragmentManager fragment,Lifecycle lifecycle){
    super(fragment,lifecycle);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch(position)
        {
            case 0:return new ChatFragment();
            case 1:return new StatusFragment();
            case 2:return new CallFragment();
            default:return new ChatFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public String setTitle(int position)
    {
        String ans="";
        if(position==0)
            ans+="CHATS";
        if(position==1)
            ans+="STATUS";
        if(position==2)
            ans+="CALLS";

        return ans;

    }

}
