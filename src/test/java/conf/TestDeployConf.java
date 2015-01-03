package conf;

import org.junit.Test;
import utils.Version;

import java.io.InputStream;

/**
 * Created by lab on 2015/1/3.
 */
public class TestDeployConf {
    @Test
    public void test01(){
        InputStream in = Version.class.getResourceAsStream("/deploy_all.xml");
        DeployConfConf deployConf = new DeployConfConf();
        deployConf.initInstance(in);

        System.out.println(deployConf);
    }
}
