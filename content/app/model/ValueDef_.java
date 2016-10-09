package model;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ValueDef.class)
public class ValueDef_ {
	public static volatile SingularAttribute<ValueDef, Integer> id;
	public static volatile SingularAttribute<ValueDef, ItemDef> itemDefId;
	public static volatile SingularAttribute<ValueDef, Integer> itemDefId_;
	public static volatile SingularAttribute<ValueDef, ValueType> valueTypeId;
	public static volatile SingularAttribute<ValueDef, Integer> valueTypeId_;
	public static volatile SetAttribute<ValueDef, Value> valueValueDefViaValueDefId;
	public static volatile SetAttribute<ValueDef, ValueDefLang> valueDefLangValueDefViaValueDefId;
}
