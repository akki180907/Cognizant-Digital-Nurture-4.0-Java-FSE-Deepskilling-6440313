public class Main {
    public static void main(String[] args) {
        StockMarket stockMarket = new StockMarket();

        Observer mobile1 = new MobileApp("InvestorX");
        Observer web1 = new WebApp("TraderPro");

        stockMarket.register(mobile1);
        stockMarket.register(web1);

        stockMarket.setStockPrice(1865.50);
        stockMarket.setStockPrice(1920.00);

        stockMarket.deregister(web1);
        stockMarket.setStockPrice(1980.25);
    }
}
