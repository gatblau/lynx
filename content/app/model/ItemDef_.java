package model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import javax.persistence.metamodel.SetAttribute;

@StaticMetamodel(ItemDef.class)
public class ItemDef_ {
    public static volatile SingularAttribute<ItemDef, Integer> id;
    public static volatile SingularAttribute<ItemDef, Boolean> required;
    public static volatile SingularAttribute<ItemDef, String> regex;
    public static volatile SingularAttribute<ItemDef, String> min;
    public static volatile SingularAttribute<ItemDef, String> max;
    public static volatile SingularAttribute<ItemDef, ItemType> itemTypeId;
    public static volatile SingularAttribute<ItemDef, Integer> itemTypeId_;
    public static volatile SingularAttribute<ItemDef, ResourceLayout> resourceLayoutId;
    public static volatile SingularAttribute<ItemDef, Integer> resourceLayoutId_;
    public static volatile SingularAttribute<ItemDef, SectionDef> sectionDefId;
    public static volatile SingularAttribute<ItemDef, Integer> sectionDefId_;
    public static volatile SetAttribute<ItemDef, Item> itemItemDefViaItemDefId;
    public static volatile SetAttribute<ItemDef, ItemDefLang> itemDefLangItemDefViaItemDefId;
    public static volatile SetAttribute<ItemDef, OptionDef> optionDefItemDefViaItemDefId;
    public static volatile SetAttribute<ItemDef, Resource> resourceItemDefViaItemDefId;
}
