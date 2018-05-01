package me.ajax.chatlist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by aj on 2018/4/23
 */
public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {

    private ArrayList<String> myData = new ArrayList<>();

    MessageAdapter() {
        myData.add("我们已经是好友了");
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_chat, parent, false);
        return new MessageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MessageViewHolder holder, int position) {
        holder.setMessageText(myData.get(position));
    }

    public void add(String str) {
        myData.add(str);
        notifyItemInserted(myData.size() - 1);//注意这里
    }

    @Override
    public int getItemCount() {
        return myData.size();
    }


    static class MessageViewHolder extends RecyclerView.ViewHolder {
        private TextView messageText;

        MessageViewHolder(View itemView) {
            super(itemView);
            messageText = (TextView) itemView.findViewById(R.id.message_text);
        }

        public void setMessageText(String text) {
            messageText.setText(text);
        }

    }
}

