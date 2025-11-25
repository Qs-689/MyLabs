# 实验三 Android界面组件实验
## 📖 实验任务
### 任务一：Android ListView的用法
- 利用SimpleAdapter实现列表界面效果

- 自定义列表项布局，使用指定图片资源

- 使用Toast显示选中的列表项信息

- 单击列表项后发送通知，通知图标为应用图标，标题为列表项内容

### 任务二：创建自定义布局的AlertDialog
- 创建自定义布局的对话框

- 调用AlertDialog.Builder的setView()方法将布局添加到AlertDialog

- 实现完整的对话框交互功能

### 任务三：使用XML定义菜单
- 使用XML资源文件定义选项菜单

- 字体大小选项（小/中/大，对应10/16/20号字）

- 普通菜单项点击弹出Toast提示

- 字体颜色选项

### 任务四：创建上下文操作模式(ActionMode)的上下文菜单
- 创建ActionMode形式的上下文菜单

- 使用ListView或ListActivity创建列表

- 为列表项创建多选操作功能

## 🛠️ 技术栈与工具
- **开发语言**: Java

- **开发工具**: Android Studio

- **目标AP**I: Android 8.0+ (API 26)

- **UI框架**: Material Design

- **版本控制**: Git

- **项目管理**: Gradle

## 📁 项目结构
Lab3_InterFace/  
├── Lab3_InterFace_1/  
├── Lab3_InterFace_2/    
├── Lab3_InterFace_3/  
├── Lab3_InterFace_4/  

## 🔧 详细实现说明
### 任务一：ListView的实现

