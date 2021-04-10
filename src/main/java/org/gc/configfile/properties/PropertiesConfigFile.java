package org.gc.configfile.properties;

import org.gc.configfile.AbstractConfigFileOperate;
import java.io.*;
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
        contentContains.clear();
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
    public boolean addContentToConfigFile(String key, Object obj) {
        File file = new File(FILE_URL);
        if(file.exists()) {
            try {
                FileWriter fw = new FileWriter(file, true);
                BufferedWriter bufw=new BufferedWriter(fw);
                bufw.newLine();
                bufw.write(key+"="+obj);
                bufw.close();
                fw.close();
            }catch(IOException e){
                logger.error("配置文件写入失败！"+e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean addContentsToConfigFile(Map contents) {
        File file = new File(FILE_URL);
        if(file.exists()) {
            try {
                FileWriter fw = new FileWriter(file, true);
                BufferedWriter bufw=new BufferedWriter(fw);
                for(Object keystr:contents.keySet()){
                    bufw.newLine();
                    bufw.write(keystr+"="+contents.get(keystr));
                    bufw.newLine();
                }
                bufw.close();
                fw.close();
            }catch(IOException e){
                logger.error("配置文件写入失败！"+e.getMessage());
            }
        }
        return false;
    }
}
