package model;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ResourceType.class)
public class ResourceType_ {
    public static volatile SingularAttribute<ResourceType, Integer> id;
    public static volatile SingularAttribute<ResourceType, String> value;
    public static volatile SingularAttribute<ResourceType, String> iconPath;
    public static volatile SetAttribute<ResourceType, Resource> resourceResourceTypeViaResourceTypeId;
}
