package me.ajax.chatlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    MessageAdapter messageAdapter;

    ArrayList<ChatMessage> messages = new ArrayList<>();
    int messageIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(messageAdapter = new MessageAdapter());

        /*
         * 既然是动画，就会有时间，我们把动画执行时间变大一点来看一看效果
         */
        ChatItemAnimator chatItemAnimator = new ChatItemAnimator();
        chatItemAnimator.setAddDuration(1000);
        chatItemAnimator.setRemoveDuration(1000);
        recyclerView.setItemAnimator(chatItemAnimator);


        messages.add(new ChatMessage(true, "你好"));
        messages.add(new ChatMessage(true, "我是谁？"));
        messages.add(new ChatMessage(true, "我这是在哪儿？"));
        messages.add(new ChatMessage(true, "啥时候吃饭？"));


        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (messageIndex == messages.size()) return;
                messageAdapter.add(messages.get(messageIndex++));
            }
        });
    }
}
