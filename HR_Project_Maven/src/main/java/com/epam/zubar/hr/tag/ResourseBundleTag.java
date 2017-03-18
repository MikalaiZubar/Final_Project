package com.epam.zubar.hr.tag;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Class uses ResourceBundle to get messages from properties files depending on the Locale.
 * It is needed for internationalization support. The tag will be used in JSPs
 * to print messages stored in properties files.
 * @author Mikalay Zubar
 */

public class ResourseBundleTag extends TagSupport{

    private static final long serialVersionUID = 1324228548110964979L;
    private String message; // message attribute in the tag
    private String locale;  // locale attribute in the tag
    private static final String ROOT = "property/text";

    public void setMessage(String message){
        this.message = message;
    }

    public void setLocale(String locale){
        this.locale = locale;
    }

    /**
     * Method uses ResourceBundle to get messages from properties files
     * depending on the Locale.
     *
     */
    @Override
    public int doStartTag() throws JspException {
        ResourceBundle rb = ResourceBundle.getBundle(ROOT,
                receiveLocale(locale));
        try {
            pageContext.getOut().write(rb.getString(message));
        } catch (IOException e) {
            throw new JspException("Error. Unable to handle tag!", e);
        }
        return SKIP_BODY;
    }

    private Locale receiveLocale(String locale) {
        Locale newLocale;
        switch (locale) {
            case "eng":
            case "ENG":
                newLocale = new Locale("en", "US");
                break;
            case "rus":
            case "RUS":
                newLocale = new Locale("ru", "RU");
                break;
            case "ger":
            case "GER":
                newLocale = new Locale("de", "DE");
                break;
            default:
                newLocale = Locale.getDefault();
        }
        return newLocale;
    }

    @Override
    public int doEndTag(){
        return EVAL_PAGE;
    }
}
