package conf;

import conf.subconf.DeployConfDB;
import org.junit.Before;
import org.junit.Test;
import utils.Version;

import java.io.InputStream;

/**
 * Created by lab on 2015/1/3.
 */
public class TestDeployDB {
    private InputStream in;

    @Before
    public void before(){
        in = Version.class.getResourceAsStream("/deploy-db.xml");
    }

    @Test
    public void test01(){
        DeployConfDB deployDB = new DeployConfDB();
        deployDB.initInstance(in);
        System.out.println(deployDB);
    }
}
