package conf.subconf;


import conf.IDeploy;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import utils.Version;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by lab on 2015/1/3.
 */
public class DeployDB implements IDeploy {
    private DeployDBSQL deployDBSQL;
    private String dbName;
    private String dbUrl;
    private String dbUser;
    private String dbPassword;
    private static Logger logger = Logger.getLogger(DeployDB.class);

    public DeployDBSQL getDeployDBSQL() {
        return deployDBSQL;
    }

    public void setDeployDBSQL(DeployDBSQL deployDBSQL) {
        this.deployDBSQL = deployDBSQL;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public String getDbUser() {
        return dbUser;
    }

    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    @Override
    public String toString() {
        return "DeployDB{" +
                "deployDBSQL=" + deployDBSQL +
                ", dbName='" + dbName + '\'' +
                ", dbUrl='" + dbUrl + '\'' +
                ", dbUser='" + dbUser + '\'' +
                ", dbPassword='" + dbPassword + '\'' +
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

        Node node = doc.selectSingleNode("deploy-db");
        initInstance(node);
    }

    @Override
    public void initInstance(Node node) {
        Node sqlFile = node.selectSingleNode("sql-file");
        String path = sqlFile.getText();
        InputStream in = null;
        deployDBSQL = new DeployDBSQL();
        if (path.startsWith("./")){
            in = Version.class.getResourceAsStream(path.substring(path.indexOf("/")));
            deployDBSQL.initInstance(in);
        }else{
            deployDBSQL.initInstance(path);
        }

        Node dbName = node.selectSingleNode("db-name");
        setDbName(dbName.getText());

        Node dbUrl = node.selectSingleNode("db-url");
        setDbUrl(dbUrl.getText());

        Node dbUser = node.selectSingleNode("db-user");
        setDbUser(dbUser.getText());

        Node dbPassword = node.selectSingleNode("db-password");
        setDbPassword(dbPassword.getText());
    }
}
