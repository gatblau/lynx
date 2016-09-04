package model;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(FactType.class)
public class FactType_ {
	public static volatile SingularAttribute<FactType, Integer> id;
	public static volatile SingularAttribute<FactType, String> value;
    public static volatile SetAttribute<FactType, FactDef> factDefFactTypeViaFactTypeId;
}
