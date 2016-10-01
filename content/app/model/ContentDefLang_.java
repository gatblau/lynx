package model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ContentDefLang.class)
public class ContentDefLang_ {
	public static volatile SingularAttribute<ContentDefLang, Integer> id;
	public static volatile SingularAttribute<ContentDefLang, String> name;
    public static volatile SingularAttribute<ContentDefLang, String> description;
    public static volatile SingularAttribute<ContentDefLang, Language> languageId;
    public static volatile SingularAttribute<ContentDefLang, Integer> languageId_;
    public static volatile SingularAttribute<ContentDefLang, ContentDef> contentDefId;
    public static volatile SingularAttribute<ContentDefLang, Integer> contentDefId_;
}
