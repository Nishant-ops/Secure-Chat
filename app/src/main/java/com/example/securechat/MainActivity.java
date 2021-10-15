package com.example.securechat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

//import com.example.securechat.message.MessageActivity;
import com.example.securechat.Adapter.FragmentAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mfirebaseauth;
   private FragmentAdapter fragmentAdapter;

    TabLayout tabLayout;
ViewPager2 viewPager2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        mfirebaseauth = FirebaseAuth.getInstance();
        tabLayout = findViewById(R.id.tab);
        tabLayout.addTab(tabLayout.newTab().setText("Chats"), 0);
        tabLayout.addTab(tabLayout.newTab().setText("Status"), 1);
        tabLayout.addTab(tabLayout.newTab().setText("Calls"), 2);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        viewPager2 = findViewById(R.id.viewPager);
        List<String> names=new ArrayList<>();
        names.add("Chats");
        names.add("Status");
        names.add("Call");
        viewPager2.setAdapter(new FragmentAdapter(getSupportFragmentManager(), getLifecycle()));
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager2,  new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(names.get(position));
            }
        });
        tabLayoutMediator.attach();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.settings:
                break;

            case R.id.Logout:
                mfirebaseauth.signOut();
                break;
        }
        return true;
    }
}