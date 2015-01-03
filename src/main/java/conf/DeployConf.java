package conf;

import conf.subconf.DeployApp;
import conf.subconf.DeployAppServer;
import conf.subconf.DeployDB;
import conf.subconf.DeployMailServer;
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
public class DeployConf implements IDeploy{
    private DeployDB deployDB;
    private DeployMailServer deployMailServer;
    private DeployAppServer deployAppServer;
    private DeployApp deployApp;

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

    public DeployDB getDeployDB() {
        return deployDB;
    }

    public DeployMailServer getDeployMailServer() {
        return deployMailServer;
    }

    public DeployAppServer getDeployAppServer() {
        return deployAppServer;
    }

    public DeployApp getDeployApp() {
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
        deployDB = new DeployDB();
        deployDB.initInstance(deployDBNode);

        Node deployMailServerNode = node.selectSingleNode("deploy-mail-server");
        deployMailServer = new DeployMailServer();
        deployMailServer.initInstance(deployMailServerNode);

        Node deployAppServerNode = node.selectSingleNode("deploy-app-server");
        deployAppServer = new DeployAppServer();
        deployAppServer.initInstance(deployAppServerNode);

        Node deployAppNode = node.selectSingleNode("deploy-app");
        deployApp = new DeployApp();
        deployApp.initInstance(deployAppNode);
    }
}
