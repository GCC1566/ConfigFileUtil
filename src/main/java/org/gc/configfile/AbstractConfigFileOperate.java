package org.gc.configfile;
import org.apache.log4j.Logger;
import java.util.HashMap;
import java.util.Map;

/**
*@description: 配置文件操作抽象类
 * 扩展新的操作方式可实现该类
*@date 2021/4/10
*@author GCC
*/
public abstract class AbstractConfigFileOperate implements ConfigFileOperate {

    public Logger logger = null;
    //配置内容容器,子类可将内容放置其中
    public Map<String,Object> contentContains = new HashMap<>();
    //配置文件路径
    public String FILE_URL;

    /**
     * @param classname 子类名
     * @param fileurl 外界传入配置文件路径
     */
    public AbstractConfigFileOperate(Class<?> classname,String fileurl){
        logger = Logger.getLogger(classname);
        FILE_URL = fileurl;
    }

    @Override
    public void refreshConfigContent(String fileurl) {
        loadConfigFile(fileurl);
    }

    @Override
    public Object getValueByKey(String key) {
        if(contentContains.isEmpty()){
            loadConfigFile(FILE_URL);
        }
        return contentContains.get(key);
    }


    @Override
    public String getStringValueByKey(String key) {
        return String.valueOf(getValueByKey(key));
    }

    @Override
    public Integer getIntValueByKey(String key) {
        try{
            return Integer.valueOf(getStringValueByKey(key));
        }catch (Exception e){
            logger.error("该key不存在 或 类型错误"+e.getMessage());
        }
        return null;
    }

    @Override
    public Boolean getBooleanValueByKey(String key) {
        try{
            return Boolean.valueOf(getStringValueByKey(key));
        }catch (Exception e){
            logger.error("该key不存在 或 类型错误"+e.getMessage());
        }
        return null;
    }

    @Override
    public Map<String, Object> getAllConfigFileContent() {
        return contentContains;
    }

}
