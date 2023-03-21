package com.example.converter.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.Objects;

@Value
@AllArgsConstructor(onConstructor = @__(@JsonCreator))
@Builder
public class CurrencyRate {
    public String numCode;
    public String charCode;
    public String nominal;
    public String name;
    public String value;


}