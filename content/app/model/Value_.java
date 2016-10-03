package model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.sql.Timestamp;

@StaticMetamodel(Value.class)
public class Value_ {
    public static volatile SingularAttribute<Value, Integer> id;
    public static volatile SingularAttribute<Value, String> shortText;
    public static volatile SingularAttribute<Value, String> longText;
    public static volatile SingularAttribute<Value, Timestamp> date;
    public static volatile SingularAttribute<Value, Integer> integer;
    public static volatile SingularAttribute<Value, java.math.BigDecimal> decimal;
    public static volatile SingularAttribute<Value, Boolean> flag;
    public static volatile SingularAttribute<Value, ValueDef> valueDefId;
    public static volatile SingularAttribute<Value, Integer> valueDefId_;
    public static volatile SingularAttribute<Value, Item> itemId;
    public static volatile SingularAttribute<Value, Integer> itemId_;
}
