package com.dennis.api;

import com.dennis.api.ssh.SSHTemplate;
import com.dennis.common.constants.CommandConstant;
import com.dennis.dao.entity.Server;

/**
 * Created by Dennis on 2019/5/9.
 */


public class Test {

    public static void main(String[] args) {


        Server server = new Server();
        server.setHost("47.106.33.241");
        server.setUsername("root");
        server.setPassword("dxs970103+.");
        server.setPort("22");


    }
}
