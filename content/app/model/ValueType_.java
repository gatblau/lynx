package model;

import java.sql.*;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import javax.persistence.metamodel.SetAttribute;

@StaticMetamodel(ValueType.class)
public class ValueType_ {
    public static volatile SingularAttribute<ValueType, Integer> id;
    public static volatile SingularAttribute<ValueType, String> name;
    public static volatile SetAttribute<ValueType, ValueDef> valueDefValueTypeViaValueTypeId;
}
