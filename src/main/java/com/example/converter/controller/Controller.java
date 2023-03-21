package com.example.converter.controller;

import com.example.converter.model.CurrencyRate;
import com.example.converter.parser.CurrencyRateParserXml;
import com.example.converter.requester.CbrRequesterImpl;
import org.apache.catalina.LifecycleState;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;

@RestController
@RequestMapping("/converter")
public class Controller {

    CbrRequesterImpl cbrRequester;
    CurrencyRateParserXml currencyRateParserXml;

    public Controller(CbrRequesterImpl cbrRequester, CurrencyRateParserXml currencyRateParserXml) {
        this.cbrRequester = cbrRequester;
        this.currencyRateParserXml = currencyRateParserXml;
    }

    private static final String CBR_URL = "http://www.cbr.ru/scripts/XML_daily.asp";

    @GetMapping(value="/{name}")
    public String helloGET(
            @PathVariable("name") String name,
            @RequestParam("value") int value) {
            cbrRequester.getRatesAsXml(CBR_URL);
            currencyRateParserXml.parse(cbrRequester.getRatesAsXml(CBR_URL));
        ArrayList<CurrencyRate> list = (ArrayList<CurrencyRate>) currencyRateParserXml.parse(cbrRequester.getRatesAsXml(CBR_URL));
        CurrencyRate currencyRate = currencyRateParserXml.filterCurrencyRate(list,name);

        BigDecimal correctRate =  currencyRateParserXml.convertStringToBI(currencyRate.getValue());
        BigDecimal input =  new BigDecimal(value);
        BigDecimal result = input.multiply(correctRate);
        String out = result.toPlainString();
        return String.format(
                "{\"message\":\"result =  %s \"}",  out);

    }
}
