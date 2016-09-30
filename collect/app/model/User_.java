package model;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(User.class)
public class User_ {
	public static volatile SingularAttribute<User, Integer> id;
    public static volatile SingularAttribute<User, String> firstname;
    public static volatile SingularAttribute<User, String> lastname;
    public static volatile SingularAttribute<User, String> email;
    public static volatile SingularAttribute<User, String> telephone;
    public static volatile SingularAttribute<User, String> pwdHash;
    public static volatile SingularAttribute<User, Boolean> enabled;
    public static volatile SingularAttribute<User, String> activationCode;
    public static volatile SingularAttribute<User, Country> countryId;
    public static volatile SingularAttribute<User, Integer> countryId_;
    public static volatile SingularAttribute<User, Group> groupId;
    public static volatile SingularAttribute<User, Integer> groupId_;
    public static volatile SingularAttribute<User, Language> preferredLanguageId;
    public static volatile SingularAttribute<User, Integer> preferredLanguageId_;
    public static volatile SingularAttribute<User, Role> roleId;
    public static volatile SingularAttribute<User, Integer> roleId_;
    public static volatile SetAttribute<User, Content> contentUserViaLockedBy;
    public static volatile SetAttribute<User, ContentAdmin> contentAdminUserViaUserId;
    public static volatile SetAttribute<User, ContentUser> contentUserUserViaUserId;
}
