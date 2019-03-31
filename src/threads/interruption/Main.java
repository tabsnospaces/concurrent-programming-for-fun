/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads.interruption;

/**
 *
 * @author yuzo
 */
class MyThread extends Thread {

    boolean waiting = true;
    boolean ready = false;

    MyThread() {
    }

    public void run() {
        String thrdName = Thread.currentThread().getName();
        System.out.println(thrdName + " starting.");
        while (waiting) {
            System.out.println("waiting:" + waiting);
        }
        System.out.println("waiting...");
        startWait();
        try {
            Thread.sleep(200);
        } catch (Exception exc) {
            System.out.println(thrdName + " interrupted.");
        }
        System.out.println(thrdName + " terminating.");
    }

    synchronized void startWait() {
        try {
            while (!ready) {
                wait();
            }
        } catch (InterruptedException exc) {
            System.out.println("wait() interrupted");
        }
    }

    synchronized void notice() {
        ready = true;
        notify();
    }
}

public class Main {

    public static void main(String args[]) throws Exception {
        MyThread thrd = new MyThread();
        thrd.setName("MyThread #1");
        showThreadStatus(thrd);

        thrd.start();
        Thread.sleep(50);
        showThreadStatus(thrd);

        thrd.waiting = false;
        Thread.sleep(50);
        showThreadStatus(thrd);

        thrd.notice();
        Thread.sleep(50);
        showThreadStatus(thrd);
        while (thrd.isAlive()) {
            System.out.println("alive");
        }
        showThreadStatus(thrd);
    }

    static void showThreadStatus(Thread thrd) {
        System.out.println(thrd.getName() + " Alive:" + thrd.isAlive() + " State:" + thrd.getState());
    }
}
