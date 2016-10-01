package model;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Resource.class)
public class Resource_ {
	public static volatile SingularAttribute<Resource, Integer> id;
	public static volatile SingularAttribute<Resource, String> resourcePath;
    public static volatile SingularAttribute<Resource, String> link;
    public static volatile SingularAttribute<Resource, ItemDef> itemDefId;
    public static volatile SingularAttribute<Resource, Integer> itemDefId_;
    public static volatile SingularAttribute<Resource, ResourceType> resourceTypeId;
    public static volatile SingularAttribute<Resource, Integer> resourceTypeId_;
    public static volatile SetAttribute<Resource, ResourceLang> resourceLangResourceViaResourceId;
}
