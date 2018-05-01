package me.ajax.chatlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    MessageAdapter messageAdapter;

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


        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messageAdapter.add("你好你好你好你好你好你好你好你好你好你好你好");
            }
        });
    }
}
