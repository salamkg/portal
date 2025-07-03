package kg.megacom.portal.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Locale;
import java.util.ResourceBundle;

public final class LocalizationService {

    private static final ResourceBundle messagesKg = ResourceBundle.getBundle("messages_kg", Locale.forLanguageTag("kg"));
    private static final ResourceBundle messagesRu = ResourceBundle.getBundle("messages_ru", Locale.forLanguageTag("ru"));

    public LocalizationService() {}

    public static String getMessage(String key) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String langId = request.getHeader("langId");

        if (langId != null) {
            return getMessage(Integer.parseInt(langId), key);
        }
        return messagesRu.getString(key);
    }

    public static String getMessage(int langId, String key) {
        switch (langId) {
            case 1: return messagesKg.getString(key);
            default: return messagesRu.getString(key);
        }
    }

}
