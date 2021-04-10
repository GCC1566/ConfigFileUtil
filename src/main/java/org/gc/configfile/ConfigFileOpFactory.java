package org.gc.configfile;

import org.apache.log4j.Logger;
import org.gc.configfile.json.JsonConfigFile;
import org.gc.configfile.properties.PropertiesConfigFile;
import org.gc.configfile.xml.XmlConfigFile;

/**
*@description: 配置文件操作器工厂
 * 根据传入的配置文件自动识别其基本类型，并产生响应的对象
*@date 2021/4/10
*@author GCC
*/
public class ConfigFileOpFactory {

    public final static Logger logger = Logger.getLogger(ConfigFileOpFactory.class);

    /**
     * 根据配置文件产出响应的配置文件操作接口
     * @param fileurl 配置文件路径
     * @return ConfigFileOperate 对象
     */
    public static ConfigFileOperate getConfigFileOp(String fileurl){
        String filetype = getFileType(fileurl);
        switch (filetype){
            case "properties":
                return new PropertiesConfigFile(fileurl);
            case "ini":
                return new PropertiesConfigFile(fileurl);
            case "json":
                return new JsonConfigFile(fileurl);
            case "xml":
                return new XmlConfigFile(fileurl);
            default:
                logger.warn("该版本暂不支持此类配置文件的操作");
                return null;
        }
    }

    /**
     * 根据配置文件路径获取文件类型
     * @param fileurl 文件路径
     * @return String 文件类型
     */
    private static String getFileType(String fileurl){
        int lastindex = fileurl.lastIndexOf(".");
        return fileurl.substring(lastindex+1).toLowerCase();
    }
}
