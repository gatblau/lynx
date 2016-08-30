package model;

//MP-MANAGED-ADDED-AREA-BEGINNING @import@
//MP-MANAGED-ADDED-AREA-ENDING @import@
import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * <p>Title: SurveyRespondent</p>
 *
 * <p>Description: Domain Object describing a SurveyRespondent entity</p>
 *
 */
@Entity (name="SurveyRespondent")
@Table (name="\"survey_respondent\"")
@NamedQueries ({
	 @NamedQuery(name="SurveyRespondent.findAll", query="SELECT a FROM SurveyRespondent a")
})

public class SurveyRespondent implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL = "SurveyRespondent.findAll";
	
    @EmbeddedId
    public SurveyRespondentId surveyRespondentId__;     @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="respondent_id", referencedColumnName = "id" , nullable=false , unique=true  , insertable=true, updatable=true) 
    private Respondent respondentId;  

    @Column(name="respondent_id"  , nullable=false , unique=true, insertable=false, updatable=false)
    private Integer respondentId_;

    @MapsId ("survey_id")
    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="survey_id", referencedColumnName = "id" , nullable=false , unique=true  , insertable=true, updatable=true) 
    private Survey surveyId;  

    @Column(name="survey_id"  , nullable=false , unique=true, insertable=false, updatable=false)
    private Integer surveyId_;

    /**
    * Default constructor
    */
    public SurveyRespondent() {
    }

	/**
	* All field constructor 
	*/
    public SurveyRespondent(
       Integer surveyId,
       Integer respondentId) {
	 this(
       surveyId,
       respondentId
	 ,true);
	}
    
	public SurveyRespondent(
       Integer surveyId,
       Integer respondentId	
    , boolean setRelationship) {
       //primary keys
       this.surveyRespondentId__ = new SurveyRespondentId();  	
       //attributes
       //parents
       if (setRelationship && respondentId!=null) {
          this.respondentId = new Respondent();
          this.respondentId.setId(respondentId);
	      setRespondentId_ (respondentId);
       }
       if (setRelationship && surveyId!=null) {
          this.surveyId = new Survey();
          this.surveyId.setId(surveyId);
	      setSurveyId_ (surveyId);
       }
    }

	public SurveyRespondent flat() {
	   return new SurveyRespondent(
		  getSurveyRespondentId__().getSurveyId(),
          getRespondentId_()
       , false
	   );
	}


    public SurveyRespondentId getSurveyRespondentId__() {
		if (surveyRespondentId__==null) surveyRespondentId__ = new SurveyRespondentId();
        return surveyRespondentId__;
    }
	
    public void setSurveyRespondentId__ (SurveyRespondentId surveyRespondentId__) {
        this.surveyRespondentId__ =  surveyRespondentId__;
    }

    public Respondent getRespondentId () {
    	return respondentId;
    }
	
    public void setRespondentId (Respondent respondentId) {
    	this.respondentId = respondentId;
    }

    public Integer getRespondentId_() {
        return respondentId_;
    }
	
    public void setRespondentId_ (Integer respondentId) {
        this.respondentId_ =  respondentId;
    }
	
    public Survey getSurveyId () {
    	return surveyId;
    }
	
    public void setSurveyId (Survey surveyId) {
    	this.surveyId = surveyId;
    }

    public Integer getSurveyId_() {
        return surveyId_;
    }
	
    public void setSurveyId_ (Integer surveyId) {
        this.surveyId_ =  surveyId;
    }
	




//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@

}
