package convert;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;


/**
 * Created by Yuksi on 13.06.2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class ConverterTest {

    Converter converter;

    @Mock
    CurrencyConnector connector;

    @Before
    public void before() {
        converter = new Converter();
        converter.setConnector(connector);
    }

    @Test
    public void convertUAH() throws IOException {
        Mockito.when(connector.getJsonRate(Converter.currency1)).thenReturn("{:111}");
        Assert.assertTrue(1110 == converter.convert(10, Converter.currency1));
    }

    @Test
    public void convertEUR() throws IOException {
        Mockito.when(connector.getJsonRate(Converter.currency2)).thenReturn("{:222}");
        Assert.assertTrue(2220 == converter.convert(10, Converter.currency2));
    }

    @Test
    public void convertGBP() throws IOException {
        Mockito.when(connector.getJsonRate(Converter.currency3)).thenReturn("{:333}");
        Assert.assertTrue(3330 == converter.convert(10, Converter.currency3));
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void convertCurrencyNull() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Argument is null");
        converter.convert(10, null);
    }

    @Test
    public void convertCurrencyNotValid() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Argument is not valid");
        converter.convert(10, " c");
    }
}