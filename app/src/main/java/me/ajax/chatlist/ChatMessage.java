package me.ajax.chatlist;

/**
 * Created by aj on 2018/5/1
 */

public class ChatMessage {

    private boolean isFrom;
    private String messageContent;

    public ChatMessage(boolean isFrom, String messageContent) {
        this.isFrom = isFrom;
        this.messageContent = messageContent;
    }

    public boolean isFrom() {
        return isFrom;
    }

    public void setFrom(boolean from) {
        isFrom = from;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }
}
