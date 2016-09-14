package model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import javax.persistence.metamodel.SetAttribute;

@StaticMetamodel(FactDef.class)
public class FactDef_ {
    public static volatile SingularAttribute<FactDef, Integer> id;
    public static volatile SingularAttribute<FactDef, Boolean> required;
    public static volatile SingularAttribute<FactDef, String> regex;
    public static volatile SingularAttribute<FactDef, String> min;
    public static volatile SingularAttribute<FactDef, String> max;
    public static volatile SingularAttribute<FactDef, FactType> factTypeId;
    public static volatile SingularAttribute<FactDef, Integer> factTypeId_;
    public static volatile SingularAttribute<FactDef, ResourceLayout> resourceLayoutId;
    public static volatile SingularAttribute<FactDef, Integer> resourceLayoutId_;
    public static volatile SingularAttribute<FactDef, SectionDef> sectionDefId;
    public static volatile SingularAttribute<FactDef, Integer> sectionDefId_;
    public static volatile SetAttribute<FactDef, Fact> factFactDefViaFactDefId;
    public static volatile SetAttribute<FactDef, FactDefLang> factDefLangFactDefViaFactDefId;
    public static volatile SetAttribute<FactDef, OptionDef> optionDefFactDefViaFactDefId;
    public static volatile SetAttribute<FactDef, Resource> resourceFactDefViaFactDefId;
}
