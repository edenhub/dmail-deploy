package pri.adam.dmail.deploy.conf.subconf;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import pri.adam.dmail.deploy.conf.IDeployConf;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by lab on 2015/1/3.
 * <p/>
 * 邮件服务器配置信息对象
 *
 * @auth adam
 * @sicne 1.0-SNAPSHOT
 */
public class DeployConfMailServer implements IDeployConf {
    private String mailServerSrc;
    private String mailServerDest;

    private static Logger logger = Logger.getLogger(DeployConfMailServer.class);

    /*
   *
   *  =======================================================================================
   *                                  Getter And Setter
   *  =======================================================================================
   *
   */

    public String getMailServerSrc() {
        return mailServerSrc;
    }

    public void setMailServerSrc(String mailServerSrc) {
        this.mailServerSrc = mailServerSrc;
    }

    public String getMailServerDest() {
        return mailServerDest;
    }

    public void setMailServerDest(String mailServerDest) {
        this.mailServerDest = mailServerDest;
    }

    /*
   *
   *  =======================================================================================
   *                                  Getter And Setter
   *  =======================================================================================
   *
   */

    @Override
    public String toString() {
        return "DeployMailServer{" +
                "mailServerSrc='" + mailServerSrc + '\'' +
                ", mailServerDest='" + mailServerDest + '\'' +
                '}';
    }

    /**
     * @param path
     */
    @Override
    public void initInstance(String path) {
        InputStream in = null;
        try {
            in = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            logger.error("无法读取配置文件，检查路径是否正确", e);
            e.printStackTrace();
        }
    }

    /**
     * @param inputStream
     */
    @Override
    public void initInstance(InputStream inputStream) {
        SAXReader reader = new SAXReader();
        Document doc = null;
        try {
            doc = reader.read(inputStream);
        } catch (DocumentException e) {
            logger.error("无法读取配置文件，检查路径是否正确", e);
            e.printStackTrace();
        }

        Node node = doc.selectSingleNode("deploy-mail-server");
        initInstance(node);
    }

    /**
     * @param node
     */
    @Override
    public void initInstance(Node node) {
        Node mailServerSrc = node.selectSingleNode("mail-server-src");
        setMailServerSrc(mailServerSrc.getText());
        Node mailServerDest = node.selectSingleNode("mail-server-dest");
        setMailServerDest(mailServerDest.getText());
    }
}
