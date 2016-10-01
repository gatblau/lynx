package model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Value.class)
public class Value_ {
	public static volatile SingularAttribute<Value, Integer> id;
	public static volatile SingularAttribute<Value, String> value;
    public static volatile SingularAttribute<Value, Item> itemId;
    public static volatile SingularAttribute<Value, Integer> itemId_;
    public static volatile SingularAttribute<Value, ValueDef> valueDefId;
    public static volatile SingularAttribute<Value, Integer> valueDefId_;
}
