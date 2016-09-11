package model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(SurveyAdmin.class)
public class SurveyAdmin_ {
	public static volatile SingularAttribute<SurveyAdmin, Integer> id;
	public static volatile SingularAttribute<SurveyAdmin, Respondent> respondentId;
	public static volatile SingularAttribute<SurveyAdmin, Integer> respondentId_;
	public static volatile SingularAttribute<SurveyAdmin, SurveyDef> surveyDefId;
	public static volatile SingularAttribute<SurveyAdmin, Integer> surveyDefId_;
}
