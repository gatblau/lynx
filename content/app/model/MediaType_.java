package model;

import java.sql.*;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import javax.persistence.metamodel.SetAttribute;

@StaticMetamodel(MediaType.class)
public class MediaType_ {
    public static volatile SingularAttribute<MediaType, Integer> id;
    public static volatile SingularAttribute<MediaType, String> name;
    public static volatile SingularAttribute<MediaType, String> template;
    public static volatile SingularAttribute<MediaType, String> reference;
    public static volatile SingularAttribute<MediaType, String> iconPath;
    public static volatile SetAttribute<MediaType, Resource> resourceMediaTypeViaMediaTypeId;
}
