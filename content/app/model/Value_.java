package model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.sql.Timestamp;

@StaticMetamodel(Value.class)
public class Value_ {
    public static volatile SingularAttribute<Value, Integer> id;
    public static volatile SingularAttribute<Value, String> shortTextVal;
    public static volatile SingularAttribute<Value, String> longTextVal;
    public static volatile SingularAttribute<Value, Timestamp> dateVal;
    public static volatile SingularAttribute<Value, Integer> intVal;
    public static volatile SingularAttribute<Value, java.math.BigDecimal> decVal;
    public static volatile SingularAttribute<Value, Boolean> boolVal;
    public static volatile SingularAttribute<Value, ValueDef> valueDefId;
    public static volatile SingularAttribute<Value, Integer> valueDefId_;
    public static volatile SingularAttribute<Value, Item> itemId;
    public static volatile SingularAttribute<Value, Integer> itemId_;
}
