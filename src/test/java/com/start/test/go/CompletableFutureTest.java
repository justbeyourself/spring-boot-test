package com.start.test.go;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @description: ${description}
 * @author: zhanghuiyong
 * @create: 2019-10-10 16:20
 */
public class CompletableFutureTest {
    public static void main(String[] args) {

        CompletableFuture<String> completableFuture
                = CompletableFuture.supplyAsync(() -> "Hello");

        // thenRun 在两个任务任务A，任务B中，如果既不需要任务A的值也不想在任务B中引用
        CompletableFuture<Void> future = completableFuture
                .thenRun(() -> System.out.println("Computation finished."));

        // thenAccept 在两个任务任务A，任务B中，如果你不需要在Future中有返回值，则可以用 thenAccept方法接收将计算结果传递给它
        CompletableFuture<Void> future1 = completableFuture
                .thenAccept(s -> System.out.println("Computation returned: " + s));

        // thenApply 在两个任务任务A，任务B中，任务B想要任务A计算的结果，可以用thenApply方法来接受一个函数实例
        CompletableFuture<String> future2 = completableFuture
                .thenApply(s -> s + " World");

        // 该thenCompose方法连同thenApply一样实现了结果的合并计算。但是他们的内部形式是不一样的，它们与Java 8中可用的Stream和Optional类的map和flatMap方法是有着类似的设计思路在里面的。
        // 两个方法都接收一个CompletableFuture并将其应用于计算结果，但thenCompose（flatMap）方法接收一个函数，该函数返回相同类型的另一个CompletableFuture对象。此功能结构允许将这些类的实例继续进行组合计算。
        CompletableFuture<String> completableFuture1
                = CompletableFuture.supplyAsync(() -> "Hello")
                .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " World"));

        // 取两个任务的结果
        CompletableFuture<String> completableFuture2
                = CompletableFuture.supplyAsync(() -> "Hello")
                .thenCombine(CompletableFuture.supplyAsync(() -> " World"), (s1, s2) -> s1 + s2);

        CompletableFuture<String> future3 = completableFuture
                .thenApplyAsync(s -> s + " World");
        try {
            System.out.println(future3.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }
}
