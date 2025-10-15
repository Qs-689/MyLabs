package com.example.lab3_interface_3;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tvTestContent;
    private TextView tvCurrentSettings;

    // 当前字体大小和颜色设置
    private int currentFontSize = 16; // 默认中号字
    private int currentTextColor = Color.BLACK;
    private String currentSizeText = "中(16号)";
    private String currentColorText = "黑色";

    // 定义颜色常量
    private static final int COLOR_PURPLE = Color.parseColor("#800080");
    private static final int COLOR_ORANGE = Color.parseColor("#FFA500");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初始化视图
        tvTestContent = findViewById(R.id.tvTestContent);
        tvCurrentSettings = findViewById(R.id.tvCurrentSettings);

        // 设置初始文本样式
        updateTextAppearance();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 加载菜单资源
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // 根据当前设置更新菜单项的选中状态
        MenuItem smallFont = menu.findItem(R.id.menu_font_small);
        MenuItem mediumFont = menu.findItem(R.id.menu_font_medium);
        MenuItem largeFont = menu.findItem(R.id.menu_font_large);
        MenuItem redColor = menu.findItem(R.id.menu_color_red);
        MenuItem blackColor = menu.findItem(R.id.menu_color_black);
        MenuItem blueColor = menu.findItem(R.id.menu_color_blue);
        MenuItem greenColor = menu.findItem(R.id.menu_color_green);
        MenuItem purpleColor = menu.findItem(R.id.menu_color_purple);
        MenuItem orangeColor = menu.findItem(R.id.menu_color_orange);

        // 清除所有字体大小选项的选中状态
        smallFont.setChecked(false);
        mediumFont.setChecked(false);
        largeFont.setChecked(false);

        // 根据当前字体大小设置选中状态
        switch (currentFontSize) {
            case 10:
                smallFont.setChecked(true);
                break;
            case 16:
                mediumFont.setChecked(true);
                break;
            case 20:
                largeFont.setChecked(true);
                break;
        }

        // 清除所有颜色选项的选中状态
        redColor.setChecked(false);
        blackColor.setChecked(false);
        blueColor.setChecked(false);
        greenColor.setChecked(false);
        purpleColor.setChecked(false);
        orangeColor.setChecked(false);

        // 使用预定义的常量进行switch判断
        if (currentTextColor == Color.RED) {
            redColor.setChecked(true);
        } else if (currentTextColor == Color.BLACK) {
            blackColor.setChecked(true);
        } else if (currentTextColor == Color.BLUE) {
            blueColor.setChecked(true);
        } else if (currentTextColor == Color.GREEN) {
            greenColor.setChecked(true);
        } else if (currentTextColor == Color.parseColor("#800080")) {
            purpleColor.setChecked(true);
        } else if (currentTextColor == Color.parseColor("#FFA500")) {
            orangeColor.setChecked(true);
        }

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.menu_normal) {
            // 普通菜单项 - 显示Toast
            showToast("您点击了普通菜单项");
            return true;
        } else if (itemId == R.id.menu_font_small) {
            // 小号字体
            currentFontSize = 10;
            currentSizeText = "小(10号)";
            updateTextAppearance();
            showToast("字体大小已设置为小号");
            return true;
        } else if (itemId == R.id.menu_font_medium) {
            // 中号字体
            currentFontSize = 16;
            currentSizeText = "中(16号)";
            updateTextAppearance();
            showToast("字体大小已设置为中号");
            return true;
        } else if (itemId == R.id.menu_font_large) {
            // 大号字体
            currentFontSize = 20;
            currentSizeText = "大(20号)";
            updateTextAppearance();
            showToast("字体大小已设置为大号");
            return true;
        } else if (itemId == R.id.menu_color_red) {
            // 红色字体
            currentTextColor = Color.RED;
            currentColorText = "红色";
            updateTextAppearance();
            showToast("字体颜色已设置为红色");
            return true;
        } else if (itemId == R.id.menu_color_black) {
            // 黑色字体
            currentTextColor = Color.BLACK;
            currentColorText = "黑色";
            updateTextAppearance();
            showToast("字体颜色已设置为黑色");
            return true;
        } else if (itemId == R.id.menu_color_blue) {
            // 蓝色字体
            currentTextColor = Color.BLUE;
            currentColorText = "蓝色";
            updateTextAppearance();
            showToast("字体颜色已设置为蓝色");
            return true;
        } else if (itemId == R.id.menu_color_green) {
            // 绿色字体
            currentTextColor = Color.GREEN;
            currentColorText = "绿色";
            updateTextAppearance();
            showToast("字体颜色已设置为绿色");
            return true;
        } else if (itemId == R.id.menu_color_purple) {
            // 紫色字体
            currentTextColor = COLOR_PURPLE;
            currentColorText = "紫色";
            updateTextAppearance();
            showToast("字体颜色已设置为紫色");
            return true;
        } else if (itemId == R.id.menu_color_orange) {
            // 橙色字体
            currentTextColor = COLOR_ORANGE;
            currentColorText = "橙色";
            updateTextAppearance();
            showToast("字体颜色已设置为橙色");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * 更新文本外观
     */
    private void updateTextAppearance() {
        // 设置测试文本的字体大小和颜色
        tvTestContent.setTextSize(currentFontSize);
        tvTestContent.setTextColor(currentTextColor);

        // 更新当前设置显示
        tvCurrentSettings.setText(String.format("当前设置：字体大小-%s，颜色-%s",
                currentSizeText, currentColorText));
    }

    /**
     * 显示Toast消息
     */
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}