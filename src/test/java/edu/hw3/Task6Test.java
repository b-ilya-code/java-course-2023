package edu.hw3;

import edu.hw3.Task6.Stock;
import edu.hw3.Task6.StockMarket;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task6Test {
    @Test
    void testMostValuableStock() {
        StockMarket stockMarket = new StockMarket();
        stockMarket.add(new Stock("a", 1));
        stockMarket.add(new Stock("b", 2));
        stockMarket.add(new Stock("c", 3));

        assertThat(stockMarket.mostValuableStock()).isEqualTo(new Stock("c", 3));
    }

    @Test
    void testRemoveStock() {
        StockMarket stockMarket = new StockMarket();
        Stock a = new Stock("a", 1);
        Stock b = new Stock("b", 2);
        stockMarket.add(a);
        stockMarket.add(b);

        stockMarket.remove(b);

        assertThat(stockMarket.mostValuableStock()).isEqualTo(a);
    }
}
