package model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.sql.Timestamp;

@StaticMetamodel(SurveyRespondent.class)
public class SurveyRespondent_ {
	public static volatile SingularAttribute<SurveyRespondent, Integer> id;
	public static volatile SingularAttribute<SurveyRespondent, Boolean> canRead;
	public static volatile SingularAttribute<SurveyRespondent, Boolean> canWrite;
	public static volatile SingularAttribute<SurveyRespondent, Timestamp> lastRead;
	public static volatile SingularAttribute<SurveyRespondent, Timestamp> lastWrite;
	public static volatile SingularAttribute<SurveyRespondent, Respondent> respondentId;
	public static volatile SingularAttribute<SurveyRespondent, Integer> respondentId_;
	public static volatile SingularAttribute<SurveyRespondent, Survey> surveyId;
	public static volatile SingularAttribute<SurveyRespondent, Integer> surveyId_;
}
