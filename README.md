# 实验一 Helloworld

这是一个使用Android Studio创建的第一个Android应用程序项目，并已通过Git Bash命令行成功同步至GitHub。

## 📖功能简介

- 一个简单的“Hello World”基础应用。
- 作为学习Android开发与Git版本控制的入门练习项目。

## 技术栈与工具

- **开发语言**: Java
- **开发工具**: Android Studio
- **版本控制**: Git
- **项目管理**: Gradle

## 📁项目结构
├── app/
│ ├── src/main/java/ # Java源代码目录
│ ├── src/main/res/ # 资源文件目录（布局、图片、字符串等）
│ └── build.gradle # Module级别的Gradle构建脚本
├── gradle/
│ └── wrapper/ # Gradle Wrapper文件，用于统一构建环境
├── build.gradle # Project级别的Gradle构建脚本
├── settings.gradle # 项目设置文件，定义包含的模块
└── .gitignore # Git忽略规则文件
## 环境配置

1.  **Android Studio**: 推荐使用最新稳定版。
2.  **Git**: 确保已安装并配置全局用户信息。
3.  **JDK**: Android Studio通常自带JDK。

## 如何克隆与构建

1.  **克隆项目到本地**:
bash
git clone https://github.com/QS-689/Mylabs.git
2.  **用Android Studio打开项目**:
- 选择 `Open` 菜单，然后选中项目根目录下的 `build.gradle` 文件。
3.  **同步与构建**:
- Android Studio会自动同步Gradle依赖。如果未自动同步，请点击工具栏的 `Sync Project with Gradle Files` 按钮。
4.  **运行应用**:
- 连接Android设备或启动模拟器。
- 点击 `Run` 按钮 (绿色的播放图标) 来安装和运行应用。

## .gitignore策略

本项目使用标准的Android `.gitignore` 规则，旨在避免将自动生成的文件、缓存和敏感信息提交到版本库。主要忽略的文件和目录包括：

- `/build` - 编译生成目录
- `/.idea` - IDE配置工作区
- `*.iml` - Module配置文件
- `/local.properties` - 包含本地SDK路径的配置文件（可能包含用户特定的路径）


## 📚实验总结

通过本实验，成功掌握了以下技能：
1.  使用Android Studio创建新项目。
2.  使用Git Bash进行本地仓库的初始化、提交等基本版本控制操作。
3.  配置有效的 `.gitignore` 文件来管理无需版本控制的文件。
4.  将本地代码仓库与远程GitHub仓库关联并推送代码。

## 👤 作者信息
何其圣

## 📄 许可证
本项目仅用于学习目的。



# 实验二Android布局学习实验

## 📖 项目简介
本项目是Android布局学习的实验项目，实现了四种不同的Android布局方式，并通过底部导航栏进行界面切换。

## ✨ 功能特性
- **四种布局实现**：线性布局、表格布局、约束布局1、约束布局2
- **底部导航栏**：实现四个界面之间的流畅切换
- **界面跳转**：通过导航按钮实现多界面导航功能

## 📁 项目结构
Lab2_LayoutLearning/
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
