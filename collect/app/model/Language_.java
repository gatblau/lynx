package model;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Language.class)
public class Language_ {

    public static volatile SingularAttribute<Language, Integer> id;

    public static volatile SingularAttribute<Language, String> name;
    public static volatile SingularAttribute<Language, String> code;

    public static volatile SetAttribute<Language, FactDefLang> factDefLangLanguageViaLanguageId;
    public static volatile SetAttribute<Language, OptionDefLang> optionDefLangLanguageViaLanguageId;
    public static volatile SetAttribute<Language, Respondent> respondentLanguageViaLanguageId;
    public static volatile SetAttribute<Language, RoleLang> roleLangLanguageViaLanguageId;
    public static volatile SetAttribute<Language, SectionDefLang> sectionDefLangLanguageViaLanguageId;
    public static volatile SetAttribute<Language, SurveyDefLang> surveyDefLangLanguageViaLanguageId;


}
