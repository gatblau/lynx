package model;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.sql.Timestamp;

@StaticMetamodel(Content.class)
public class Content_ {
	public static volatile SingularAttribute<Content, Integer> id;
    public static volatile SingularAttribute<Content, Timestamp> updated;
    public static volatile SingularAttribute<Content, Timestamp> created;
    public static volatile SingularAttribute<Content, Integer> version;
    public static volatile SingularAttribute<Content, User> lockedBy;
    public static volatile SingularAttribute<Content, Integer> lockedBy_;
    public static volatile SingularAttribute<Content, ContentDef> contentDefId;
    public static volatile SingularAttribute<Content, Integer> contentDefId_;
    public static volatile SetAttribute<Content, Section> sectionContentViaContentId;
    public static volatile SetAttribute<Content, ContentLang> contentLangContentViaContentId;
    public static volatile SetAttribute<Content, ContentUser> contentUserContentViaContentId;
}
