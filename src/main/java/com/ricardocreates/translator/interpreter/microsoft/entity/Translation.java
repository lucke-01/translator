package com.ricardocreates.translator.interpreter.microsoft.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class Translation implements Serializable {
    private String text;
    private String to;
}
