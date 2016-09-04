package model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(CountryLang.class)
public class CountryLang_ {
    public static volatile SingularAttribute<CountryLang, Integer> id;
	public static volatile SingularAttribute<CountryLang, String> name;
    public static volatile SingularAttribute<CountryLang, Country> countryId;
    public static volatile SingularAttribute<CountryLang, Integer> countryId_;
    public static volatile SingularAttribute<CountryLang, Language> languageId;
    public static volatile SingularAttribute<CountryLang, Integer> languageId_;
}
