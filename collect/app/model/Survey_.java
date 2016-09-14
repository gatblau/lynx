package model;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.sql.Timestamp;

@StaticMetamodel(Survey.class)
public class Survey_ {
	public static volatile SingularAttribute<Survey, Integer> id;
    public static volatile SingularAttribute<Survey, Timestamp> updated;
    public static volatile SingularAttribute<Survey, Timestamp> created;
    public static volatile SingularAttribute<Survey, Integer> version;
    public static volatile SingularAttribute<Survey, Respondent> lockedBy;
    public static volatile SingularAttribute<Survey, Integer> lockedBy_;
    public static volatile SingularAttribute<Survey, SurveyDef> surveyDefId;
    public static volatile SingularAttribute<Survey, Integer> surveyDefId_;
    public static volatile SetAttribute<Survey, Section> sectionSurveyViaSurveyId;
    public static volatile SetAttribute<Survey, SurveyLang> surveyLangSurveyViaSurveyId;
    public static volatile SetAttribute<Survey, SurveyRespondent> surveyRespondentSurveyViaSurveyId;
}