```Java
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
```
#### 项目运行
![image](https://github.com/Qs-689/MyLabs/blob/master/Lab3_InterFace/lab3_img/%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE%202025-11-18%20151955.png)

#### 点击列表

![image](https://github.com/Qs-689/MyLabs/blob/master/Lab3_InterFace/lab3_img/%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE%202025-11-18%20152330.png)

![image](https://github.com/Qs-689/MyLabs/blob/master/Lab3_InterFace/lab3_img/%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE%202025-11-18%20152352.png)

![image](https://github.com/Qs-689/MyLabs/blob/master/Lab3_InterFace/lab3_img/%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE%202025-11-18%20152438.png)
### 任务二：自定义AlertDialog

```Java
// 加载自定义布局
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
```
#### 项目运行

![image](https://github.com/Qs-689/MyLabs/blob/master/Lab3_InterFace/lab3_img/%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE%202025-11-18%20160939.png)
#### 点击按钮

![image](https://github.com/Qs-689/MyLabs/blob/master/Lab3_InterFace/lab3_img/%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE%202025-11-18%20161112.png)
#### 点击账号输入框

![image](https://github.com/Qs-689/MyLabs/blob/master/Lab3_InterFace/lab3_img/%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE%202025-11-18%20161149.png)
#### 点击密码输入框

![image](https://github.com/Qs-689/MyLabs/blob/master/Lab3_InterFace/lab3_img/%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE%202025-11-18%20161226.png)
#### 点击“Sign In”

![image](https://github.com/Qs-689/MyLabs/blob/master/Lab3_InterFace/lab3_img/%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE%202025-11-18%20161324.png)
### 任务三：XML菜单系统

```xml
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
```
#### 运行项目


![image](https://github.com/Qs-689/MyLabs/blob/master/Lab3_InterFace/lab3_img/%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE%202025-11-18%20161523.png)
#### 点击右上角菜单


![image](https://github.com/Qs-689/MyLabs/blob/master/Lab3_InterFace/lab3_img/%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE%202025-11-18%20161601.png)

#### 点击字体大小菜单
![image](https://github.com/Qs-689/MyLabs/blob/master/Lab3_InterFace/lab3_img/%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE%202025-11-25%20143134.png)

#### 点击字体颜色菜单
![image](https://github.com/Qs-689/MyLabs/blob/master/Lab3_InterFace/lab3_img/%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE%202025-11-25%20143151.png)

#### 调整字体


![image](https://github.com/Qs-689/MyLabs/blob/master/Lab3_InterFace/lab3_img/%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE%202025-11-18%20161628.png)

![image](https://github.com/Qs-689/MyLabs/blob/master/Lab3_InterFace/lab3_img/%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE%202025-11-18%20161650.png)
#### 点击普通菜单项

![image](https://github.com/Qs-689/MyLabs/blob/master/Lab3_InterFace/lab3_img/%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE%202025-11-18%20161718.png)
#### 调整字体颜色


![image](https://github.com/Qs-689/MyLabs/blob/master/Lab3_InterFace/lab3_img/%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE%202025-11-18%20161750.png)

![image](https://github.com/Qs-689/MyLabs/blob/master/Lab3_InterFace/lab3_img/%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE%202025-11-18%20161811.png)

![image](https://github.com/Qs-689/MyLabs/blob/master/Lab3_InterFace/lab3_img/%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE%202025-11-18%20161824.png)
### 任务四：ActionMode上下文菜单

```Java
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
                // 更新适配器状态
                adapter.setSelected(position, checked);
                updateActionModeTitle(mode);
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                mode.getMenuInflater().inflate(R.menu.context_menu, menu);
                actionMode = mode;
                adapter.setActionMode(true);
                updateActionModeTitle(mode);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
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
                    return true;
                }

                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                adapter.setActionMode(false);
                actionMode = null;
            }
        });

        // 修改点击监听器
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (actionMode != null) {
                    // 在多选模式下，切换选择状态
                    boolean newState = !adapter.isSelected(position);
                    adapter.setSelected(position, newState);
                    listView.setItemChecked(position, newState);
                    updateActionModeTitle(actionMode);
                } else {
                    // 普通模式下显示项目内容
                    Toast.makeText(MainActivity.this,
                            "点击了: " + dataList.get(position), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
```
#### 运行项目

![image](https://github.com/Qs-689/MyLabs/blob/master/Lab3_InterFace/lab3_img/%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE%202025-11-18%20162510.png)
#### 长按某列表

![image](https://github.com/Qs-689/MyLabs/blob/master/Lab3_InterFace/lab3_img/%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE%202025-11-18%20162551.png)
#### 多选列表

![image](https://github.com/Qs-689/MyLabs/blob/master/Lab3_InterFace/lab3_img/%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE%202025-11-18%20162630.png)
#### 按删除按钮

![image](https://github.com/Qs-689/MyLabs/blob/master/Lab3_InterFace/lab3_img/%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE%202025-11-18%20162708.png)
#### 长按某列表并全选

![image](https://github.com/Qs-689/MyLabs/blob/master/Lab3_InterFace/lab3_img/%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE%202025-11-18%20162741.png)
#### 按删除按钮

![image](https://github.com/Qs-689/MyLabs/blob/master/Lab3_InterFace/lab3_img/%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE%202025-11-18%20162802.png)
## ⚙️ 环境配置

1.  **Android Studio**: 推荐使用最新稳定版

2.  **Android SDK**: API 26 (Android 8.0) 或更高

3.  **JDK**: 1.8 或更高版本

## 🚀 构建与运行
### 克隆项目:

bash
git clone https://github.com/QS-689/Mylabs.git
### 导入Android Studio:

- 选择 Open an existing Android Studio project

- 导航到项目根目录

### 同步Gradle依赖:

- Android Studio会自动同步，或手动点击 Sync Project with Gradle Files

### 运行应用:

- 连接Android设备或启动模拟器（API 26+）

- 点击 Run 'app' 按钮

## ✨ 核心功能特性
### ListView功能
- 使用SimpleAdapter绑定数据

- 自定义列表项布局

- 点击显示Toast提示

- 通知功能集成

### AlertDialog功能
- 完全自定义对话框布局

- 规范的Android对话框交互

- 支持输入验证和数据处理

### 菜单系统功能
- XML定义的层级菜单

- 实时字体大小和颜色调整

- Toast反馈机制

### ActionMode功能
- 多选操作模式

- 上下文操作栏

- 批量操作支持

## 💡 技术要点总结
### 界面组件掌握
- **ListView与适配器**: 掌握SimpleAdapter的使用和数据绑定

- **自定义对话框**: 理解AlertDialog.Builder和setView()方法

- **XML菜单系统**: 掌握菜单资源文件的定义和使用

- **ActionMode**: 理解多选操作模式的完整生命周期

### 开发技能提升
- **资源管理**: 熟练使用布局、菜单、图片等资源文件

- **事件处理**: 掌握各种用户交互事件的处理方式

- **通知系统**: 学习Android通知的创建和显示

## 👤 作者信息
何其圣
