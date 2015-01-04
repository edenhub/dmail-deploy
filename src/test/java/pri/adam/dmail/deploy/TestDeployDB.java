package pri.adam.dmail.deploy;

import org.junit.Before;
import org.junit.Test;
import pri.adam.dmail.deploy.conf.subconf.DeployConfDB;
import pri.adam.dmail.deploy.utils.Version;

import java.io.InputStream;

/**
 * Created by lab on 2015/1/3.
 */
public class TestDeployDB {
    private InputStream in;

    @Before
    public void before() {
        in = Version.class.getResourceAsStream("/deploy-db.xml");
    }

//    @Test
//    public void test01(){
//        DeployConfDB deployDB = new DeployConfDB();
//        deployDB.initInstance(in);
//        System.out.println(deployDB);
//    }
}
