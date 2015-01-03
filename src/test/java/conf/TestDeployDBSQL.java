package conf;

import conf.subconf.DeployDBSQL;
import org.junit.Before;
import org.junit.Test;
import utils.Version;

import java.io.InputStream;

/**
 * Created by lab on 2015/1/3.
 */
public class TestDeployDBSQL {
    private InputStream in = null;

    @Before
    public void before(){
        in = Version.class.getResourceAsStream("/mysql.xml");
    }

    @Test
    public void test01(){
        DeployDBSQL deployDBSQL = new DeployDBSQL();
        deployDBSQL.initInstance(in);
        System.out.println(deployDBSQL);
    }
}
