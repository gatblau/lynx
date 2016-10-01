package model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ItemDefLang.class)
public class ItemDefLang_ {
    public static volatile SingularAttribute<ItemDefLang, Integer> id;
    public static volatile SingularAttribute<ItemDefLang, String> name;
    public static volatile SingularAttribute<ItemDefLang, String> description;
    public static volatile SingularAttribute<ItemDefLang, String> question;
    public static volatile SingularAttribute<ItemDefLang, ItemDef> itemDefId;
    public static volatile SingularAttribute<ItemDefLang, Integer> itemDefId_;
    public static volatile SingularAttribute<ItemDefLang, Language> languageId;
    public static volatile SingularAttribute<ItemDefLang, Integer> languageId_;
}
