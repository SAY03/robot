package com.company;

public class Main {

    public static void main(String[] args) {

        Robot robot=new Robot();

        Runnable r1 = new Runnable() {
            @Override
            public void run(){
//                for (int i = 1; i < 5; i++) {
                while(true) {
                    robot.rightStep();
                }
            }
        };
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
//                for (int i = 1; i < 5; i++) {
                while(true) {
                    robot.leftStep();
                }
            }
        };

        new Thread(r1).start();
        new Thread(r2).start();
    }
}

class Robot{
    private boolean leg = true; // true - left step, false - right step
    public synchronized void rightStep() {
        while (leg) {
            try {
                wait();
            }
            catch (InterruptedException e) {
            }
        }
        leg = true;
        System.out.println("Right leg");
        notify();
    }
    public synchronized void leftStep() {
        while (!leg) {
            try {
                wait();
            }
            catch (InterruptedException e) {
            }
        }
        leg = false;
        System.out.println("Left leg");
        notify();
    }
}
