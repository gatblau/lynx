package model;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Item.class)
public class Item_ {
    public static volatile SingularAttribute<Item, Integer> id;
	public static volatile SingularAttribute<Item, ItemDef> itemDefId;
    public static volatile SingularAttribute<Item, Integer> itemDefId_;
    public static volatile SingularAttribute<Item, Section> sectionId;
    public static volatile SingularAttribute<Item, Integer> sectionId_;
	public static volatile SetAttribute<Item, Option> optionItemViaItemId;
}
