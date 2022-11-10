module com.ricardocreates {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.base;
    requires lombok;
    requires org.apache.httpcomponents.client5.httpclient5;
    requires org.apache.httpcomponents.core5.httpcore5;
    requires com.google.gson;
    requires com.google.common;
    requires org.mapstruct;
    requires java.compiler;
    requires java.desktop;
    requires org.apache.commons.lang3;
    requires feign.core;
    requires feign.okhttp;
    requires feign.gson;
    requires feign.slf4j;
    exports com.ricardocreates.translator.gui;
    exports com.ricardocreates.translator.gui.controller;
    exports com.ricardocreates.translator.interpreter.microsoft to org.mapstruct;
    exports com.ricardocreates.translator.gui.converter;
    //exports com.ricardocreates.translator.interpreter.libretranslate to org.mapstruct;
    exports com.ricardocreates.translator.interpreter.libretranslate;
    opens com.ricardocreates.translator.gui.controller to javafx.fxml;
    opens com.ricardocreates.translator.interpreter.microsoft.entity to com.google.gson;
    opens com.ricardocreates.translator.interpreter.libretranslate.entity to com.google.gson;

}