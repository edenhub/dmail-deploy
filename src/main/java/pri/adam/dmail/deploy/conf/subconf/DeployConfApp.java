package pri.adam.dmail.deploy.conf.subconf;


import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import pri.adam.dmail.deploy.conf.IDeployConf;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by lab on 2015/1/3.
 * 数据库配置信息对象
 *
 * @auth adam
 * @sicne 1.0-SNAPSHOT
 */
public class DeployConfApp implements IDeployConf {
    private String appSrc;
    private String appIndex;

    private static Logger logger = Logger.getLogger(DeployConfApp.class);

    /*
   *
   *  =======================================================================================
   *                                  Getter And Setter
   *  =======================================================================================
   *
   */

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

      /*
     *
     *  =======================================================================================
     *                                  Getter And Setter
     *  =======================================================================================
     *
     */

    @Override
    public String toString() {
        return "DeployApp{" +
                "appSrc='" + appSrc + '\'' +
                ", appIndex='" + appIndex + '\'' +
                '}';
    }

    /**
     * @param path
     */
    @Override
    public void initInstance(String path) {
        InputStream in = null;
        try {
            in = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            logger.error("无法读取配置文件，检查路径是否正确", e);
            e.printStackTrace();
        }
    }

    /**
     * @param inputStream
     */
    @Override
    public void initInstance(InputStream inputStream) {
        SAXReader reader = new SAXReader();
        Document doc = null;
        try {
            doc = reader.read(inputStream);
        } catch (DocumentException e) {
            logger.error("无法读取配置文件，检查路径是否正确", e);
            e.printStackTrace();
        }

        Node node = doc.selectSingleNode("deploy-app");
        initInstance(node);
    }

    /**
     * @param node
     */
    @Override
    public void initInstance(Node node) {
        Node appSrc = node.selectSingleNode("app-src");
        setAppSrc(appSrc.getText());
        Node appIndex = node.selectSingleNode("app-index");
        setAppIndex(appIndex.getText());
    }
}
