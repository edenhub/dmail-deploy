package pri.adam.dmail.deploy.conf;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import pri.adam.dmail.deploy.conf.subconf.DeployConfApp;
import pri.adam.dmail.deploy.conf.subconf.DeployConfAppServer;
import pri.adam.dmail.deploy.conf.subconf.DeployConfDB;
import pri.adam.dmail.deploy.conf.subconf.DeployConfMailServer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by lab on 2015/1/3.
 */
public class DeployConf implements IDeployConf {
    private DeployConfDB deployDB;
    private DeployConfMailServer deployMailServer;
    private DeployConfAppServer deployAppServer;
    private DeployConfApp deployApp;

    private static Logger logger = Logger.getLogger(DeployConf.class);

    @Override
    public String toString() {
        return "DeployConf{" +
                "deployDB=" + deployDB +
                ", deployMailServer=" + deployMailServer +
                ", deployAppServer=" + deployAppServer +
                ", deployApp=" + deployApp +
                '}';
    }

    public DeployConfDB getDeployDB() {
        return deployDB;
    }

    public DeployConfMailServer getDeployMailServer() {
        return deployMailServer;
    }

    public DeployConfAppServer getDeployAppServer() {
        return deployAppServer;
    }

    public DeployConfApp getDeployApp() {
        return deployApp;
    }

    public static Logger getLogger() {
        return logger;
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

        Node node = doc.selectSingleNode("deploy");
        initInstance(node);
    }

    @Override
    public void initInstance(Node node) {
        Node deployDBNode = node.selectSingleNode("deploy-db");
        deployDB = new DeployConfDB();
        deployDB.initInstance(deployDBNode);

        Node deployMailServerNode = node.selectSingleNode("deploy-mail-server");
        deployMailServer = new DeployConfMailServer();
        deployMailServer.initInstance(deployMailServerNode);

        Node deployAppServerNode = node.selectSingleNode("deploy-app-server");
        deployAppServer = new DeployConfAppServer();
        deployAppServer.initInstance(deployAppServerNode);

        Node deployAppNode = node.selectSingleNode("deploy-app");
        deployApp = new DeployConfApp();
        deployApp.initInstance(deployAppNode);
    }
}
