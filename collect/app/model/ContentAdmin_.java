package model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ContentAdmin.class)
public class ContentAdmin_ {
	public static volatile SingularAttribute<ContentAdmin, Integer> id;
	public static volatile SingularAttribute<ContentAdmin, User> userId;
	public static volatile SingularAttribute<ContentAdmin, Integer> userId_;
	public static volatile SingularAttribute<ContentAdmin, ContentDef> contentDefId;
	public static volatile SingularAttribute<ContentAdmin, Integer> contentDefId_;
}
