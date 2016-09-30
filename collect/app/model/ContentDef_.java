package model;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ContentDef.class)
public class ContentDef_ {
	public static volatile SingularAttribute<ContentDef, Integer> id;
	public static volatile SetAttribute<ContentDef, SectionDef> sectionDefContentDefViaContentDefId;
    public static volatile SetAttribute<ContentDef, Content> contentContentDefViaContentDefId;
    public static volatile SetAttribute<ContentDef, ContentDefLang> contentDefLangContentDefViaContentDefId;
}
