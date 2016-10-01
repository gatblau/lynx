package model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ContentLang.class)
public class ContentLang_ {
	public static volatile SingularAttribute<ContentLang, Integer> id;
	public static volatile SingularAttribute<ContentLang, String> name;
    public static volatile SingularAttribute<ContentLang, String> description;
    public static volatile SingularAttribute<ContentLang, Language> languageId;
    public static volatile SingularAttribute<ContentLang, Integer> languageId_;
    public static volatile SingularAttribute<ContentLang, Content> contentId;
    public static volatile SingularAttribute<ContentLang, Integer> contentId_;
}
