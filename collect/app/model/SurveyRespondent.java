package model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity (name="SurveyRespondent")
@Table (name="\"survey_respondent\"")
@NamedQueries ({
	 @NamedQuery(name="SurveyRespondent.findAll", query="SELECT a FROM SurveyRespondent a")
	,@NamedQuery(name="SurveyRespondent.findByCanRead", query="SELECT a FROM SurveyRespondent a WHERE a.canRead = :canRead")
	,@NamedQuery(name="SurveyRespondent.findByCanWrite", query="SELECT a FROM SurveyRespondent a WHERE a.canWrite = :canWrite")
	,@NamedQuery(name="SurveyRespondent.findByLastRead", query="SELECT a FROM SurveyRespondent a WHERE a.lastRead = :lastRead")
	,@NamedQuery(name="SurveyRespondent.findByLastWrite", query="SELECT a FROM SurveyRespondent a WHERE a.lastWrite = :lastWrite")
})
public class SurveyRespondent implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL = "SurveyRespondent.findAll";
    public static final String FIND_BY_CANREAD = "SurveyRespondent.findByCanRead";
    public static final String FIND_BY_CANWRITE = "SurveyRespondent.findByCanWrite";
    public static final String FIND_BY_LASTREAD = "SurveyRespondent.findByLastRead";
    public static final String FIND_BY_LASTWRITE = "SurveyRespondent.findByLastWrite";
	
    @Id @Column(name="id" ) 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="can_read"   , nullable=false , unique=false)
    private Boolean canRead; 

    @Column(name="can_write"   , nullable=false , unique=false)
    private Boolean canWrite; 

    @Column(name="last_read"   , nullable=true , unique=false)
    private Timestamp lastRead; 

    @Column(name="last_write"   , nullable=true , unique=false)
    private Timestamp lastWrite;

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="respondent_id", referencedColumnName = "id" , nullable=false , unique=false , insertable=true, updatable=true) 
    private Respondent respondentId;  

    @Column(name="respondent_id"  , nullable=false , unique=true, insertable=false, updatable=false)
    private Integer respondentId_;

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="survey_id", referencedColumnName = "id" , nullable=false , unique=true  , insertable=true, updatable=true) 
    private Survey surveyId;  

    @Column(name="survey_id"  , nullable=false , unique=true, insertable=false, updatable=false)
    private Integer surveyId_;

    public SurveyRespondent() {
    }

    public SurveyRespondent(
       Integer id,
       Integer surveyId,
       Integer respondentId,
       Boolean canRead,
       Boolean canWrite,
       Timestamp lastRead,
       Timestamp lastWrite) {
	 this(
       id,
       surveyId,
       respondentId,
       canRead,
       canWrite,
       lastRead,
       lastWrite
	 ,true);
	}
    
	public SurveyRespondent(
       Integer id,
       Integer surveyId,
       Integer respondentId,
       Boolean canRead,
       Boolean canWrite,
       Timestamp lastRead,
       Timestamp lastWrite	
    , boolean setRelationship) {
       setId (id);
       setCanRead (canRead);
       setCanWrite (canWrite);
       setLastRead (lastRead);
       setLastWrite (lastWrite);
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
          getId(),
          getSurveyId_(),
          getRespondentId_(),
          getCanRead(),
          getCanWrite(),
          getLastRead(),
          getLastWrite()
       , false
	   );
	}

    public Integer getId() {
        return id;
    }
	
    public void setId (Integer id) {
        this.id =  id;
    }

    public Boolean getCanRead() {
        return canRead;
    }
	
    public void setCanRead (Boolean canRead) {
        this.canRead =  canRead;
    }

    public Boolean getCanWrite() {
        return canWrite;
    }
	
    public void setCanWrite (Boolean canWrite) {
        this.canWrite =  canWrite;
    }

    public Timestamp getLastRead() {
        return lastRead;
    }
	
    public void setLastRead (Timestamp lastRead) {
        this.lastRead =  lastRead;
    }

    public Timestamp getLastWrite() {
        return lastWrite;
    }
	
    public void setLastWrite (Timestamp lastWrite) {
        this.lastWrite =  lastWrite;
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

    @javax.persistence.PrePersist
    public void prePersist_ () {
    }

    @javax.persistence.PreUpdate
    public void preUpdate_ () {
    }
}
