package model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Option.class)
public class Option_ {
	public static volatile SingularAttribute<Option, Integer> id;
	public static volatile SingularAttribute<Option, String> value;
    public static volatile SingularAttribute<Option, Item> itemId;
    public static volatile SingularAttribute<Option, Integer> itemId_;
    public static volatile SingularAttribute<Option, OptionDef> optionDefId;
    public static volatile SingularAttribute<Option, Integer> optionDefId_;
}
