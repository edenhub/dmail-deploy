package pri.adam.dmail.deploy.action;

import pri.adam.dmail.deploy.conf.subconf.DeployConfApp;
import pri.adam.dmail.deploy.conf.subconf.DeployConfAppServer;
import pri.adam.dmail.deploy.conf.subconf.DeployConfDB;
import pri.adam.dmail.deploy.conf.subconf.DeployConfMailServer;

import javax.mail.MethodNotSupportedException;

/**
 * Created by lab on 2015/1/3.
 */
public interface IDeploy {

    public boolean deployDB(final DeployConfDB deployConfDB);

    public boolean deployMailServer(final DeployConfMailServer deployConfMailServer);

    public boolean deployAppServer(final DeployConfAppServer deployConfAppServer);

    public boolean deployApp(final DeployConfApp deployConfApp) throws MethodNotSupportedException;
}
