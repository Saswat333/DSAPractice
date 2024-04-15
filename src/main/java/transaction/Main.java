//package main.transaction;
//
//import java.util.concurrent.locks.Lock;
//import java.util.concurrent.locks.ReentrantLock;
//
//class Account {
//    private int balance;
//    private final Lock lock = new ReentrantLock();
//
//    public Account(int balance) {
//        this.balance = balance;
//    }
//
//    public void withdraw(int amount) {
//        lock.lock();
//        try {
//            if (balance >= amount) {
//                balance -= amount;
//            } else {
//                System.out.println("Insufficient funds!");
//            }
//        } finally {
//            lock.unlock();
//        }
//    }
//
//    public void deposit(int amount) {
//        lock.lock();
//        try {
//            balance += amount;
//        } finally {
//            lock.unlock();
//        }
//    }
//
//    public int getBalance() {
//        return balance;
//    }
//}
//
//class TransferThread extends Thread {
//    private final Account fromAccount;
//    private final Account toAccount;
//    private final int amount;
//
//    public TransferThread(Account fromAccount, Account toAccount, int amount) {
//        this.fromAccount = fromAccount;
//        this.toAccount = toAccount;
//        this.amount = amount;
//    }
//
//    @Override
//    public void run() {
//        if (fromAccount.getBalance() >= amount) {
//            fromAccount.withdraw(amount);
//            toAccount.deposit(amount);
//            System.out.println("Transferred " + amount + " from " + fromAccount.hashCode() + " to " + toAccount.hashCode());
//        } else {
//            System.out.println("Insufficient funds in " + fromAccount.hashCode());
//        }
//    }
//}
//
//public class Main {
//    public static void main(String[] args) {
//        Account accountA = new Account(1000);
//        Account accountB = new Account(500);
//
//        //main.transaction
//        TransferThread thread1 = new TransferThread(accountA, accountB, 200);
//        TransferThread thread2 = new TransferThread(accountA, accountB, 100);
//
//        thread1.start();
//        thread2.start();
//
////        try {
////            thread1.join();
////            thread2.join();
////        } catch (InterruptedException e) {
////            e.printStackTrace();
////        }
//
//        System.out.println("main.transaction.Account A balance: " + accountA.getBalance());
//        System.out.println("main.transaction.Account B balance: " + accountB.getBalance());
//    }
//}
