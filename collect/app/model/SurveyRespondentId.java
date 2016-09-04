package model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class SurveyRespondentId implements Serializable {


    @Column(name="survey_id"  ,nullable=false)
    private Integer surveyId;

	public Integer getSurveyId() {
        return surveyId;
    }
	
    public void setSurveyId (Integer surveyId) {
        this.surveyId = surveyId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        return obj.toString().equals(this.toString());
    }
 
    @Override
    public int hashCode() {
        return toString().hashCode();
    }
 
    @Override
    public String toString() {
        return "SurveyRespondentId:" 
        + ":" + surveyId
        ;
    }
}
