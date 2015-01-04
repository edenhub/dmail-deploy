package pri.adam.dmail.deploy.action;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import pri.adam.dmail.deploy.conf.DeployConf;
import pri.adam.dmail.deploy.conf.subconf.*;
import pri.adam.dmail.utils.dbutil.DBManager;
import pri.adam.dmail.utils.dbutil.DBToolkit;
import pri.adam.dmail.utils.dbutil.StatementTemplate;

import javax.mail.MethodNotSupportedException;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

/**
 * Created by lab on 2015/1/4.
 * <p/>
 * 默认的部署接口实现
 *
 * @auth adam
 * @sicne 1.0-SNAPSHOT
 */
public class DefDeploy implements IDeploy, IDeployAll {
    private static Logger logger = Logger.getLogger(DefDeploy.class);

    /**
     * @param deployConfDB 数据库配置信息文件对象
     * @return
     */
    @Override
    public boolean deployDB(final DeployConfDB deployConfDB) {
        Properties dbPros = new Properties();
        dbPros.setProperty("db.driverName", selectDriverName(deployConfDB.getDbType()));
        dbPros.setProperty("db.Url", deployConfDB.getDbUrl());
        dbPros.setProperty("db.username", deployConfDB.getDbUser());
        dbPros.setProperty("db.password", deployConfDB.getDbPassword());

        DBManager dbManager = new DBManager();
        dbManager.conn(dbPros);

        StatementTemplate<Boolean> createTables =
                new StatementTemplate<Boolean>(dbManager.getConnection());

        Object resultObject = createTables.executeStmSQL(createTables.new StatmentExecutor() {
            @Override
            public Boolean execute(Statement stm) throws SQLException {
                DeployConfDBSQL deployConfDBSQL = deployConfDB.getDeployDBSQL();
                List<DeployConfDBSQL.TablePair> tablePairs = deployConfDBSQL.getTablePairs();

                for (DeployConfDBSQL.TablePair tp : tablePairs) {
                    stm.addBatch(tp.getTableSql());
                }

                int res[] = stm.executeBatch();
                if (res.length != tablePairs.size())
                    return false;

                return true;
            }
        });

        boolean createResult = resultObject != null ? true : false;

        if (!createResult) {
            logger.error("检查配置信息是否有错");
        }

        try {
            DBToolkit.closeDBConnection(dbManager.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return createResult;
    }

    /**
     * @param deployConfMailServer 邮件服务器配置信息文件对象
     * @return
     */
    @Override
    public boolean deployMailServer(final DeployConfMailServer deployConfMailServer) {
        File serverPath = new File(deployConfMailServer.getMailServerDest());
        if (!serverPath.exists()) {
            File serverSrc = new File(getDefaultPath() + "/james2");
            try {
                FileUtils.copyDirectory(serverSrc, serverPath);
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

        return true;
    }

    /**
     * @param deployConfAppServer 服务器配置信息文件对象
     * @return
     */
    @Override
    public boolean deployAppServer(final DeployConfAppServer deployConfAppServer) {
        File serverPath = new File(deployConfAppServer.getAppServerDest());
        if (!serverPath.exists()) {
            File serverSrc = new File(getDefaultPath() + "/tomcat7");
            try {
                FileUtils.copyDirectory(serverSrc, serverPath);
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

        return true;
    }

    /**
     * @param deployConfApp
     * @return
     * @throws MethodNotSupportedException
     */
    @Override
    public boolean deployApp(final DeployConfApp deployConfApp) throws MethodNotSupportedException {
        throw new MethodNotSupportedException("未实现");
    }

    /**
     * @param deployConf 总配置文件信息对象
     * @return
     */
    @Override
    public boolean deployAll(final DeployConf deployConf) {
        if (deployDB(deployConf.getDeployDB()) &&
                deployMailServer(deployConf.getDeployMailServer()) &&
                deployAppServer(deployConf.getDeployAppServer()))
            return true;

        return false;
    }

    /**
     * 选择所指定数据库类型的驱动类
     *
     * @param dbType
     * @return
     */
    private String selectDriverName(final DeployConfDB.DBType dbType) {
        switch (dbType) {
            case MYSQL:
                return "com.mysql.jdbc.Driver";
            case ORACLE:
                return "";
        }

        throw new RuntimeException("不支持该种数据库");
    }

    /**
     * 获得当前jar所在文件夹
     *
     * @return
     */
    public String getDefaultPath() {
        String jarPath = DefDeploy.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        int startIndex = jarPath.indexOf("/");
        int endIndex = jarPath.lastIndexOf("/");
        String folderPath = jarPath.substring(startIndex + 1, endIndex);

        return folderPath;
    }
}
