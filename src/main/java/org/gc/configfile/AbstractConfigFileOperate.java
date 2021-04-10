package org.gc.configfile;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
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
    public Map<String,Object> contentContains = new LinkedHashMap<>();
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
    public void clearConfigFileContent() {
        File file = new File(FILE_URL);
        if(file.exists()){
            try {
                FileWriter fw = new FileWriter(file);
                fw.write("");
                fw.flush();
                fw.close();
                contentContains.clear();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
        return toInt(contentContains.get(key));
    }

    @Override
    public Long getLongValueByKey(String key) {
        return toLong(contentContains.get(key));
    }

    @Override
    public Double getDoubleValueByKey(String key) {
        return toDouble(contentContains.get(key));
    }

    @Override
    public Boolean getBooleanValueByKey(String key) {
        return toBoolean(contentContains.get(key));
    }

    @Override
    public Map<String, Object> getAllConfigFileContent() {
        return contentContains;
    }



    private Integer toInt(Object value){
        if(value == null){
            return null;
        }
        if(value instanceof Integer){
            return (Integer) value;
        }
        if(value instanceof Number){
            return ((Number) value).intValue();
        }
        if(value instanceof String){
            String strVal = (String) value;
            if(strVal.length() == 0 //
                    || "null".equals(strVal) //
                    || "NULL".equals(strVal)){
                return null;
            }
            if(strVal.indexOf(',') != 0){
                strVal = strVal.replaceAll(",", "");
            }
            return Integer.parseInt(strVal);
        }
        if(value instanceof Boolean){
            return ((Boolean) value).booleanValue() ? 1 : 0;
        }
        return null;
    }

    private Long toLong(Object value){
        if(value == null){
            return null;
        }
        if(value instanceof Number){
            return ((Number) value).longValue();
        }
        if(value instanceof String) {
            String strVal = (String) value;
            if (strVal.length() == 0 //
                    || "null".equals(strVal) //
                    || "NULL".equals(strVal)) {
                return null;
            }
            if (strVal.indexOf(',') != 0) {
                strVal = strVal.replaceAll(",", "");
            }
            try {
                return Long.parseLong(strVal);
            } catch (NumberFormatException ex) {
                logger.error(ex.getMessage());
            }
        }
        return null;
    }

    private Double toDouble(Object value){
        if(value == null){
            return null;
        }
        if(value instanceof Number){
            return ((Number) value).doubleValue();
        }
        if(value instanceof String){
            String strVal = value.toString();
            if(strVal.length() == 0 //
                    || "null".equals(strVal) //
                    || "NULL".equals(strVal)){
                return null;
            }
            if(strVal.indexOf(',') != 0){
                strVal = strVal.replaceAll(",", "");
            }
            return Double.parseDouble(strVal);
        }
        return null;
    }


     private Boolean toBoolean(Object value){
        if(value == null){
            return null;
        }
        if(value instanceof Boolean){
            return (Boolean) value;
        }
        if(value instanceof Number){
            return ((Number) value).intValue() == 1;
        }
        if(value instanceof String){
            String strVal = (String) value;
            if(strVal.length() == 0
                    || "null".equals(strVal)
                    || "NULL".equals(strVal)){
                return null;
            }
            if("true".equalsIgnoreCase(strVal)
                    || "1".equals(strVal)){
                return Boolean.TRUE;
            }
            if("false".equalsIgnoreCase(strVal)
                    || "0".equals(strVal)){
                return Boolean.FALSE;
            }
        }
        return null;
    }



}
