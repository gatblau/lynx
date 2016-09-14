package model;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ResourceLayout.class)
public class ResourceLayout_ {
    public static volatile SingularAttribute<ResourceLayout, Integer> id;
    public static volatile SingularAttribute<ResourceLayout, String> value;
    public static volatile SetAttribute<ResourceLayout, FactDef> factDefResourceLayoutViaResourceLayoutId;
}
