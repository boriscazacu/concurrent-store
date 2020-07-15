package com.petproject.store.model;

import com.petproject.store.Store;
import com.petproject.store.services.StorePerformanceService;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class Stall {

    Logger log;
    List<Seller>  sellers;
    AtomicInteger servedBuyers = new AtomicInteger();
    StorePerformanceService performanceService = new StorePerformanceService();

    public Stall(Logger log, List<Seller>  sellers) {
        this.log = log;
        this.sellers = sellers;
    }

    public synchronized void trade(Queue<Buyer> buyers) {
        Thread[] threads = new Thread[sellers.size()];
        int i =0;
        servedBuyers.set(0);
        performanceService.startServeBuyers();
        for (Seller seller: sellers) {
            threads[i] = new Thread(() -> {
                seller.serveTheBuyer(buyers.poll());
                servedBuyers.incrementAndGet();
            });
            threads[i].start();
            try {
                threads[0].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
        }
        log.info(performanceService.checkPerformance(servedBuyers.get()) );

    }

}
