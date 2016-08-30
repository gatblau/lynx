package model;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(OptionDef.class)
public class OptionDef_ {

    public static volatile SingularAttribute<OptionDef, Integer> id;

    public static volatile SingularAttribute<OptionDef, String> name;
    public static volatile SingularAttribute<OptionDef, FactDef> factDefId;
    public static volatile SingularAttribute<OptionDef, Integer> factDefId_;

    public static volatile SetAttribute<OptionDef, Option> optionOptionDefViaFactOptionDefId;
    public static volatile SetAttribute<OptionDef, OptionDefLang> optionDefLangOptionDefViaOptionDefId;


}
