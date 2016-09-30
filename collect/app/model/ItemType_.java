package model;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ItemType.class)
public class ItemType_ {
	public static volatile SingularAttribute<ItemType, Integer> id;
	public static volatile SingularAttribute<ItemType, String> value;
    public static volatile SetAttribute<ItemType, ItemDef> itemDefItemTypeViaItemTypeId;
}
