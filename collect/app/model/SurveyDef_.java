package model;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(SurveyDef.class)
public class SurveyDef_ {
	public static volatile SingularAttribute<SurveyDef, Integer> id;
	public static volatile SetAttribute<SurveyDef, SectionDef> sectionDefSurveyDefViaSurveyDefId;
    public static volatile SetAttribute<SurveyDef, Survey> surveySurveyDefViaSurveyDefId;
    public static volatile SetAttribute<SurveyDef, SurveyDefLang> surveyDefLangSurveyDefViaSurveyDefId;
}
