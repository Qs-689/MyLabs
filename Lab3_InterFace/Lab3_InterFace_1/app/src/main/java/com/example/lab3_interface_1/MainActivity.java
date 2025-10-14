package com.example.lab3_interface_1;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import androidx.core.app.NotificationCompat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity {

    private ListView listView;
    private List<Map<String, Object>> dataList;

    // 添加final关键字
    private final String[] animalNames = {"Lion", "Tiger", "Monkey", "Dog", "Cat", "Elephant"};
    private final int[] animalIcons = {
            R.drawable.lion, R.drawable.tiger, R.drawable.monkey,
            R.drawable.dog, R.drawable.cat, R.drawable.elephant
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
        setupListView();
        createNotificationChannel();
    }

    private void initView() {
        listView = findViewById(R.id.animal_listview);
    }

    private void initData() {
        dataList = new ArrayList<>();

        for (int i = 0; i < animalNames.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", animalNames[i]);
            map.put("icon", animalIcons[i]);
            dataList.add(map);
        }
    }

    private void setupListView() {
        SimpleAdapter adapter = new SimpleAdapter(
                this,
                dataList,
                R.layout.item_list,
                new String[]{"name", "icon"},
                new int[]{R.id.animal_name, R.id.animal_icon}
        );

        listView.setAdapter(adapter);

        // 使用lambda表达式替换匿名内部类
        listView.setOnItemClickListener((parent, view, position, id) -> {
            String animalName = animalNames[position];
            Toast.makeText(MainActivity.this, "选中了: " + animalName, Toast.LENGTH_SHORT).show();
            sendNotification(animalName);
        });
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    "animal_channel",
                    "动物列表通知",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }

    private void sendNotification(String animalName) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "animal_channel")
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle(animalName)
                .setContentText("您选择了" + animalName + "，这是相关的详细信息")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify((int) System.currentTimeMillis(), builder.build());
    }
}