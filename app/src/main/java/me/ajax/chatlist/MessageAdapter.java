package me.ajax.chatlist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by aj on 2018/4/23
 */
public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {

    private ArrayList<ChatMessage> messages = new ArrayList<>();

    MessageAdapter() {
        messages.add(new ChatMessage(true, "我们已经是好友了"));
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_chat, parent, false);
        return new MessageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MessageViewHolder holder, int position) {
        holder.setMessage(messages.get(position), position);
    }

    public void add(ChatMessage message) {
        messages.add(message);
        notifyItemInserted(messages.size() - 1);//注意这里
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }


    class MessageViewHolder extends RecyclerView.ViewHolder {
        private TextView messageText;
        private ImageView avatarView;

        MessageViewHolder(View itemView) {
            super(itemView);
            messageText = (TextView) itemView.findViewById(R.id.message_text);
            avatarView = (ImageView) itemView.findViewById(R.id.avatar);
        }

        void setMessage(ChatMessage message, int position) {

            boolean showAvatar = true;
            if (position - 1 >= 0) {
                showAvatar = message.isFrom() != messages.get(position - 1).isFrom();
            }
            avatarView.setVisibility(showAvatar ? View.VISIBLE : View.INVISIBLE);
            messageText.setText(message.getMessageContent());
        }

    }
}

