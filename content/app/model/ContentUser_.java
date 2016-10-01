package model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.sql.Timestamp;

@StaticMetamodel(ContentUser.class)
public class ContentUser_ {
	public static volatile SingularAttribute<ContentUser, Integer> id;
	public static volatile SingularAttribute<ContentUser, Boolean> canRead;
	public static volatile SingularAttribute<ContentUser, Boolean> canWrite;
	public static volatile SingularAttribute<ContentUser, Timestamp> lastRead;
	public static volatile SingularAttribute<ContentUser, Timestamp> lastWrite;
	public static volatile SingularAttribute<ContentUser, User> userId;
	public static volatile SingularAttribute<ContentUser, Integer> userId_;
	public static volatile SingularAttribute<ContentUser, Content> contentId;
	public static volatile SingularAttribute<ContentUser, Integer> contentId_;
}
