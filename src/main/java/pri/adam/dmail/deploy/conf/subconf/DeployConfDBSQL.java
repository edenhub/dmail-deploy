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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lab on 2015/1/3.
 * <p/>
 * 数据库执行语句配置信息对象
 *
 * @auth adam
 * @sicne 1.0-SNAPSHOT
 */
public class DeployConfDBSQL implements IDeployConf {
    private List<TablePair> tablePairs;
    private static Logger logger = Logger.getLogger(DeployConfDBSQL.class);

    /*
   *
   *  =======================================================================================
   *                                  Getter And Setter
   *  =======================================================================================
   *
   */

    public DeployConfDBSQL() {
        tablePairs = new ArrayList<TablePair>();
    }

    public DeployConfDBSQL(int capabilty) {
        tablePairs = new ArrayList<TablePair>(capabilty);
    }

    public TablePair createTablePair(String tableName, String tableSql) {
        return new TablePair(tableName, tableSql);
    }

    public TablePair createTablePair() {
        return new TablePair();
    }

    public void addTable(TablePair tablePair) {
        tablePairs.add(tablePair);
    }

    public List<TablePair> getTablePairs() {
        return tablePairs;
    }

    /*
   *
   *  =======================================================================================
   *                                  Getter And Setter
   *  =======================================================================================
   *
   */

    /**
     * @param path
     */
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
    public void initInstance(InputStream inputStream) {
        SAXReader reader = new SAXReader();
        Document doc = null;
        try {
            doc = reader.read(inputStream);
        } catch (DocumentException e) {
            logger.error("无法读取配置文件，检查路径是否正确", e);
            e.printStackTrace();
        }

        Node node = doc.selectSingleNode("db-sql");
        initInstance(node);
    }

    /**
     * @param node
     */
    public void initInstance(Node node) {
        List<Node> tableNodes = node.selectNodes("table");

        for (Node n : tableNodes) {
            TablePair tablePair = createTablePair();
            Node tableName = n.selectSingleNode("table-name");
            tablePair.setTableName(tableName.getText());
            Node tableSql = n.selectSingleNode("table-sql");
            tablePair.setTableSql(tableSql.getText());
            tablePairs.add(tablePair);
        }
    }

    @Override
    public String toString() {
        return "DeployDBSQL{" +
                "tablePairs=" + tablePairs +
                '}';
    }

    /**
     * 数据库结构对象
     */
    public class TablePair {
        private String tableName;
        private String tableSql;

        public TablePair() {
        }

        public TablePair(String tableName, String tableSql) {
            this.tableName = tableName;
            this.tableSql = tableSql;
        }

        /*
   *
   *  =======================================================================================
   *                                  Getter And Setter
   *  =======================================================================================
   *
   */
        public String getTableName() {
            return tableName;
        }

        public void setTableName(String tableName) {
            this.tableName = tableName;
        }

        public String getTableSql() {
            return tableSql;
        }

        public void setTableSql(String tableSql) {
            this.tableSql = tableSql;
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
            return "TablePair{" +
                    "tableName='" + tableName + '\'' +
                    ", tableSql='" + tableSql + '\'' +
                    '}';
        }
    }
}
