package model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(FactDefLang.class)
public class FactDefLang_ {
    public static volatile SingularAttribute<FactDefLang, Integer> id;
    public static volatile SingularAttribute<FactDefLang, String> name;
    public static volatile SingularAttribute<FactDefLang, String> description;
    public static volatile SingularAttribute<FactDefLang, String> question;
    public static volatile SingularAttribute<FactDefLang, FactDef> factDefId;
    public static volatile SingularAttribute<FactDefLang, Integer> factDefId_;
    public static volatile SingularAttribute<FactDefLang, Language> languageId;
    public static volatile SingularAttribute<FactDefLang, Integer> languageId_;
}
