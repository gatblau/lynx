package model;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Role.class)
public class Role_ {
	public static volatile SingularAttribute<Role, Integer> id;
	public static volatile SetAttribute<Role, Respondent> respondentRoleViaRoleId;
    public static volatile SetAttribute<Role, RoleLang> roleLangRoleViaRoleId;
}
