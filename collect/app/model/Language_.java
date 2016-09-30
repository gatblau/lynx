package model;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Language.class)
public class Language_ {
	public static volatile SingularAttribute<Language, Integer> id;
	public static volatile SingularAttribute<Language, String> name;
    public static volatile SingularAttribute<Language, String> code;
	public static volatile SetAttribute<Language, Country> countryLanguageViaDefaultLanguageId;
    public static volatile SetAttribute<Language, CountryLang> countryLangLanguageViaLanguageId;
    public static volatile SetAttribute<Language, ItemDefLang> itemDefLangLanguageViaLanguageId;
    public static volatile SetAttribute<Language, OptionDefLang> optionDefLangLanguageViaLanguageId;
    public static volatile SetAttribute<Language, User> userLanguageViaPreferredLanguageId;
    public static volatile SetAttribute<Language, RoleLang> roleLangLanguageViaLanguageId;
    public static volatile SetAttribute<Language, SectionDefLang> sectionDefLangLanguageViaLanguageId;
    public static volatile SetAttribute<Language, ContentDefLang> contentDefLangLanguageViaLanguageId;
    public static volatile SetAttribute<Language, ContentLang> contentLangLanguageViaLanguageId;
}
