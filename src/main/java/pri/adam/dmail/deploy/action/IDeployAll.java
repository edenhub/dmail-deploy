package pri.adam.dmail.deploy.action;

import pri.adam.dmail.deploy.conf.DeployConf;

/**
 * Created by lab on 2015/1/4.
 * <p/>
 * 一键部署
 *
 * @auth adam
 * @sicne 1.0-SNAPSHOT
 */
public interface IDeployAll {

    /**
     * 一键部署
     *
     * @param deployConf 总配置文件信息对象
     * @return 部署成功返回true 否则返回false
     */
    public boolean deployAll(final DeployConf deployConf);
}
