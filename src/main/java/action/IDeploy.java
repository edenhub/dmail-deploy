package action;

import conf.subconf.DeployConfApp;
import conf.subconf.DeployConfAppServer;
import conf.subconf.DeployConfDB;
import conf.subconf.DeployConfMailServer;

/**
 * Created by lab on 2015/1/3.
 */
public interface IDeploy {

    public boolean deployDB(DeployConfDB deployConfDB);

    public boolean deployMailServer(DeployConfMailServer deployConfMailServer);

    public boolean deployAppServer(DeployConfAppServer deployConfAppServer);

    public boolean deployApp(DeployConfApp deployConfApp);
}
