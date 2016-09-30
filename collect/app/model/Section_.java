package model;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Section.class)
public class Section_ {
	public static volatile SingularAttribute<Section, Integer> id;
	public static volatile SingularAttribute<Section, SectionDef> sectionDefId;
    public static volatile SingularAttribute<Section, Integer> sectionDefId_;
    public static volatile SingularAttribute<Section, Content> contentId;
    public static volatile SingularAttribute<Section, Integer> contentId_;
	public static volatile SetAttribute<Section, Item> itemSectionViaSectionId;
}
