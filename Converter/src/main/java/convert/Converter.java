package convert;

import java.io.IOException;

/**
 * Created by Yuksi on 13.06.2018.
 */
public class Converter {
    final static String currency1 = "UAH";
    final static String currency2 = "EUR";
    final static String currency3 = "GBP";

    private CurrencyConnector connector;

    public double convert(double sum, String currency) {
        if (currency == null) {
            throw new IllegalArgumentException("Argument is null");
        }
         else if (!currency1.equals(currency) && !currency2.equals(currency) && !currency3.equals(currency)) {
            throw new IllegalArgumentException("Argument is not valid");
        }
        double rate = 0;
        String response = null;

        try {
            response = connector.getJsonRate(currency);
            rate =  Double.parseDouble(response.substring(response.indexOf(':') + 1, response.length() - 1));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sum * rate;
    }

    public void setConnector(CurrencyConnector connector) {
        this.connector = connector;
    }

    public static void main(String[] args) {
        Converter converter = new Converter();
        CurrencyConnector connector = new CurrencyConnector();
        converter.setConnector(connector);
        System.out.println(converter.convert(10, "UAH"));
        System.out.println(converter.convert(10, "EUR"));
        System.out.println(converter.convert(10, "GBP"));
    }
}