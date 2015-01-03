package conf;

import org.dom4j.Node;

import java.io.InputStream;

/**
 * Created by lab on 2015/1/3.
 */
public interface IDeploy {
    public void initInstance(String path);

    public void initInstance(InputStream inputStream);

    public void initInstance(Node node);
}
