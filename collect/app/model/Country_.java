package model;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Country.class)
public class Country_ {
    public static volatile SingularAttribute<Country, Integer> id;

    public static volatile SingularAttribute<Country, Language> defaultLanguageId;
    public static volatile SingularAttribute<Country, Integer> defaultLanguageId_;

    public static volatile SetAttribute<Country, CountryLang> countryLangCountryViaCountryId;
    public static volatile SetAttribute<Country, Respondent> respondentCountryViaCountryId;
}
