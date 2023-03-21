package com.example.converter.parser;

import com.example.converter.model.CurrencyRate;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CurrencyRateParserXmlTest {
    @Test
    void parseTest() throws IOException, URISyntaxException {
        //given
        var parser = new CurrencyRateParserXml();
        //var uri = ClassLoader.getSystemResource("cbr_response.xml").toURI();
        var ratesXml = Files.readString(Path.of("C:\\Users\\Vit\\IdeaProjects\\converter\\src\\test\\java\\com\\example\\converter\\resouces\\cbr_response.xml"), Charset.forName("UTF-8"));

        //when
        var rates = parser.parse(ratesXml);

        //then
        assertThat(rates.size()).isEqualTo(43);
        assertThat(rates.contains(getUSDrate())).isTrue();
        assertThat(rates.contains(getEURrate())).isTrue();

    }

    CurrencyRate getUSDrate() {
        return CurrencyRate.builder()
                .numCode("840")
                .charCode("USD")
                .nominal("1")
                .name("Доллар США")
                .value("75,1927")
                .build();
    }

    CurrencyRate getEURrate() {
        return CurrencyRate.builder()
                .numCode("978")
                .charCode("EUR")
                .nominal("1")
                .name("Евро")
                .value("80,5192")
                .build();
    }

    @Test
    public void testFilterCurrencyRate() throws IOException {
            var parser = new CurrencyRateParserXml();
            //var uri = ClassLoader.getSystemResource("cbr_response.xml").toURI();
            var ratesXml = Files.readString(Path.of("C:\\Users\\Vit\\IdeaProjects\\converter\\src\\test\\java\\com\\example\\converter\\resouces\\cbr_response.xml"), Charset.forName("UTF-8"));

            //when
            ArrayList<CurrencyRate> rates = (ArrayList<CurrencyRate>) parser.parse(ratesXml);

            CurrencyRate THB = new CurrencyRate("764", "THB","10", "Таиландских батов","21,7832");

            CurrencyRate currencyRateUSD = parser.filterCurrencyRate(rates,"USD");
            CurrencyRate currencyRateEUR = parser.filterCurrencyRate(rates,"EUR");
            CurrencyRate currencyRateTHB = parser.filterCurrencyRate(rates,"THB");

            assertThat(currencyRateUSD.equals(getUSDrate())).isTrue();
            assertThat(currencyRateEUR.equals(getEURrate())).isTrue();
            assertThat(currencyRateTHB.equals(THB)).isTrue();

    }

    @Test
    void testConvertStringToBI(){
        var parser = new CurrencyRateParserXml();
        String eur =  getEURrate().getValue();
        BigDecimal expectedOutput = new BigDecimal("80.5192");
        BigDecimal actualOutput = parser.convertStringToBI(eur);
        assertThat(expectedOutput.equals(actualOutput)).isTrue();
    }
}