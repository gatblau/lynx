package model;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Group.class)
public class Group_ {
	public static volatile SingularAttribute<Group, Integer> id;
	public static volatile SingularAttribute<Group, String> name;
	public static volatile SetAttribute<Group, User> userGroupViaGroupId;
}
