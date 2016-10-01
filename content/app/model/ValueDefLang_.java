package model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ValueDefLang.class)
public class ValueDefLang_ {
	public static volatile SingularAttribute<ValueDefLang, Integer> id;
	public static volatile SingularAttribute<ValueDefLang, String> name;
    public static volatile SingularAttribute<ValueDefLang, String> description;
    public static volatile SingularAttribute<ValueDefLang, Language> languageId;
    public static volatile SingularAttribute<ValueDefLang, Integer> languageId_;
    public static volatile SingularAttribute<ValueDefLang, ValueDef> valueDefId;
    public static volatile SingularAttribute<ValueDefLang, Integer> valueDefId_;
}
