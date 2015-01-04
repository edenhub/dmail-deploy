package pri.adam.dmail.deploy;

import org.junit.Test;
import pri.adam.dmail.deploy.action.DefDeploy;
import pri.adam.dmail.deploy.conf.DeployConf;
import pri.adam.dmail.deploy.utils.Version;

/**
 * Created by lab on 2015/1/4.
 */
public class TestCompiler {

//    @Test
//    public void test01() throws MethodNotSupportedException {
//        DeployConf deployConf = new DeployConf();
//        deployConf.initInstance(Version.class.getResourceAsStream("/deploy_all.xml"));
//        DefDeploy defDeploy = new DefDeploy();
//        defDeploy.deployApp(deployConf.getDeployApp());
//    }

//    @Test
//    public void test02(){
//        DeployConf deployConf = new DeployConf();
//        deployConf.initInstance(Version.class.getResourceAsStream("/deploy_all.xml"));
//        DefDeploy defDeploy = new DefDeploy();
//        defDeploy.deployDB(deployConf.getDeployDB());
//    }

    @Test
    public void test03(){
        DeployConf deployConf = new DeployConf();
        deployConf.initInstance(Version.class.getResourceAsStream("/deploy_all.xml"));
        DefDeploy defDeploy = new DefDeploy();
        defDeploy.deployMailServer(deployConf.getDeployMailServer());
    }

    @Test
    public void test04(){
        DeployConf deployConf = new DeployConf();
        deployConf.initInstance(Version.class.getResourceAsStream("/deploy_all.xml"));
        DefDeploy defDeploy = new DefDeploy();
        defDeploy.deployAppServer(deployConf.getDeployAppServer());
    }
}
