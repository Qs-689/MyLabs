package com.example.lab3_interface_1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity {

    private ListView listView;
    private List<Map<String, Object>> dataList;
    private View lastSelectedView = null;

    private final String[] animalNames = {"Lion", "Tiger", "Monkey", "Dog", "Cat", "Elephant"};
    private final int[] animalIcons = {
            R.drawable.lion, R.drawable.tiger, R.drawable.monkey,
            R.drawable.dog, R.drawable.cat, R.drawable.elephant
    };
    private final String[] animalDescriptions = {
            "狮子是大型猫科动物，生活在非洲草原，是群居动物",
            "老虎是最大的猫科动物，有独特的条纹皮毛，主要分布在亚洲",
            "猴子是灵长类动物，聪明灵活，生活在森林中",
            "狗是人类最早驯化的动物，忠诚友好，有各种品种",
            "猫是受欢迎的宠物，独立优雅，有夜视能力",
            "大象是陆地上最大的哺乳动物，有长鼻子和大耳朵"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
        setupListView();
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

        listView.setOnItemClickListener((parent, view, position, id) -> {
            // 重置上次选中的背景
            if (lastSelectedView != null) {
                lastSelectedView.setBackgroundColor(Color.TRANSPARENT);
            }

            // 设置当前选中项背景为红色
            view.setBackgroundColor(Color.RED);
            lastSelectedView = view;

            String animalName = animalNames[position];
            Toast.makeText(MainActivity.this, "选中了: " + animalName, Toast.LENGTH_SHORT).show();

            // 显示弹窗通知
            showAnimalDialog(animalName, position);
        });
    }

    private void showAnimalDialog(String animalName, int position) {
        // 创建弹窗
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(animalName);
        builder.setMessage(animalDescriptions[position]);

        // 设置图标（使用动物图标）
        builder.setIcon(animalIcons[position]);

        // 添加确定按钮
        builder.setPositiveButton("确定", (dialog, which) -> dialog.dismiss());

        // 可选：添加更多按钮
        builder.setNegativeButton("了解更多", (dialog, which) -> {
            // 这里可以添加跳转到详情页的逻辑
            Toast.makeText(MainActivity.this, "跳转到" + animalName + "详情页", Toast.LENGTH_SHORT).show();
        });

        // 创建并显示弹窗
        AlertDialog dialog = builder.create();
        dialog.show();


    }
}