package com.dennis.api.controller;

import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.KafkaFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutionException;

/**
 * Created by Dennis on 2019/3/25.
 */

@RestController
@RequestMapping("/kafka")
public class ProducerController {


    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    /**
     * 发送消息
     * @param msg
     * @return
     */
    @RequestMapping(value = "/send.action")
    public String send(String msg){

        kafkaTemplate.send("flume", msg);

        return "success";
    }

    /**
     * 创建主题
     * @param topic
     * @return
     */
    @RequestMapping(value = "/create.action")
    public String create(String topic){

        Properties prop = new Properties();
        prop.put("bootstrap.servers","47.101.202.233:9092");

        AdminClient admin = AdminClient.create(prop);

        ArrayList<NewTopic> topics = new ArrayList<NewTopic>();
        // 创建主题  参数：主题名称、分区数、副本数
        NewTopic newTopic = new NewTopic(topic, 1, (short)1);
        topics.add(newTopic);

        CreateTopicsResult result = admin.createTopics(topics);

        try {
            result.all().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        return "success";
    }

    /**
     * 主题列表
     * @return
     */
    @RequestMapping(value = "/topics.action")
    public String topics(){

        Properties prop = new Properties();
        prop.put("bootstrap.servers","47.101.202.233:9092");
        AdminClient admin = AdminClient.create(prop);

        ListTopicsResult result = admin.listTopics();
        KafkaFuture<Set<String>> future = result.names();

        try {
            System.out.println("==================Kafka Topics====================");
            future.get().forEach(n -> System.out.println(n));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return "success";
    }

    /**
     * 删除主题
     * @param topic
     * @return
     */
    @RequestMapping(value = "/delete.action")
    public String delete(String topic){

        Properties prop = new Properties();
        prop.put("bootstrap.servers","47.101.202.233:9092");
        AdminClient client = AdminClient.create(prop);

        ArrayList<String> topics = new ArrayList<>();
        topics.add(topic);

        DeleteTopicsResult result = client.deleteTopics(topics);

        try {
            result.all().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        return "success";
    }


}
