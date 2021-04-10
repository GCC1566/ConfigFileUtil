package org.gc.configfile.xml;

import org.gc.configfile.AbstractConfigFileOperate;

import java.util.Map;

public class XmlConfigFile extends AbstractConfigFileOperate {

    public XmlConfigFile(String fileurl) {
        super(XmlConfigFile.class, fileurl);
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
