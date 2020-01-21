package com.start.test.utils;

import java.net.InetAddress;
import java.util.Random;

/**
 在与银行的交互过程中，必须使用一个唯一的流水号来标识一次请求操作，不然业务操作会乱作一团，在对比了一些优秀的唯一 ID 生成算法后，最终选择使用 Twitter 的 Snowflake 算法来为我们提供银行存管中的流水号服务。
 此算法的好处有两点：一是纯数字，二是整体上来说是按时间顺序的。

 原理不作过多介绍，网络上的文章也很多，算法原版是 Scala 写的，网上也有 Java 版本，但是有 bug，会产生重复的 ID，无法用于生产环境的集群部署，我对其进行了修改，修改后，可用于生产环境，已稳定运行 8 个月，下面是完整核心代码：
 https://gist.github.com/xishuixixia/f0f8684805d0504289b7a40f3b327dd6

 需要注意的是，集群机器需关闭 NTP 的时间同步功能，至于原因，留给读者，看上面代码便知。

 *
 *   (a) id构成: 42位的时间前缀 + 10位的节点标识 + 12位的sequence避免并发的数字(12位不够用时强制得到新的时间前缀)
 *       注意这里进行了小改动: snowkflake是5位的datacenter加5位的机器id; 这里变成使用10位的机器id
 *   (b) 对系统时间的依赖性非常强，需关闭ntp的时间同步功能。当检测到ntp时间调整后，将会拒绝分配id
 */
public class IdWorker {

    private final long workerId;
    // 时间起始标记点，作为基准，一般取系统的最近时间
    private final long epoch = 1539773333019L;
    // 机器标识位数
    private final long workerIdBits = 10L;
    // 机器ID最大值: 1023
    private final long maxWorkerId = -1L ^ -1L << this.workerIdBits;
    // 0，并发控制
    private long sequence = 0L;
    //毫秒内自增位
    private final long sequenceBits = 12L;

    private final long workerIdShift = this.sequenceBits;
    private final long timestampLeftShift = this.sequenceBits + this.workerIdBits;
    private final long sequenceMask = -1L ^ -1L << this.sequenceBits;
    private long lastTimestamp = -1L;
    private IdWorker(long workerId) {
        if (workerId > this.maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", this.maxWorkerId));
        }
        this.workerId = workerId;
    }

    public synchronized long nextId() throws Exception {
        long timestamp = IdWorker.timeGen();
        // 如果上一个timestamp与新产生的相等，则sequence加一(0-4095循环); 对新的timestamp，sequence从0开始
        if (this.lastTimestamp == timestamp) {
            this.sequence = this.sequence + 1 & this.sequenceMask;
            if (this.sequence == 0) {
                // 重新生成timestamp
                timestamp = this.tilNextMillis(this.lastTimestamp);
            }
        } else {
            this.sequence = 0;
        }

        if (timestamp < this.lastTimestamp) {
            throw new Exception(String.format("clock moved backwards.Refusing to generate id for %d milliseconds", (this.lastTimestamp - timestamp)));
        }

        this.lastTimestamp = timestamp;
        return timestamp - this.epoch << this.timestampLeftShift | this.workerId << this.workerIdShift | this.sequence;
    }

    private static IdWorker flowIdWorker = new IdWorker(getworkerHostIp());
    public static IdWorker getFlowIdWorkerInstance() {
        return flowIdWorker;
    }
    /**
     * 等待下一个毫秒的到来, 保证返回的毫秒数在参数lastTimestamp之后
     */
    private long tilNextMillis(long lastTimestamp) {
        long timestamp = IdWorker.timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = IdWorker.timeGen();
        }
        return timestamp;
    }
    /**
     * 获得系统当前毫秒数
     */
    private static long timeGen() {
        return System.currentTimeMillis();
    }
    /**
     *
     * @return int
     */
    public static int getworkerHostIp() {
        try {
            byte[] bytes = InetAddress.getLocalHost().getAddress();
            return Integer.valueOf(bytes[3] & 0xFF);
        } catch (Exception e) {
            return 1;
        }

    }

    public static long id() {
        try {
            return IdWorker.getFlowIdWorkerInstance().nextId();
        } catch (Exception e) {
            return Long.parseLong(System.currentTimeMillis() + "" + new Random(System.currentTimeMillis()).nextInt(10));
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println(System.currentTimeMillis());
        System.out.println(IdWorker.id());
    }
}
