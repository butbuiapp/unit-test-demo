package miu.edu;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PortfolioTest extends TestCase {
    private Portfolio portfolio;
    private StockService stockService;

//    public static void main(String[] args) {
//        PortfolioTest test = new PortfolioTest();
//        test.setUp();
//        test.testGetMarketValue();
//    }

    public void setUp() {
        // create a portfolio that is to be tested
        portfolio = new Portfolio();

        // create a mock of stock service
        stockService = mock(StockService.class);
        portfolio.setStockService(stockService);
    }

    public void testGetMarketValue() {
        List<Stock> stocks = new ArrayList<>();
        Stock goolgeStock = new Stock("1", "Google", 10);
        Stock msStock = new Stock("2", "Microsoft", 100);
        stocks.add(goolgeStock);
        stocks.add(msStock);

        portfolio.setStocks(stocks);

        // mock getPrice
        when(stockService.getPrice(goolgeStock)).thenReturn(50.00);
        when(stockService.getPrice(msStock)).thenReturn(1000.00);

        double marketValue = portfolio.getMarketValue();
        assertThat(marketValue).isEqualTo(100500.0);
    }
}
