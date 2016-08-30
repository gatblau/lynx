package model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(SurveyRespondent.class)
public class SurveyRespondent_ {

    public static volatile SingularAttribute<SurveyRespondent, SurveyRespondentId> surveyRespondentId__;

    public static volatile SingularAttribute<SurveyRespondent, Respondent> respondentId;
    public static volatile SingularAttribute<SurveyRespondent, Integer> respondentId_;
    public static volatile SingularAttribute<SurveyRespondent, Survey> surveyId;
    public static volatile SingularAttribute<SurveyRespondent, Integer> surveyId_;



}
