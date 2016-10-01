package model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(OptionDefLang.class)
public class OptionDefLang_ {
	public static volatile SingularAttribute<OptionDefLang, Integer> id;
	public static volatile SingularAttribute<OptionDefLang, String> name;
    public static volatile SingularAttribute<OptionDefLang, String> description;
    public static volatile SingularAttribute<OptionDefLang, Language> languageId;
    public static volatile SingularAttribute<OptionDefLang, Integer> languageId_;
    public static volatile SingularAttribute<OptionDefLang, OptionDef> optionDefId;
    public static volatile SingularAttribute<OptionDefLang, Integer> optionDefId_;
}
