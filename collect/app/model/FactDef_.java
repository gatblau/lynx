package model;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(FactDef.class)
public class FactDef_ {

    public static volatile SingularAttribute<FactDef, Integer> id;

    public static volatile SingularAttribute<FactDef, SectionDef> sectionDefId;
    public static volatile SingularAttribute<FactDef, Integer> sectionDefId_;

    public static volatile SetAttribute<FactDef, FactDefLang> factDefLangFactDefViaFactDefId;
    public static volatile SetAttribute<FactDef, OptionDef> optionDefFactDefViaFactDefId;

    public static volatile SetAttribute<FactDef, Section> sectionFactViaId;

}
