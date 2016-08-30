package model;

import java.sql.*;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import javax.persistence.metamodel.SetAttribute;

@StaticMetamodel(Survey.class)
public class Survey_ {

    public static volatile SingularAttribute<Survey, Integer> id;

    public static volatile SingularAttribute<Survey, Timestamp> updated;
    public static volatile SingularAttribute<Survey, Timestamp> created;
    public static volatile SingularAttribute<Survey, Integer> version;
    public static volatile SingularAttribute<Survey, SurveyDef> surveyDefId;
    public static volatile SingularAttribute<Survey, Integer> surveyDefId_;

    public static volatile SetAttribute<Survey, Section> sectionSurveyViaSurveyId;
    public static volatile SetAttribute<Survey, SurveyRespondent> surveyRespondentSurveyViaSurveyId;


}
