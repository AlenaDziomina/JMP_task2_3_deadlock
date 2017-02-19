package com.grouk;

import java.util.ArrayList;

public class Main extends Thread {

    private final Object resource1;
    private final Object resource2;
    private int i;

    public Main(int i, Object resource1, Object resource2) {
        this.resource1 = resource1;
        this.resource2 = resource2;
        this.i = i;
    }

    @Override
    public void run() {
        System.out.println("Thread " + i + "started");
        synchronized(resource1){
            System.out.println("Thread " + i + ": locked resource " + resource1);
            try{
                Thread.sleep(50);
            } catch (InterruptedException e) {}

            synchronized(resource2){
                System.out.println("Thread " + i + ": locked resource " + resource2);
            }
        }
    }

    public static void main(String args[]) {
        ArrayList<Object> resources = new ArrayList<>(4);
        resources.add("resource1");
        resources.add("resource2");
        resources.add("resource3");
        resources.add("resource4");

        for (int i = 0; i < 4; i++) {
            Thread t = new Main(i, resources.get(i), resources.get((i + 1) % 4));
            t.start();
        }
    }
}
