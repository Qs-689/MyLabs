# NotePad 扩展
## （一）NoteList界面中笔记条目增加时间戳显示
### 1. 功能要求
- 每个新建笔记都会保存新建时间并显示在列表中
- 修改笔记后自动更新为修改时间
- 时间戳以直观的格式显示在笔记标题下方
### 2. 实现思路
#### 1.布局修改：在笔记列表项中添加新的TextView用于显示时间

#### 2.数据扩展：在数据库查询投影中添加修改时间字段

#### 3.适配器调整：更新SimpleCursorAdapter以绑定时间数据

#### 4.时间格式化：将时间戳转换为易读的日期时间格式

### 3. 技术实现
#### (1) 布局文件修改 - noteslist_item.xml
xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">
    
    <!-- 原标题TextView -->
    <TextView
        android:id="@android:id/text1"
        android:layout_width="match_parent"
        android:layout_height="?android:attr/listPreferredItemHeight"
        android:textAppearance="?android:attr/textAppearanceLarge"
        android:gravity="center_vertical"
        android:paddingLeft="5dip"
        android:singleLine="true" />
    
    <!-- 添加显示时间的TextView -->
    <TextView
        android:id="@+id/text1_time"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:textAppearance="?android:attr/textAppearanceSmall"
        android:paddingLeft="5dip"/>
</LinearLayout>

#### (2) 数据投影扩展 - NotesList.java
```Java
private static final String[] PROJECTION = new String[] {
    NotePad.Notes._ID, // 0
    NotePad.Notes.COLUMN_NAME_TITLE, // 1
    NotePad.Notes.COLUMN_NAME_MODIFICATION_DATE // 2 - 添加修改时间
};
```

#### (3) 适配器配置更新
```Java
// 数据列与视图ID的映射
String[] dataColumns = { 
    NotePad.Notes.COLUMN_NAME_TITLE, 
    NotePad.Notes.COLUMN_NAME_MODIFICATION_DATE 
};

int[] viewIDs = { 
    android.R.id.text1, 
    R.id.text1_time 
};
```

#### (4) 时间格式化处理
在NotePadProvider的insert方法中：

```Java
// 获取当前系统时间（毫秒）
Long now = Long.valueOf(System.currentTimeMillis());
Date date = new Date(now);
SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
String dateTime = format.format(date);

// 如果值映射中不包含创建日期，则设置为当前时间
if (values.containsKey(NotePad.Notes.COLUMN_NAME_CREATE_DATE) == false) {
    values.put(NotePad.Notes.COLUMN_NAME_CREATE_DATE, dateTime);
}

// 如果值映射中不包含修改日期，则设置为当前时间
if (values.containsKey(NotePad.Notes.COLUMN_NAME_MODIFICATION_DATE) == false) {
    values.put(NotePad.Notes.COLUMN_NAME_MODIFICATION_DATE, dateTime);
}
```
在NoteEditor的updateNote方法中：

```java
Long now = Long.valueOf(System.currentTimeMillis());
Date date = new Date(now);
SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
String dateTime = format.format(date);
values.put(NotePad.Notes.COLUMN_NAME_MODIFICATION_DATE, dateTime);
```
### 4.实现效果界面截图
#### (1)创建/修改笔记后显示创建/修改时间
<img width="1080" height="2400" alt="Screenshot_20251125_194624" src="https://github.com/user-attachments/assets/e7d93676-27d6-42b4-aaa2-ae8c7cb2ce89" />

## （二）添加笔记查询功能（根据标题或内容查询）
### 1. 功能要求
- 在笔记列表界面提供搜索框，支持实时搜索

- 可根据笔记标题或内容进行关键字查询

- 查询结果实时显示在列表中

### 2. 实现思路
#### 1.界面添加：在ActionBar中添加搜索菜单项和搜索框

#### 2.搜索逻辑：实现SearchView的监听器处理搜索输入

#### 3.数据过滤：根据输入内容动态过滤Cursor数据

#### 4.结果显示：更新ListView显示过滤后的结果

