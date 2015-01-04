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
 */
public class DeployConfAppServer implements IDeployConf {
    private String appServerSrc;
    private String appServerDest;

    private Logger logger = Logger.getLogger(DeployConfAppServer.class);

    public String getAppServerSrc() {
        return appServerSrc;
    }

    public void setAppServerSrc(String appServerSrc) {
        this.appServerSrc = appServerSrc;
    }

    public String getAppServerDest() {
        return appServerDest;
    }

    public void setAppServerDest(String appServerDest) {
        this.appServerDest = appServerDest;
    }

    @Override
    public String toString() {
        return "DeployAppServer{" +
                "appServerSrc='" + appServerSrc + '\'' +
                ", appServerDest='" + appServerDest + '\'' +
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

        Node node = doc.selectSingleNode("deploy-app-server");
        initInstance(node);
    }

    @Override
    public void initInstance(Node node) {
        Node appServerSrc = node.selectSingleNode("app-server-src");
        setAppServerSrc(appServerSrc.getText());
        Node appServerDest = node.selectSingleNode("app-server-dest");
        setAppServerDest(appServerDest.getText());
    }
}
