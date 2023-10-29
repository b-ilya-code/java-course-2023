package edu.hw3.Task6;

import java.util.Comparator;
import java.util.PriorityQueue;

public class StockMarket {
    PriorityQueue<Stock> stocks = new PriorityQueue<>(Comparator.comparingInt(Stock::price).reversed());

    public void add(Stock stock) {
        stocks.add(stock);
    }

    public void remove(Stock stock) {
        stocks.remove(stock);
    }

    public Stock mostValuableStock() {
        return stocks.peek();
    }
}
