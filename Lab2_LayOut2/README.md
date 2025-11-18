# 实验二 Android布局学习实验

## 📖 项目简介
本项目是Android布局学习的实验项目，实现了四种不同的Android布局方式，并通过底部导航栏进行界面切换。

## ✨ 功能特性
- **四种布局实现**：线性布局、表格布局、约束布局1、约束布局2
- **底部导航栏**：实现四个界面之间的流畅切换
- **界面跳转**：通过导航按钮实现多界面导航功能

## 📁 项目结构
Lab2_LayOut/  
├── app/  
│ ├── manifests/  
│ │ └── AndroidManifest.xml  
│ ├── java/  
│ │ └── com/example/lab2_layout/  
│ │ ├── MainActivity1.java  
│ │ ├── MainActivity2.java  
│ │ ├── MainActivity3.java  
│ │ └── MainActivity4.java  
│ └── res/  
│ ├── drawable/  
│ │ ├── double_arrows.png  
│ │ ├── space_station_icon.png  
│ │ ├── rocket_icon.png  
│ │ ├── rover_icon.png  
│ │ └── single_arrow.png  
│ ├── layout/  
│ │ ├── activity_main.xml  
│ │ ├── activity_main2.xml  
│ │ ├── activity_main3.xml  
│ │ ├── activity_main4.xml  
│ │ └── bottom_navigation.xml  
│ └── values/  
├── gradle/  
│ └── wrapper/  
├── build.gradle  
├── settings.gradle  
└── .gitignore  
## 🎨 布局实现

### 📏 线性布局 (MainActivity1)
- **布局文件**：activity_main.xml
- **特点**：垂直或水平排列子视图

### 📊 表格布局 (MainActivity2)
- **布局文件**：activity_main2.xml
- **特点**：表格形式排列，支持行列合并

### 🔗 约束布局1 (MainActivity3)
- **布局文件**：activity_main3.xml
- **特点**：通过约束关系定位视图

### 🔗 约束布局2 (MainActivity4)
- **布局文件**：activity_main4.xml
- **特点**：复杂的约束关系，链式布局

## ⚙️ 环境配置
- **Android Studio**：推荐使用最新稳定版
- **Git**：确保已安装并配置全局用户信息

### 📥 克隆项目
bash
git clone https://github.com/QS-689/Mylabs.git
### 🔧 构建步骤
1. 用Android Studio打开项目
2. 同步Gradle依赖
3. 连接Android设备或启动模拟器
4. 点击运行按钮安装应用

## 💡 技术要点

### 🎯 布局方式
- **线性布局**：简单列表式布局
- **表格布局**：数据表格展示
- **约束布局**：复杂响应式界面

### 🧭 导航实现
- 底部导航栏布局
- 多Activity跳转逻辑
- 界面切换机制

## 📚 实验收获
通过本实验掌握了：
- 四种Android布局方式的应用
- 多Activity架构设计
- 底部导航栏的使用
- 资源文件管理规范

## 👤 作者信息
何其圣

## 📄 许可证
本项目仅用于学习目的。
