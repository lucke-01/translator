package com.ricardocreates.translator.interpreter.microsoft.entity;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

import lombok.Data;
@Data
public class LanguageEntity implements Serializable {
    @Expose
    private String name;
    @Expose
    private String nativeName;
    @Expose
    private String dir;
}
