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
// 使用SimpleAdapter创建列表
SimpleAdapter adapter = new SimpleAdapter(this, data, 
    R.layout.list_item, 
    new String[]{"icon", "title", "subtitle"}, 
    new int[]{R.id.ivIcon, R.id.tvTitle, R.id.tvSubtitle});

listView.setAdapter(adapter);

// 列表项点击事件
listView.setOnItemClickListener((parent, view, position, id) -> {
    String selectedItem = data.get(position).get("title");
    Toast.makeText(this, "选中: " + selectedItem, Toast.LENGTH_SHORT).show();
    
    // 发送通知
    sendNotification(selectedItem);
});
```
#### 项目运行

<img src="C:\Users\16549\Pictures\Screenshots\屏幕截图 2025-11-18 151955.png" alt="屏幕截图 2025-11-18 151955" style="zoom:50%;" />

#### 点击列表

<img src="C:\Users\16549\Pictures\Screenshots\屏幕截图 2025-11-18 152330.png" alt="屏幕截图 2025-11-18 152330" style="zoom:50%;" />

<img src="C:\Users\16549\Pictures\Screenshots\屏幕截图 2025-11-18 152352.png" alt="屏幕截图 2025-11-18 152352" style="zoom:50%;" />

<img src="C:\Users\16549\Pictures\Screenshots\屏幕截图 2025-11-18 152438.png" alt="屏幕截图 2025-11-18 152438" style="zoom:50%;" />

### 任务二：自定义AlertDialog

```Java
// 加载自定义布局
View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_custom, null);

AlertDialog.Builder builder = new AlertDialog.Builder(this);
builder.setView(dialogView)
       .setTitle("自定义对话框")
       .setPositiveButton("确定", (dialog, which) -> {
           // 处理确定按钮点击
       })
       .setNegativeButton("取消", (dialog, which) -> {
           dialog.dismiss();
       });

AlertDialog dialog = builder.create();
dialog.show();
```
#### 项目运行

<img src="C:\Users\16549\AppData\Roaming\Typora\typora-user-images\image-20251118160947302.png" alt="image-20251118160947302" style="zoom:50%;" />

#### 点击按钮

<img src="C:\Users\16549\AppData\Roaming\Typora\typora-user-images\image-20251118161113977.png" alt="image-20251118161113977" style="zoom:50%;" />

#### 点击账号输入框

<img src="C:\Users\16549\AppData\Roaming\Typora\typora-user-images\image-20251118161151640.png" alt="image-20251118161151640" style="zoom:50%;" />

#### 点击密码输入框

<img src="C:\Users\16549\AppData\Roaming\Typora\typora-user-images\image-20251118161229446.png" alt="image-20251118161229446" style="zoom:50%;" />

#### 点击“Sign In”

<img src="C:\Users\16549\AppData\Roaming\Typora\typora-user-images\image-20251118161329602.png" alt="image-20251118161329602" style="zoom:50%;" />

### 任务三：XML菜单系统

```xml
<!-- res/menu/main_menu.xml -->
<menu xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:id="@+id/menu_font_size" android:title="字体大小">
        <menu>
            <item android:id="@+id/menu_font_small" android:title="小 (10号字)"/>
            <item android:id="@+id/menu_font_medium" android:title="中 (16号字)"/>
            <item android:id="@+id/menu_font_large" android:title="大 (20号字)"/>
        </menu>
    </item>
    <item android:id="@+id/menu_normal" android:title="普通菜单项"/>
    <item android:id="@+id/menu_font_color" android:title="字体颜色">
        <menu>
            <item android:id="@+id/menu_color_red" android:title="红色"/>
            <item android:id="@+id/menu_color_black" android:title="黑色"/>
        </menu>
    </item>
</menu>
```
#### 运行项目

<img src="C:\Users\16549\AppData\Roaming\Typora\typora-user-images\image-20251118161524254.png" alt="image-20251118161524254" style="zoom:50%;" />

#### 点击右上角菜单

<img src="C:\Users\16549\AppData\Roaming\Typora\typora-user-images\image-20251118161603732.png" alt="image-20251118161603732" style="zoom:50%;" />

#### 调整字体

<img src="C:\Users\16549\AppData\Roaming\Typora\typora-user-images\image-20251118161630747.png" alt="image-20251118161630747" style="zoom:50%;" />

<img src="C:\Users\16549\AppData\Roaming\Typora\typora-user-images\image-20251118161652958.png" alt="image-20251118161652958" style="zoom:50%;" />

#### 点击普通菜单项

<img src="C:\Users\16549\AppData\Roaming\Typora\typora-user-images\image-20251118161722838.png" alt="image-20251118161722838" style="zoom:50%;" />

#### 调整字体颜色

<img src="C:\Users\16549\AppData\Roaming\Typora\typora-user-images\image-20251118161754291.png" alt="image-20251118161754291" style="zoom:50%;" />

<img src="C:\Users\16549\AppData\Roaming\Typora\typora-user-images\image-20251118161812071.png" alt="image-20251118161812071" style="zoom:50%;" />

<img src="C:\Users\16549\AppData\Roaming\Typora\typora-user-images\image-20251118161829505.png" alt="image-20251118161829505" style="zoom:50%;" />

### 任务四：ActionMode上下文菜单

```Java
listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        mode.getMenuInflater().inflate(R.menu.context_menu, menu);
        return true;
    }
    
    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        // 处理菜单项点击
        return true;
    }
});
```
#### 运行项目

<img src="C:\Users\16549\AppData\Roaming\Typora\typora-user-images\image-20251118162511059.png" alt="image-20251118162511059" style="zoom:50%;" />

#### 长按某列表

<img src="C:\Users\16549\AppData\Roaming\Typora\typora-user-images\image-20251118162602458.png" alt="image-20251118162602458" style="zoom:50%;" />

#### 多选列表

<img src="C:\Users\16549\AppData\Roaming\Typora\typora-user-images\image-20251118162634409.png" alt="image-20251118162634409" style="zoom:50%;" />

#### 按删除按钮

<img src="C:\Users\16549\AppData\Roaming\Typora\typora-user-images\image-20251118162710808.png" alt="image-20251118162710808" style="zoom: 50%;" />

#### 长按某列表并全选

<img src="C:\Users\16549\AppData\Roaming\Typora\typora-user-images\image-20251118162745322.png" alt="image-20251118162745322" style="zoom:50%;" />

#### 按删除按钮

<img src="C:\Users\16549\AppData\Roaming\Typora\typora-user-images\image-20251118162804849.png" alt="image-20251118162804849" style="zoom:50%;" />

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
