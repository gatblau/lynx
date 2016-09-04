package model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Option.class)
public class Option_ {
	public static volatile SingularAttribute<Option, Integer> id;
	public static volatile SingularAttribute<Option, String> value;
    public static volatile SingularAttribute<Option, Fact> factId;
    public static volatile SingularAttribute<Option, Integer> factId_;
    public static volatile SingularAttribute<Option, OptionDef> optionDefId;
    public static volatile SingularAttribute<Option, Integer> optionDefId_;
}
