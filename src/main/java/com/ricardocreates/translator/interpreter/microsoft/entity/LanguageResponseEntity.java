package com.ricardocreates.translator.interpreter.microsoft.entity;

import java.io.Serializable;
import java.util.Map;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;
@Data
public class LanguageResponseEntity implements Serializable {
    @SerializedName("translation")
    @Expose
    private Map<String, LanguageEntity> translation;
}
