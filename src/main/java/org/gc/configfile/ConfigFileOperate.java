package org.gc.configfile;

import java.util.Map;

/**
*@description: 配置文件操作接口
*@date 2021/4/9
*@author GCC
*/
public interface ConfigFileOperate {

    /**
     * 根据指定配置文件加载配置内容
     * @param fileurl 配置文件路径
     */
    public void loadConfigFile(String fileurl);

    /**
     * 清空配置文件内容
     */
    public void clearConfigFileContent();

    /**
     * 刷新配置内容：重新读取配置文件
     */
    public void refreshConfigContent(String fileurl);


    /**
     * 根据key值获取指定配置值
     * @param key key值
     * @return Object 配置内容
     */
    public Object getValueByKey(String key);

    /**
     * 根据key获取String类型配置值
     * @param key key
     * @return  String 配置内容
     */
    public String getStringValueByKey(String key);

    /**
     * 根据key获取String类型配置值
     * @param key key
     * @return  int 配置内容
     */
    public Integer getIntValueByKey(String key);

    /**
     * 根据key获取Long类型配置值，时间戳等
     * @param key key
     * @return  int 配置内容
     */
    public Long getLongValueByKey(String key);

    /**
     * 根据key获取Double类型配置值
     * @param key key
     * @return  int 配置内容
     */
    public Double getDoubleValueByKey(String key);

    /**
     * 根据key 获取 boolean 类型配置值
     * @param key key
     * @return boolean 配置内容
     */
    public Boolean getBooleanValueByKey(String key);

    /**
     * 获取全部配置内容
     * @return Map 全部配置文件
     */
    public Map<String,Object> getAllConfigFileContent();


    /**
     * 向配置文件追加内容
     * @param key key值
     * @param obj 数据值
     * @return boolean 是否写入成功
     */
    public boolean addContentToConfigFile(String key, Object obj);


    /**
     * 批量向配置文件中追加内容
     * @param contents 批量内容
     * @return boolean 是否写入成功
     */
    public boolean addContentsToConfigFile(Map contents);

}
