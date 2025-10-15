package com.example.lab3_interface_2;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnShowDialog;
    private AlertDialog loginDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初始化按钮
        btnShowDialog = findViewById(R.id.btnShowDialog);

        // 设置按钮点击事件
        btnShowDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomLoginDialog();
            }
        });
    }

    private void showCustomLoginDialog() {
        // 创建AlertDialog构建器
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // 获取自定义布局
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_login, null);

        // 设置自定义视图
        builder.setView(dialogView);

        // 获取布局中的控件
        final EditText etUsername = dialogView.findViewById(R.id.etUsername);
        final EditText etPassword = dialogView.findViewById(R.id.etPassword);
        Button btnCancel = dialogView.findViewById(R.id.btnCancel);
        Button btnSignIn = dialogView.findViewById(R.id.btnSignIn);

        // 创建对话框
        loginDialog = builder.create();

        // 设置Cancel按钮点击事件
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 关闭对话框，回到最初界面
                loginDialog.dismiss();
            }
        });

        // 设置Sign in按钮点击事件
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "请输入账号和密码", Toast.LENGTH_SHORT).show();
                } else {
                    // 登录成功处理
                    Toast.makeText(MainActivity.this, "登录成功！账号：" + username, Toast.LENGTH_SHORT).show();
                    loginDialog.dismiss();
                }
            }
        });

        // 设置对话框外部点击不关闭（可选）
        loginDialog.setCancelable(false);

        // 显示对话框
        loginDialog.show();

        // 自动弹出键盘（可选）
        etUsername.post(new Runnable() {
            @Override
            public void run() {
                etUsername.requestFocus();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 避免窗口泄漏
        if (loginDialog != null && loginDialog.isShowing()) {
            loginDialog.dismiss();
        }
    }
}