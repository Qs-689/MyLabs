package com.example.lab3_interface_4;


import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private CustomAdapter adapter;
    private ActionMode actionMode;
    private List<String> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初始化数据
        initializeData();

        // 初始化ListView和适配器
        listView = findViewById(R.id.listView);
        adapter = new CustomAdapter(this, dataList);
        listView.setAdapter(adapter);

        // 设置多选模式监听器
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                // 当选择状态改变时调用
                adapter.toggleSelection(position);
                updateActionModeTitle(mode);
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                // 创建ActionMode
                mode.getMenuInflater().inflate(R.menu.context_menu, menu);
                actionMode = mode;
                adapter.setActionMode(true);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                // 处理菜单项点击
                int id = item.getItemId();

                if (id == R.id.menu_delete) {
                    deleteSelectedItems();
                    mode.finish();
                    return true;
                } else if (id == R.id.menu_share) {
                    shareSelectedItems();
                    return true;
                } else if (id == R.id.menu_select_all) {
                    selectAllItems();
                    updateActionModeTitle(mode);
                    return true;
                }

                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                // ActionMode销毁时调用
                adapter.setActionMode(false);
                adapter.clearSelection();
                actionMode = null;
            }
        });

        // 设置列表项点击监听
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (actionMode != null) {
                    // 在ActionMode中，点击即选择/取消选择
                    listView.setItemChecked(position, !listView.isItemChecked(position));
                } else {
                    // 普通模式下显示项目内容
                    Toast.makeText(MainActivity.this,
                            "点击了: " + dataList.get(position), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initializeData() {
        dataList = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            dataList.add("列表项 " + i);
        }
    }

    private void updateActionModeTitle(ActionMode mode) {
        int selectedCount = adapter.getSelectedCount();
        if (selectedCount > 0) {
            mode.setTitle(selectedCount + " selected");
        } else {
            mode.setTitle("请选择项目");
        }
    }

    private void deleteSelectedItems() {
        List<Integer> selectedPositions = adapter.getSelectedPositions();

        // 从大到小删除，避免索引变化问题
        for (int i = selectedPositions.size() - 1; i >= 0; i--) {
            int position = selectedPositions.get(i);
            dataList.remove(position);
        }

        adapter.notifyDataSetChanged();
        Toast.makeText(this, "删除了 " + selectedPositions.size() + " 个项目", Toast.LENGTH_SHORT).show();
    }

    private void shareSelectedItems() {
        List<Integer> selectedPositions = adapter.getSelectedPositions();
        StringBuilder shareText = new StringBuilder("分享内容:\n");

        for (int position : selectedPositions) {
            shareText.append(dataList.get(position)).append("\n");
        }

        Toast.makeText(this, shareText.toString(), Toast.LENGTH_LONG).show();
    }

    private void selectAllItems() {
        for (int i = 0; i < dataList.size(); i++) {
            listView.setItemChecked(i, true);
        }
    }
}