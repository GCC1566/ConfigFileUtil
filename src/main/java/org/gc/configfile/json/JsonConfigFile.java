package org.gc.configfile.json;

import org.gc.configfile.AbstractConfigFileOperate;

import java.util.Map;

public class JsonConfigFile extends AbstractConfigFileOperate {

    public JsonConfigFile(String fileurl){
        super(JsonConfigFile.class,fileurl);
    }

    @Override
    public void loadConfigFile(String fileurl) {

    }

    @Override
    public boolean addContentToConfigFile(String key, Object obj) {
        return false;
    }

    @Override
    public boolean addContentsToConfigFile(Map contents) {
        return false;
    }
}