### 3. 技术实现
#### (1) 菜单文件添加搜索项 - list_options_menu.xml
```java
<menu xmlns:android="http://schemas.android.com/apk/res/android">
    <item
        android:id="@+id/menu_search"
        android:title="搜索"
        android:icon="@android:drawable/ic_search_category_default"
        android:showAsAction="ifRoom|collapseActionView"
        android:actionViewClass="android.widget.SearchView" />
</menu>
```   
#### (2) 搜索功能实现 - NotesList.java
```java
@Override
public boolean onCreateOptionsMenu(Menu menu) {
    super.onCreateOptionsMenu(menu);
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.list_options_menu, menu);
    
    // 获取搜索菜单项
    MenuItem searchItem = menu.findItem(R.id.menu_search);
    SearchView searchView = (SearchView) searchItem.getActionView();
    
    // 设置搜索框配置
    searchView.setQueryHint("搜索笔记标题或内容...");
    
    // 搜索文本变化监听
    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            // 执行搜索
            performSearch(query);
            return true;
        }
        
        @Override
        public boolean onQueryTextChange(String newText) {
            // 实时搜索
            performSearch(newText);
            return true;
        }
    });
    
    return true;
}

/**
 * 执行搜索功能
 */
private void performSearch(String query) {
    if (query == null || query.trim().isEmpty()) {
        // 如果搜索内容为空，显示所有笔记
        setAdapterWithFilter(null);
    } else {
        // 构建搜索条件
        String selection = NotePad.Notes.COLUMN_NAME_TITLE + " LIKE ? OR " + 
                          NotePad.Notes.COLUMN_NAME_NOTE + " LIKE ?";
        String[] selectionArgs = new String[] { 
            "%" + query + "%", 
            "%" + query + "%" 
        };
        
        setAdapterWithFilter(selection, selectionArgs);
    }
}

/**
 * 根据条件设置适配器
 */
private void setAdapterWithFilter(String selection, String[] selectionArgs) {
    // 重新查询Cursor
    Cursor cursor = managedQuery(
        getIntent().getData(),            // 内容URI
        PROJECTION,                       // 返回列
        selection,                        // 条件语句
        selectionArgs,                    // 条件参数
        NotePad.Notes.DEFAULT_SORT_ORDER  // 排序
    );
    
    // 更新适配器
    String[] dataColumns = { NotePad.Notes.COLUMN_NAME_TITLE, NotePad.Notes.COLUMN_NAME_MODIFICATION_DATE };
    int[] viewIDs = { android.R.id.text1, R.id.text1_time };
    
    SimpleCursorAdapter adapter = new SimpleCursorAdapter(
        this,                            // Context
        R.layout.noteslist_item,          // 布局文件
        cursor,                          // 数据
        dataColumns,                     // 数据列
        viewIDs                          // 视图ID
    );
    
    setListAdapter(adapter);
}
```
### 4.实现效果界面截图
#### (1)点击搜索按钮进行搜索界面
![qq_pic_merged_1764071692248](https://github.com/user-attachments/assets/b443d3f5-f9ae-4817-ae1c-d9e124d93303)

#### (2)输入搜索内容，显示符合条件（根据标题或内容查询）的笔记
<img width="1080" height="2400" alt="Screenshot_20251125_195604" src="https://github.com/user-attachments/assets/2fa868a0-e5e9-4a37-a342-deb40d173a09" />

#### (3)回删搜素内容至空时，显示所有的笔记
<img width="1080" height="2400" alt="Screenshot_20251125_195024" src="https://github.com/user-attachments/assets/58ce1e25-e74a-4f24-a57f-2efa7763c860" />



## （三）笔记分类功能
### 1. 功能要求
- 支持为笔记添加分类标签

- 可按分类筛选显示笔记

- 提供分类管理界面

### 2. 实现思路
#### 1.数据库扩展：在笔记表中添加分类字段

#### 2.界面增强：在编辑界面添加分类选择功能

#### 3.分类筛选：实现按分类过滤笔记列表

#### 4.分类管理：提供分类的增删改查功能

### 3. 技术实现
#### (1) 数据库扩展 - NotePad.java
```java
public static final class Notes implements BaseColumns {
    // 原有字段...
    public static final String COLUMN_NAME_CATEGORY = "category";
    
    // 更新数据库创建语句
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + NOTES_TABLE_NAME + " ("
                + _ID + " INTEGER PRIMARY KEY,"
                + COLUMN_NAME_TITLE + " TEXT,"
                + COLUMN_NAME_NOTE + " TEXT,"
                + COLUMN_NAME_CREATE_DATE + " INTEGER,"
                + COLUMN_NAME_MODIFICATION_DATE + " INTEGER,"
                + COLUMN_NAME_CATEGORY + " TEXT"  // 添加分类字段
                + ");");
    }
}
```
#### (2) 分类选择界面 - res/layout/category_dialog.xml
```java
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="vertical"
    android:padding="16dp">

    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="选择分类"
        android:textSize="18sp"
        android:textStyle="bold"
        android:layout_marginBottom="16dp" />

    <Spinner
        android:id="@+id/category_spinner"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:entries="@array/categories" />

    <EditText
        android:id="@+id/new_category"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="8dp"
        android:hint="或输入新分类" />

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal"
        android:gravity="end"
        android:layout_marginTop="16dp">

        <Button
            android:id="@+id/btn_cancel"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="取消" />

        <Button
            android:id="@+id/btn_ok"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="确定"
            android:layout_marginStart="8dp" />

    </LinearLayout>

</LinearLayout>
```
#### (3) 分类数组资源 - res/values/arrays.xml
```java
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <string-array name="categories">
        <item>未分类</item>
        <item>工作</item>
        <item>学习</item>
        <item>生活</item>
        <item>个人</item>
    </string-array>
</resources>
```
#### (4) 分类功能实现 - NoteEditor.java
```java
// 在编辑界面添加分类选择菜单
@Override
public boolean onCreateOptionsMenu(Menu menu) {
    super.onCreateOptionsMenu(menu);
    
    // 添加分类菜单项
    MenuItem categoryItem = menu.add(0, MENU_CATEGORY, 0, "分类");
    categoryItem.setIcon(android.R.drawable.ic_menu_sort_by_size);
    
    return true;
}

// 处理分类菜单点击
@Override
public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
        case MENU_CATEGORY:
            showCategoryDialog();
            return true;
        default:
            return super.onOptionsItemSelected(item);
    }
}

/**
 * 显示分类选择对话框
 */
private void showCategoryDialog() {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    LayoutInflater inflater = getLayoutInflater();
    View dialogView = inflater.inflate(R.layout.category_dialog, null);
    builder.setView(dialogView);
    
    final Spinner categorySpinner = (Spinner) dialogView.findViewById(R.id.category_spinner);
    final EditText newCategoryEdit = (EditText) dialogView.findViewById(R.id.new_category);
    Button btnCancel = (Button) dialogView.findViewById(R.id.btn_cancel);
    Button btnOk = (Button) dialogView.findViewById(R.id.btn_ok);
    
    final AlertDialog dialog = builder.create();
    
    // 设置当前分类
    String currentCategory = getCurrentCategory();
    if (currentCategory != null) {
        ArrayAdapter<CharSequence> adapter = (ArrayAdapter<CharSequence>) categorySpinner.getAdapter();
        int position = adapter.getPosition(currentCategory);
        if (position >= 0) {
            categorySpinner.setSelection(position);
        }
    }
    
    btnCancel.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dialog.dismiss();
        }
    });
    
    btnOk.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String selectedCategory;
            if (!newCategoryEdit.getText().toString().trim().isEmpty()) {
                selectedCategory = newCategoryEdit.getText().toString().trim();
            } else {
                selectedCategory = categorySpinner.getSelectedItem().toString();
            }
            
            updateNoteCategory(selectedCategory);
            dialog.dismiss();
        }
    });
    
    dialog.show();
}

/**
 * 更新笔记分类
 */
private void updateNoteCategory(String category) {
    ContentValues values = new ContentValues();
    values.put(NotePad.Notes.COLUMN_NAME_CATEGORY, category);
    
    getContentResolver().update(
        mUri,    // 笔记URI
        values,  // 更新值
        null,    // WHERE条件
        null     // WHERE参数
    );
    
    Toast.makeText(this, "分类已更新: " + category, Toast.LENGTH_SHORT).show();
}
```
#### (5) 按分类筛选 - NotesList.java
```java
// 添加分类筛选菜单
@Override
public boolean onCreateOptionsMenu(Menu menu) {
    super.onCreateOptionsMenu(menu);
    
    // 添加分类筛选子菜单
    SubMenu categoryMenu = menu.addSubMenu("分类筛选");
    categoryMenu.add(0, MENU_CATEGORY_ALL, 0, "全部");
    categoryMenu.add(0, MENU_CATEGORY_WORK, 0, "工作");
    categoryMenu.add(0, MENU_CATEGORY_STUDY, 0, "学习");
    categoryMenu.add(0, MENU_CATEGORY_LIFE, 0, "生活");
    categoryMenu.add(0, MENU_CATEGORY_PERSONAL, 0, "个人");
    
    return true;
}

// 处理分类筛选
@Override
public boolean onOptionsItemSelected(MenuItem item) {
    String categoryFilter = null;
    
    switch (item.getItemId()) {
        case MENU_CATEGORY_ALL:
            categoryFilter = null;  // 显示全部
            break;
        case MENU_CATEGORY_WORK:
            categoryFilter = "工作";
            break;
        case MENU_CATEGORY_STUDY:
            categoryFilter = "学习";
            break;
        case MENU_CATEGORY_LIFE:
            categoryFilter = "生活";
            break;
        case MENU_CATEGORY_PERSONAL:
            categoryFilter = "个人";
            break;
    }
    
    if (item.getItemId() >= MENU_CATEGORY_ALL && item.getItemId() <= MENU_CATEGORY_PERSONAL) {
        filterByCategory(categoryFilter);
        return true;
    }
    
    return super.onOptionsItemSelected(item);
}

/**
 * 按分类筛选笔记
 */
private void filterByCategory(String category) {
    String selection = null;
    String[] selectionArgs = null;
    
    if (category != null) {
        selection = NotePad.Notes.COLUMN_NAME_CATEGORY + " = ?";
        selectionArgs = new String[] { category };
    }
    
    // 重新查询并更新列表
    Cursor cursor = managedQuery(
        getIntent().getData(),
        PROJECTION,
        selection,
        selectionArgs,
        NotePad.Notes.DEFAULT_SORT_ORDER
    );
   
}
```
### 4.实现效果界面截图

#### (1)点击右上角菜单按钮并点击分类筛选
![retouch_2025112520091620(1)](https://github.com/user-attachments/assets/780f3026-1767-457d-9c8b-d3f3b5bf5664)
#### (2)分别点击通用/工作/个人/想法
<img width="1080" height="2400" alt="Screenshot_20251125_201146" src="https://github.com/user-attachments/assets/24649d22-f911-40bc-8b94-6e8f6adf7d4f" />
<img width="1080" height="2400" alt="Screenshot_20251125_201205" src="https://github.com/user-attachments/assets/6ed3932d-d774-4cc2-bea5-5a3af6a438cd" />
<img width="1080" height="2400" alt="Screenshot_20251125_201224" src="https://github.com/user-attachments/assets/981e3971-57c9-4f0c-91b8-3cc0ad613883" />
<img width="1080" height="2400" alt="Screenshot_20251125_201238" src="https://github.com/user-attachments/assets/d37831ff-0cd4-449c-93e6-3715c28965b8" />
#### (3)点击所有分类回到首页
<img width="1080" height="2400" alt="Screenshot_20251125_201314" src="https://github.com/user-attachments/assets/df137251-ad1c-4f5f-8fb0-a41e0accd1ba" />

#### (4)点击右上角搜索按钮并点击分类筛选
![retouch_2025112520154286(1)](https://github.com/user-attachments/assets/bade7967-c17d-4103-a78f-c0b1549475d3)
#### (5)分别点击通用/工作/个人/想法
<img width="1080" height="2400" alt="Screenshot_20251125_201720" src="https://github.com/user-attachments/assets/52b41fef-ab0c-42b1-9d32-4f834150830f" />
<img width="1080" height="2400" alt="Screenshot_20251125_201739" src="https://github.com/user-attachments/assets/5017aed3-47c8-4de9-b69e-2efde6eeb424" />
<img width="1080" height="2400" alt="Screenshot_20251125_201820" src="https://github.com/user-attachments/assets/5211eff0-a031-4869-bf5d-6f646f0e9d6c" />
<img width="1080" height="2400" alt="Screenshot_20251125_201836" src="https://github.com/user-attachments/assets/e3510cac-baa8-4f51-bd3b-eeb734322e6f" />
#### (6)点击所有分类
<img width="1080" height="2400" alt="Screenshot_20251125_201911" src="https://github.com/user-attachments/assets/0f791114-5614-4ab5-91db-4d28163bbacf" />

#### (7)编写/修改笔记时选择分类
![retouch_2025112520274511(1)(1)](https://github.com/user-attachments/assets/e91d6116-0339-4b9c-b555-6634f0584c30)














