package pri.adam.dmail.deploy.cli;

import org.apache.commons.cli.*;
import org.apache.log4j.Logger;
import pri.adam.dmail.deploy.action.DefDeploy;
import pri.adam.dmail.deploy.conf.DeployConf;
import pri.adam.dmail.deploy.conf.subconf.DeployConfAppServer;
import pri.adam.dmail.deploy.conf.subconf.DeployConfDB;
import pri.adam.dmail.deploy.conf.subconf.DeployConfMailServer;

/**
 * Created by lab on 2015/1/3.
 * 命令行窗口
 *
 * @auth adam
 * @sicne 1.0-SNAPSHOT
 */
public class CLIViewer {
    private DefDeploy deployer = new DefDeploy();
    private static Logger logger = Logger.getLogger(CLIViewer.class);

    /**
     * 主方法
     *
     * @param args
     */
    public static void main(String[] args) {
        new CLIViewer().buildOptions(args);
    }

    /**
     * 命令行信息
     *
     * @param args
     */
    public void buildOptions(String[] args) {
        Options options = new Options();
        options.addOption("a", "deploy-all", false, "直接一键部署全部");
        options.addOption("d", "deploy-db", false, "部署数据库");
        options.addOption("m", "deploy-mail-server", false, "部署邮件服务器");
        options.addOption("c", "deploy-container", false, "部署容器");
        options.addOption("h", "help", false, "帮助");

        String formatstr = "yDeploy [-d][-m][-c] fileDirectory";

        HelpFormatter helpFormatter = new HelpFormatter();
        CommandLineParser parser = new PosixParser();
        CommandLine cli = null;

        try {
            cli = parser.parse(options, args);
        } catch (ParseException e) {
            helpFormatter.printHelp(formatstr, options);
        }

        if (cli.hasOption("a")) {
//            String value = cli.getOptionValue("a");
            DeployConf deployConf = new DeployConf();
//            if (value.equals(".")) {
            deployConf.initInstance(CLIViewer.class.getResourceAsStream("/deploy_all.xml"));
//            } else {
//                deployConf.initInstance(value);
//            }
            boolean res = deployer.deployAll(deployConf);
            if (!res) {
                logger.info("无法部署，请检查配置文件");
            } else {
                logger.info("部署完毕");
            }
            return;
        } else if (cli.hasOption("d")) {
//            String value = cli.getOptionValue("d");
            DeployConfDB deployConfDB = new DeployConfDB();
//            if (value.equals(".")){
            deployConfDB.initInstance(CLIViewer.class.getResourceAsStream("/deploy_db.xml"));
//            }else{
//                deployConfDB.initInstance(value);
//            }

            boolean res = deployer.deployDB(deployConfDB);

            if (!res) {
                logger.info("无法部署邮件服务器，请检查配置文件");
            } else {
                logger.info("部署成功");
            }
        } else if (cli.hasOption("m")) {
//            String value = cli.getOptionValue("m");
            DeployConfMailServer deployConfMailServer = new DeployConfMailServer();
//            if (value.equals(".")) {
            deployConfMailServer.initInstance(CLIViewer.class.getResourceAsStream("/deploy_mail.xml"));
//            } else {
//                deployConfMailServer.initInstance(value);
//            }
            boolean res = deployer.deployMailServer(deployConfMailServer);
            if (!res) {
                logger.info("无法部署邮件服务器，请检查配置文件");
            } else {
                logger.info("部署成功");
            }
        } else if (cli.hasOption("c")) {
//            String value = cli.getOptionValue("c");
            DeployConfAppServer deployConfAppServer = new DeployConfAppServer();
//            if (value.equals(".")){
            deployConfAppServer.initInstance(CLIViewer.class.getResourceAsStream("/deploy_app.xml"));
//            }else{
//                deployConfAppServer.initInstance(value);
//            }

            boolean res = deployer.deployAppServer(deployConfAppServer);
            if (!res) {
                logger.info("无法部署邮件服务器，请检查配置文件");
            } else {
                logger.info("部署成功");
            }
        } else if (cli.hasOption("h")) {
            helpFormatter.printHelp(formatstr, options);
        } else {
            helpFormatter.printHelp(formatstr, options);
        }
    }
}
