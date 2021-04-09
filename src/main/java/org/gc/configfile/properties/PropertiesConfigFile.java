package org.gc.configfile.properties;

import org.gc.configfile.AbstractConfigFileOperate;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;

/**
*@description: properties类型配置文件(兼容ini后缀)
*@date 2021/4/10
*@author GCC
*/
public class PropertiesConfigFile extends AbstractConfigFileOperate {

    public PropertiesConfigFile(String fileurl) {
        super(PropertiesConfigFile.class, fileurl);
    }

    @Override
    public void loadConfigFile(String fileurl) {
        BufferedReader input = null;
        Properties properties = new Properties();
        try {
            input = new BufferedReader(new InputStreamReader(new FileInputStream(fileurl)));
            properties.load(input);
            Enumeration<?> enm = properties.propertyNames();
            while(enm.hasMoreElements()){
                String key = (String)enm.nextElement();
                contentContains.put(key,properties.getProperty(key));
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }finally {
            try{
                input.close();
            }catch (Exception e){
                logger.error(e.getMessage());
            }
        }
    }

    @Override
    public boolean writeContentToConfigFile(String key, Object obj) {
        return false;
    }

    @Override
    public boolean writeContentsToConfigFile(Map contents) {
        return false;
    }
}
