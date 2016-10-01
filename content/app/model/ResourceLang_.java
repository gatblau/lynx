package model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ResourceLang.class)
public class ResourceLang_ {
	public static volatile SingularAttribute<ResourceLang, Integer> id;
	public static volatile SingularAttribute<ResourceLang, String> name;
    public static volatile SingularAttribute<ResourceLang, String> description;
    public static volatile SingularAttribute<ResourceLang, Language> languageId;
    public static volatile SingularAttribute<ResourceLang, Integer> languageId_;
    public static volatile SingularAttribute<ResourceLang, Resource> resourceId;
    public static volatile SingularAttribute<ResourceLang, Integer> resourceId_;
}
