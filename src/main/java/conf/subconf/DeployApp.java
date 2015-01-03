package conf.subconf;


import conf.IDeploy;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by lab on 2015/1/3.
 */
public class DeployApp implements IDeploy {
    private String appSrc;
    private String appIndex;

    private static Logger logger = Logger.getLogger(DeployApp.class);

    public String getAppSrc() {
        return appSrc;
    }

    public void setAppSrc(String appSrc) {
        this.appSrc = appSrc;
    }

    public String getAppIndex() {
        return appIndex;
    }

    public void setAppIndex(String appIndex) {
        this.appIndex = appIndex;
    }

    @Override
    public String toString() {
        return "DeployApp{" +
                "appSrc='" + appSrc + '\'' +
                ", appIndex='" + appIndex + '\'' +
                '}';
    }

    @Override
    public void initInstance(String path) {
        InputStream in = null;
        try {
            in = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            logger.error("无法读取配置文件，检查路径是否正确",e);
            e.printStackTrace();
        }
    }

    @Override
    public void initInstance(InputStream inputStream) {
        SAXReader reader = new SAXReader();
        Document doc = null;
        try {
            doc = reader.read(inputStream);
        } catch (DocumentException e) {
            logger.error("无法读取配置文件，检查路径是否正确",e);
            e.printStackTrace();
        }

        Node node = doc.selectSingleNode("deploy-app");
        initInstance(node);
    }

    @Override
    public void initInstance(Node node) {
        Node appSrc = node.selectSingleNode("app-src");
        setAppSrc(appSrc.getText());
        Node appIndex = node.selectSingleNode("app-index");
        setAppIndex(appIndex.getText());
    }
}
