package model;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(SectionDef.class)
public class SectionDef_ {

    public static volatile SingularAttribute<SectionDef, Integer> id;

    public static volatile SingularAttribute<SectionDef, Boolean> static_Name;
    public static volatile SingularAttribute<SectionDef, SectionDef> sectionDefId;
    public static volatile SingularAttribute<SectionDef, Integer> sectionDefId_;
    public static volatile SingularAttribute<SectionDef, SurveyDef> surveyDefId;
    public static volatile SingularAttribute<SectionDef, Integer> surveyDefId_;

    public static volatile SetAttribute<SectionDef, FactDef> factDefSectionDefViaSectionDefId;
    public static volatile SetAttribute<SectionDef, Section> sectionSectionDefViaSectionDefId;
    public static volatile SetAttribute<SectionDef, SectionDef> sectionDefSectionDefViaSectionDefId;
    public static volatile SetAttribute<SectionDef, SectionDefLang> sectionDefLangSectionDefViaSectionDefId;


}
