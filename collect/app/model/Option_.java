package model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Option.class)
public class Option_ {

    public static volatile SingularAttribute<Option, Integer> id;

    public static volatile SingularAttribute<Option, Integer> factId;
    public static volatile SingularAttribute<Option, String> value;
    public static volatile SingularAttribute<Option, OptionDef> factOptionDefId;
    public static volatile SingularAttribute<Option, Integer> factOptionDefId_;



}
