package model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(RoleLang.class)
public class RoleLang_ {
	public static volatile SingularAttribute<RoleLang, Integer> id;
	public static volatile SingularAttribute<RoleLang, String> name;
    public static volatile SingularAttribute<RoleLang, String> description;
    public static volatile SingularAttribute<RoleLang, Language> languageId;
    public static volatile SingularAttribute<RoleLang, Integer> languageId_;
    public static volatile SingularAttribute<RoleLang, Role> roleId;
    public static volatile SingularAttribute<RoleLang, Integer> roleId_;
}
