/*
package com.example.converter.parser;


import com.example.converter.model.CurrencyRate;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import org.w3c.dom.Element;


public class CurrencyConverter {
    public CurrencyRate parseXmlString(String xmlString) throws Exception {
        // Создаем фабрику документов
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);

        // Создаем парсер документов и парсим XML-строку
        InputSource source = new InputSource(new StringReader(xmlString));
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(source);

        // Получаем элемент <Valute>
        Element valuteElement = (Element) doc.getElementsByTagName("Valute").item(0);

        // Создаем объект CurrencyRate
        var rate = new CurrencyRate();

        // Заполняем его поля значениями из XML-элемента
        rate.charCode = valuteElement.getElementsByTagName("CharCode").item(0).getTextContent();
        rate.numCode = valuteElement.getElementsByTagName("NumCode").item(0).getTextContent();
        rate.charCode = valuteElement.getElementsByTagName("CharCode").item(0).getTextContent();
        rate.nominal = valuteElement.getElementsByTagName("Nominal").item(0).getTextContent();
        rate.name = valuteElement.getElementsByTagName("Name").item(0).getTextContent();
        rate.value = valuteElement.getElementsByTagName("Value").item(0).getTextContent();

        return rate;
    }
}
*/
