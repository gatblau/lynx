package model;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(OptionDef.class)
public class OptionDef_ {
	public static volatile SingularAttribute<OptionDef, Integer> id;
	public static volatile SingularAttribute<OptionDef, ItemDef> itemDefId;
    public static volatile SingularAttribute<OptionDef, Integer> itemDefId_;
	public static volatile SetAttribute<OptionDef, Option> optionOptionDefViaOptionDefId;
    public static volatile SetAttribute<OptionDef, OptionDefLang> optionDefLangOptionDefViaOptionDefId;

}