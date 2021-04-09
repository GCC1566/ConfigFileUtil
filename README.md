# ConfigUtil
author GCC

## 写在最前面：

一个自定义的配置文件操作包，主要包含功能有：配置文件的加载，配置内容的刷新、根据key获取配置内容（各种数据类型），将数据写入配置文件，该模块的诞生主要是因为本猿比较喜欢造轮子，生产开发过程中，经常会碰到各种配置文件的操作，基于框架（例如SpringBoot）的配置文件操作契合度较高，平时个人做小工具的时候使用的框架比较灵活，这时候就需要一个可以拆箱即用的灵活配件，防止一遍遍地写重复的代码，于是打算做这么一个小包。设计较为简单，实现也不复杂，主要是灵活可扩展。

## 一、设计及扩展

​	1、整体功能基于一个 ConfigFileOperate接口，声明具体的公共方法

​    2、以抽象类AbstractConfigFileOperate来实现ConfigFileOperate接口的一些基本的读取前制功能，并创建公共的缓存对象contentcontain容器，继承抽象类AbstractConfigFileOperate的类在实现loadConfigFile（）方法时须将读取的配置内容放入容器中

​	3、分别的子包 json、xml、properties内分别实现了对应json配置文件、xml配置文件、properties（ini）配置的具体类，具体类继承自抽象类AbstractConfigFileOperate

​    4、对外提供一个静态工厂来支持对象的获取，以达到外部使用解耦的目的

## 二、扩展机制

​	1、想要额外扩展自己特定的配置文件读取，可继续在根目录下追加目录，继承并实现AbstractConfigFileOperate类，例如：

```java
public class PropertiesConfigFile extends AbstractConfigFileOperate {

    public PropertiesConfigFile(String fileurl) {
        super(PropertiesConfigFile.class, fileurl);
    }

    @Override
    public void loadConfigFile(String fileurl) {
    }

    @Override
    public boolean writeContentToConfigFile(String key, Object obj) {
        return false;
    }

    @Override
    public boolean writeContentsToConfigFile(Map contents) {
        return false;
    }
```

这里需要注意，实现loadConfigFile（）方法时，需要把从配置文件中读取的数据放到其父类AbstractConfigFileOperate类的contentcontain成员变量中。

​	2、在工厂ConfigFileOpFactory中，修改一下静态方法getConfigFileOp(String fileurl)，追加switch即可

例如：

```java
public static ConfigFileOperate getConfigFileOp(String fileurl){
        String filetype = getFileType(fileurl);
        switch (filetype){
            case "properties":
                return new PropertiesConfigFile(fileurl);
            case "xml":
                return new XMLConfigFile(fileurl);
            //追加case:
                
            default:
                logger.warn("该版本暂不支持此类配置文件的操作");
                return null;
        }
    }
```



## 三、使用方法

1.引入jar依赖

2.根据个人的配置文件根据工厂获取ConfigFileOperate接口对象

```java
 public static void main( String[] args )
    {
        ConfigFileOperate configFileOperate = ConfigFileOpFactory.getConfigFileOp("default.ini");
        String username = configFileOperate.getStringValueByKey("name");
        int age = configFileOperate.getIntValueByKey("age");
    }
```

