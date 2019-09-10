package com.crud.communicator.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LoveCalculatorDto {
    private String fname;
    private String sname;
    private String percentage;
    private String result;

    @Override
    public String toString() {
        return percentage + ": " + result;
    }
}
