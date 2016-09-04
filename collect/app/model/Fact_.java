package model;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Fact.class)
public class Fact_ {
    public static volatile SingularAttribute<Fact, Integer> id;
	public static volatile SingularAttribute<Fact, FactDef> factDefId;
    public static volatile SingularAttribute<Fact, Integer> factDefId_;
    public static volatile SingularAttribute<Fact, Section> sectionId;
    public static volatile SingularAttribute<Fact, Integer> sectionId_;
	public static volatile SetAttribute<Fact, Option> optionFactViaFactId;
}
