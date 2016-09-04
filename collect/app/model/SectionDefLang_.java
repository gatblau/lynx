package model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(SectionDefLang.class)
public class SectionDefLang_ {
	public static volatile SingularAttribute<SectionDefLang, Integer> id;
	public static volatile SingularAttribute<SectionDefLang, String> name;
	public static volatile SingularAttribute<SectionDefLang, String> description;
	public static volatile SingularAttribute<SectionDefLang, Language> languageId;
	public static volatile SingularAttribute<SectionDefLang, Integer> languageId_;
	public static volatile SingularAttribute<SectionDefLang, SectionDef> sectionDefId;
	public static volatile SingularAttribute<SectionDefLang, Integer> sectionDefId_;
}
