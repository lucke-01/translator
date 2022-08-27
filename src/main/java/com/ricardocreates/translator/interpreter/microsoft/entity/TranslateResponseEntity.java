package com.ricardocreates.translator.interpreter.microsoft.entity;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class TranslateResponseEntity implements Serializable {
    private List<Translation> translations;
}
