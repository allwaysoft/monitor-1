package com.dennis.api.async;

import com.dennis.api.ssh.SSHTemplate;
import com.dennis.api.websocket.WebSocket;
import com.dennis.dao.entity.App;
import com.dennis.dao.entity.Server;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Created by Dennis on 2019/5/12.
 */


@Component
public class SSHAsync {


    /**
     * 执行 日志监控 命令
     *
     * @param app
     * @param server
     * @param webSocket
     */
    @Async
    public void execTail(App app, Server server, WebSocket webSocket) {

        String command = "tail -f " + app.getPath();
        System.out.println(Thread.currentThread().getId());

        SSHTemplate.executePrint(server, command, webSocket, String.valueOf(app.getPkId()));

        System.out.println("==== SSH Close ====");
        Thread.currentThread().interrupt();

    }


}
