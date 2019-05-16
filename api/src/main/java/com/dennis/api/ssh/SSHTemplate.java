package com.dennis.api.ssh;

import com.dennis.api.websocket.WebSocket;
import com.dennis.common.constants.CommandConstant;
import com.dennis.common.tools.MapUtil;
import com.dennis.dao.entity.Server;
import com.jcraft.jsch.*;

import java.io.*;
import java.util.*;

/**
 * Created by Dennis on 2019/4/3.
 */


public class SSHTemplate {

    public static final String LINE_SEPARATOR = System.getProperty("line.separator");


    /**
     * 获取 连接
     *
     * @param server
     * @return
     */
    private static Session getSession(Server server) {

        JSch jSch = new JSch();
        Session session = null;
        try {
            session = jSch.getSession(server.getUsername(), server.getHost(), Integer.valueOf(server.getPort()));
            session.setPassword(server.getPassword());
            Properties properties = new Properties();
            properties.setProperty("StrictHostKeyChecking", "no");
            session.setConfig(properties);

            session.connect();

        } catch (JSchException e) {
            return null;
        }

        return session;
    }

    /**
     * 获取 SFTP 文件上传/下载 通道
     *
     * @param session
     * @return
     * @throws JSchException
     */
    private static ChannelSftp openChannelSftp(Session session) throws JSchException {
        ChannelSftp channelSftp = (ChannelSftp) session.openChannel("sftp");
        channelSftp.connect();
        return channelSftp;
    }


    /**
     * 关闭 Channel、Session
     *
     * @param session
     * @param channel
     */
    private static void close(Session session, Channel channel) {
        if (channel != null)
            channel.disconnect();
        if (session != null)
            session.disconnect();
    }



    public static boolean testConnect(Map params) {
        JSch jSch = new JSch();
        Session session = null;

        try {

            session = jSch.getSession(MapUtil.getString(params, "username"), MapUtil.getString(params, "host"), MapUtil.getInt(params, "port"));
            session.setPassword(MapUtil.getString(params, "password"));
            Properties properties = new Properties();
            properties.setProperty("StrictHostKeyChecking", "no");
            session.setConfig(properties);

            session.connect();

        } catch (JSchException e) {
            return false;
        }

        // 关闭连接
        session.disconnect();
        ;

        return true;
    }


    /**
     * 执行命令
     *
     * @param server
     * @param command
     * @return
     */
    public static String exec(Server server, String command) {

        Session session = getSession(server);

        ChannelExec exec = null;
        StringBuilder builder = new StringBuilder();

        try {

            exec = (ChannelExec) session.openChannel("exec");
            InputStream in = exec.getInputStream();
            exec.setCommand(command);
            exec.connect();

            byte[] tmp = new byte[1024];
            while (true) {

                while (in.available() > 0) {
                    int i = in.read(tmp, 0, 1024);
                    if (i < 0)
                        break;
                    String str = new String(tmp, 0, i);
                    builder.append(str).append(LINE_SEPARATOR);
                }
                if (exec.isClosed()) {
                    if (in.available() > 0)
                        continue;
                    break;
                }
            }

        } catch (JSchException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(session,exec);
        }

        return builder.toString();
    }

    /**
     * 执行命令
     *
     * @param server
     * @param command
     * @return
     */
    public static void executePrint(Server server, String command, WebSocket webSocket, String code) {

        Session session = getSession(server);

        ChannelExec exec = null;
        StringBuilder builder = new StringBuilder();

        try {

            ChannelExec channel =  (ChannelExec) session.openChannel("exec");
            InputStream in = channel.getInputStream();
            channel.setCommand(command);
            channel.connect();

            byte[] tmp = new byte[1024];
            while (true) {
                while (in.available() > 0) {
                    int i = in.read(tmp, 0, 1024);
                    if (i < 0)
                        break;

                    String line = new String(tmp, 0, i);
                    line = line.replaceAll("\\n","<br/>");

                    // 判断 socket 连接是否还存在， 如果不存在，关闭 ssh 连接。
                    if (webSocket.isExists(code)){
                        webSocket.sendOneMessage(code,line);
                    }else {
                        close(session,exec);
                    }
                }
                if (channel.isClosed()) {
                    if (in.available() > 0)
                        continue;
                    break;
                }
            }
        } catch (JSchException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("========== Tail SSH Session Close ==========");
            close(session,exec);
        }
    }


    /**
     * 批量执行命令
     *
     * @param server
     * @param commands
     * @return
     */
    public static Map<String, String> batchExeC(Server server, List<String> commands) {

        Session session = getSession(server);
        Map<String, String> map = new HashMap<>();
        StringBuilder stringBuffer;

        BufferedReader reader = null;
        ChannelExec channel = null;
        try {
            for (String command : commands) {
                stringBuffer = new StringBuilder();

                channel = (ChannelExec) session.openChannel("exec");
                channel.setCommand(command);

                InputStream in = channel.getInputStream();
                channel.connect();

                reader = new BufferedReader(new InputStreamReader(in));
                String buf;
                while ((buf = reader.readLine()) != null) {

                    System.out.println(buf);
                    stringBuffer.append(buf.trim()).append(LINE_SEPARATOR);

                }
                //每个命令存储自己返回数据-用于后续对返回数据进行处理
                map.put(command, stringBuffer.toString());
            }
        } catch (IOException | JSchException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            close(session, channel);
        }
        return map;
    }


    /**
     * 上传文件 sftp
     *
     * @param server
     * @param localFile
     * @param remotePath
     * @return
     */
    public static boolean upload(Server server, String localFile, String remotePath, String fileName) {

        Session session = getSession(server);
        ChannelSftp channelSftp = null;
        try {
            channelSftp = openChannelSftp(session);

            if (!isExist(channelSftp))
                channelSftp.mkdir(remotePath);

            channelSftp.put(localFile, remotePath,ChannelSftp.OVERWRITE);

            if (fileName != null)
                channelSftp.chmod(777, remotePath + fileName);


        } catch (JSchException | SftpException e) {
            e.printStackTrace();
        } finally {
            if (channelSftp != null)
                channelSftp.disconnect();
            if (session != null)
                session.disconnect();
        }
        return true;
    }


    /**
     * 获取文件列表
     * @param server
     * @param remotePath
     * @return
     * @throws Exception
     */
    public static Vector listFiles(Server server, String remotePath) {

        Session session = getSession(server);
        ChannelSftp channelSftp = null;
        Vector vector = null;
        try {
            channelSftp = openChannelSftp(session);
            vector = channelSftp.ls(remotePath);
            return vector;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(session, channelSftp);
        }
        return vector;
    }



    private static boolean isExist(ChannelSftp sftp){

        try {
            Vector<ChannelSftp.LsEntry> vector = sftp.ls("/opt");
            for (ChannelSftp.LsEntry it : vector){
                if (CommandConstant.MONITOR.equals(it.getFilename()))
                    return true;
            }
        } catch (SftpException e) {
            e.printStackTrace();
        }
        return false;
    }

}
