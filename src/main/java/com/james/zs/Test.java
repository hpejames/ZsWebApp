package com.james.zs;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 业务异常
 *
 * @author james.gao
 * @version v1.0
 * @description TODO
 * @create date 8:12 2017/11/9
 * @modified by james.gao
 * @modify date 8:12 2017/11/9
 */
public class Test {

    public static void main (String[] args) {
        ConcurrentLinkedQueue<String> concurrentLinkedQueue = new ConcurrentLinkedQueue<String>();
        concurrentLinkedQueue.add("1");
        concurrentLinkedQueue.add("2");
        concurrentLinkedQueue.add("3");
        concurrentLinkedQueue.add("4");
        concurrentLinkedQueue.add("5");
        System.out.println(concurrentLinkedQueue.size() + "");
        System.out.println(concurrentLinkedQueue.poll());
        System.out.println(concurrentLinkedQueue.poll());
        System.out.println(concurrentLinkedQueue.poll());
        System.out.println(concurrentLinkedQueue.poll());
        System.out.println(concurrentLinkedQueue.poll());
        System.out.println(concurrentLinkedQueue.poll());
        System.out.println(concurrentLinkedQueue.poll());
        System.out.println(concurrentLinkedQueue.size() + "");
    }
}
