package com.ricardocreates.translator.interpreter.microsoft;

import java.io.IOException;
import java.util.List;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.mapstruct.factory.Mappers;

import com.ricardocreates.translator.config.TranslatorConfig;
import com.ricardocreates.translator.config.UserConfig;
import com.ricardocreates.translator.gson.GsonUtil;
import com.ricardocreates.translator.interpreter.InterpreterService;
import com.ricardocreates.translator.interpreter.microsoft.entity.LanguageResponseEntity;
import com.ricardocreates.translator.interpreter.microsoft.entity.TranslateResponseEntity;
import com.ricardocreates.translator.interpreter.microsoft.entity.Translation;
import com.ricardocreates.translator.interpreter.model.Language;
import com.ricardocreates.translator.util.StringsCustomUtil;

public class MicrosfotApiInterpreterService implements InterpreterService {
    private static final String API_VERSION = "?api-version=3.0";
    private static final String BASIC_URL_API = "https://api.cognitive.microsofttranslator.com/";
    private static final String LANGUAGES_URL_API = String.format("%s%s%s", BASIC_URL_API,"languages",API_VERSION);
    
    private MicrosoftInterpreterMapper msInterpreterMapper = Mappers.getMapper(MicrosoftInterpreterMapper.class);
    private final UserConfig userConfig = TranslatorConfig.getUserConfig();
    
    @Override
    public List<Language> getAvailableLanguages() {
        HttpGet availableLanguagesGet = new HttpGet(LANGUAGES_URL_API);

        String responseAvailableLanguagesString = "";
        try (CloseableHttpResponse responseAvailableLanguagesGet = HttpClients.createDefault().execute(availableLanguagesGet)){
            responseAvailableLanguagesString = EntityUtils.toString(responseAvailableLanguagesGet.getEntity());
        } catch (IOException e) {
            throw new RuntimeException("unable to communicate with microsoft service");
        } catch (ParseException p) {
            throw new RuntimeException("unable to parse response from microsoft service");
        }
        LanguageResponseEntity languageResponse = GsonUtil.getGson().fromJson(responseAvailableLanguagesString, LanguageResponseEntity.class);
        List<Language> languages = msInterpreterMapper.languageResponseToListLanguage(languageResponse);
        
        return languages;
    }

    @Override
    public String translate(String sourceLanguage, String destLanguage, String text) {
        final String translateUrl = String.format("%stranslate%s&from=%s&to=%s",BASIC_URL_API, API_VERSION, sourceLanguage, destLanguage);
        System.out.println(translateUrl);
        HttpPost translatePost = new HttpPost(translateUrl);
        final String jsonStringBody = String.format("[{\"text\": \"%s\"}]", text);
        
        HttpEntity stringEntity = new StringEntity(jsonStringBody, ContentType.APPLICATION_JSON);
        translatePost.setEntity(stringEntity);
        translatePost.addHeader("Content-Type", "application/json");
        //TODO change it
        translatePost.addHeader("Ocp-Apim-Subscription-Key", userConfig.getMicrosoftApiConfig().getOcpApimSubscriptionKey());
        translatePost.addHeader("Ocp-Apim-Subscription-Region", userConfig.getMicrosoftApiConfig().getOcpApimSubscriptionRegion());
        

        String responseTranslateString = "";
        try (CloseableHttpResponse responseTranslatePost = HttpClients.createDefault().execute(translatePost)){
            responseTranslateString = EntityUtils.toString(responseTranslatePost.getEntity());
            //remove array response to be an object response
            responseTranslateString = StringsCustomUtil.jsonArrayStringToJsonObjectString(responseTranslateString);
        } catch (IOException e) {
            throw new RuntimeException("unable to communicate with microsoft service");
        } catch (ParseException p) {
            throw new RuntimeException("unable to parse response from microsoft service");
        }
        System.out.println("RESPONSE TEXT \n"+responseTranslateString);
        TranslateResponseEntity translateResponse = GsonUtil.getGson().fromJson(responseTranslateString, TranslateResponseEntity.class);
        Translation translation = translateResponse.getTranslations().get(0);
        return translation.getText();
    }

}
