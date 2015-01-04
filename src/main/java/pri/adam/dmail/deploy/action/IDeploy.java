package pri.adam.dmail.deploy.action;

import pri.adam.dmail.deploy.conf.subconf.DeployConfApp;
import pri.adam.dmail.deploy.conf.subconf.DeployConfAppServer;
import pri.adam.dmail.deploy.conf.subconf.DeployConfDB;
import pri.adam.dmail.deploy.conf.subconf.DeployConfMailServer;

import javax.mail.MethodNotSupportedException;

/**
 * Created by lab on 2015/1/3.
 * <p/>
 * 用于相关组件的部署
 *
 * @auth adam
 * @sicne 1.0-SNAPSHOT
 */
public interface IDeploy {

    /**
     * 部署数据库，创建需要的表
     *
     * @param deployConfDB 数据库配置信息文件对象
     * @return 创建成功返回true 否则返回false
     */
    public boolean deployDB(final DeployConfDB deployConfDB);

    /**
     * 部署邮件服务器
     *
     * @param deployConfMailServer 邮件服务器配置信息文件对象
     * @return 部署成功返回true 否则返回false
     */
    public boolean deployMailServer(final DeployConfMailServer deployConfMailServer);


    /**
     * 部署服务器
     *
     * @param deployConfAppServer 服务器配置信息文件对象
     * @return 部署成功返回true 否则返回false
     */
    public boolean deployAppServer(final DeployConfAppServer deployConfAppServer);

    /**
     * 未实现
     *
     * @param deployConfApp
     * @return
     * @throws MethodNotSupportedException
     */
    public boolean deployApp(final DeployConfApp deployConfApp) throws MethodNotSupportedException;
}
