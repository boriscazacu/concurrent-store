package com.petproject.store.services;

import com.petproject.store.model.Seller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SellersService {

    public static void inviteSellers(List<Seller> sellers, int sellersCount) {
        sellers.addAll(Stream.generate(() -> new Seller()).limit(sellersCount).collect(Collectors.toList()));
    }
//        public static void inviteSellers(Seller sellers, int sellersCount) {
//            int cnt =0;
//            while (cnt < sellersCount) {
//                new Thread(() -> {
//                    new Seller();
//                }).start();
//                cnt ++;
//            }
//        }

}
