package conf;

import conf.subconf.DeployDB;
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
        DeployDB deployDB = new DeployDB();
        deployDB.initInstance(in);
        System.out.println(deployDB);
    }
}
