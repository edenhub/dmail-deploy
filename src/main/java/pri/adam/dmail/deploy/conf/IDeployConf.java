package pri.adam.dmail.deploy.conf;

import org.dom4j.Node;

import java.io.InputStream;

/**
 * Created by lab on 2015/1/3.
 * <p/>
 * 配置信息对象加载方法
 *
 * @auth adam
 * @sicne 1.0-SNAPSHOT
 */
public interface IDeployConf {
    /**
     * 从指定的路径中加载
     *
     * @param path
     */
    public void initInstance(String path);

    /**
     * 从指定的流对象中加载
     *
     * @param inputStream
     */
    public void initInstance(InputStream inputStream);

    /**
     * 从指定的Node对象中加载
     *
     * @param node
     */
    public void initInstance(Node node);
}
