package com.example.securechat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.securechat.Cryptography.AES;
import com.example.securechat.messagemodel.ChatAdapter;
import com.example.securechat.messagemodel.messageModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;

public class ChatActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Toolbar toolbar=findViewById(R.id.toolbar);
        AES aes=new AES();
        firebaseDatabase=FirebaseDatabase.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
        final String senderId=firebaseAuth.getUid();
        String receiverId=getIntent().getStringExtra("userId");
        TextView textView=findViewById(R.id.chatterName);
        textView.setText(getIntent().getStringExtra("username"));
        setSupportActionBar(toolbar);

        RecyclerView recyclerView=findViewById(R.id.chat_screen_Recycler_view);

        final ArrayList<messageModel> messageModels=new ArrayList<>();

        ChatAdapter chatAdapter=new ChatAdapter(messageModels,this);
        recyclerView.setAdapter(chatAdapter);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        EditText message=findViewById(R.id.message);
       String senderchatarea=senderId+receiverId;
       String revieverchatArea=receiverId+senderId;

       firebaseDatabase.getReference().child("chats")
               .child(senderchatarea).addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
               messageModels.clear();
               for(DataSnapshot dataSnapshot:snapshot.getChildren())
               {
                  messageModel messageModel=dataSnapshot.getValue(messageModel.class);

                  messageModels.add(messageModel);
               }
               chatAdapter.notifyDataSetChanged();

           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       });
        ImageButton imageButton=findViewById(R.id.sendButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Textmessage=message.getText().toString();

                message.setText("");
                String encryptedMessage = null;

                try {
                    encryptedMessage = aes.encrypt(Textmessage);
                } catch (Exception e) {
//            Logger.getLogger(AESCrypt.class.getName()).log(Level.SEVERE, null, e);
                    e.printStackTrace();
                }
                messageModel messageModel=new messageModel(senderId,encryptedMessage);
                messageModel.setTimeStamp(new Date().getTime());
                firebaseDatabase.getReference().child("chats")
                        .child(senderchatarea).push().setValue(messageModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        firebaseDatabase.getReference().child("chats").child(revieverchatArea).push()
                                .setValue(messageModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                            }
                        });
                    }
                });
            }
        });

    }
}