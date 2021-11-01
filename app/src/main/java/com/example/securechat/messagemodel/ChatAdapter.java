package com.example.securechat.messagemodel;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import com.example.securechat.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;



public class ChatAdapter extends RecyclerView.Adapter{

    ArrayList<messageModel> messageModelArrayList;
    Context context;

    int SENDER_VIEW_TYPE=1;
    int RECEIVER_VIEW_TYPE=2;


    public ChatAdapter(ArrayList<messageModel> messageModelArrayList, Context context) {
        this.messageModelArrayList = messageModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==SENDER_VIEW_TYPE)
        {
            View view= LayoutInflater.from(context).inflate(R.layout.sample_sender,parent,false);
            return new SenderViewHolder(view);
        }
        else{
            View view= LayoutInflater.from(context).inflate(R.layout.sample_reciver,parent,false);
            return new RecieverViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(messageModelArrayList.get(position).getUid().equals(FirebaseAuth.getInstance().getUid()))
        {
            return SENDER_VIEW_TYPE;
        }
        else{
            return RECEIVER_VIEW_TYPE;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        messageModel messageModel=messageModelArrayList.get(position);
        if(holder.getClass()==SenderViewHolder.class)
        {
            ((SenderViewHolder)holder).senderMessage.setText(messageModel.message);
            //((SenderViewHolder)holder).senderTime.setText(Math.toIntExact(messageModel.timeStamp));
        }
        else{
            ((RecieverViewHolder)holder).recieverMessage.setText(messageModel.message);
          //  ((RecieverViewHolder)holder).recieverTime.setText(Math.toIntExact(messageModel.timeStamp));
        }
    }

    @Override
    public int getItemCount() {
        return messageModelArrayList.size();
    }

    public class RecieverViewHolder extends RecyclerView.ViewHolder
    {

        TextView recieverMessage,recieverTime;
        public RecieverViewHolder(@NonNull View itemView) {
            super(itemView);
            recieverMessage=itemView.findViewById(R.id.ReciveText);
            recieverTime=itemView.findViewById(R.id.reciverTime);

        }
    }

    public class SenderViewHolder extends RecyclerView.ViewHolder
    {
         TextView senderMessage,senderTime;
        public SenderViewHolder(@NonNull View itemView) {
            super(itemView);
            senderMessage=itemView.findViewById(R.id.sender_text);
            senderTime=itemView.findViewById(R.id.sender_time);
        }
    }
}
