package model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity (name="Survey")
@Table (name="\"survey\"")
@NamedQueries ({
	 @NamedQuery(name="Survey.findAll", query="SELECT a FROM Survey a")
	,@NamedQuery(name="Survey.findByUpdated", query="SELECT a FROM Survey a WHERE a.updated = :updated")
	,@NamedQuery(name="Survey.findByCreated", query="SELECT a FROM Survey a WHERE a.created = :created")
	,@NamedQuery(name="Survey.findByVersion", query="SELECT a FROM Survey a WHERE a.version = :version")
})
public class Survey implements Serializable {
    private static final long serialVersionUID = 1L;
	public static final Integer __DEFAULT_VERSION = Integer.valueOf(1);

    public static final String FIND_ALL = "Survey.findAll";
    public static final String FIND_BY_UPDATED = "Survey.findByUpdated";
    public static final String FIND_BY_CREATED = "Survey.findByCreated";
    public static final String FIND_BY_VERSION = "Survey.findByVersion";
	
    @Id @Column(name="id" ) 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="updated"   , nullable=true , unique=false)
    private Timestamp updated; 

    @Column(name="created"   , nullable=false , unique=false)
    private Timestamp created; 

    @Column(name="version"   , nullable=false , unique=false)
    private Integer version;

    @ManyToOne (fetch=FetchType.LAZY )
    @JoinColumn(name="locked_by", referencedColumnName = "id" , nullable=true , unique=false , insertable=true, updatable=true) 
    private Respondent lockedBy;  

    @Column(name="locked_by"  , nullable=true , unique=true, insertable=false, updatable=false)
    private Integer lockedBy_;

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="survey_def_id", referencedColumnName = "id" , nullable=false , unique=true  , insertable=true, updatable=true) 
    private SurveyDef surveyDefId;  

    @Column(name="survey_def_id"  , nullable=false , unique=true, insertable=false, updatable=false)
    private Integer surveyDefId_;

    @OneToMany (targetEntity=model.Section.class, fetch=FetchType.LAZY, mappedBy="surveyId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <Section> sectionSurveyViaSurveyId = new HashSet<Section>(); 

    @OneToMany (targetEntity=model.SurveyLang.class, fetch=FetchType.LAZY, mappedBy="surveyId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <SurveyLang> surveyLangSurveyViaSurveyId = new HashSet<SurveyLang>(); 

    @OneToMany (targetEntity=model.SurveyRespondent.class, fetch=FetchType.LAZY, mappedBy="surveyRespondentId__.surveyId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <SurveyRespondent> surveyRespondentSurveyViaSurveyId = new HashSet<SurveyRespondent>(); 

    public Survey() {
    }

    public Survey(
       Integer id,
       Integer surveyDefId,
       Timestamp updated,
       Timestamp created,
       Integer version,
       Integer lockedBy) {
	 this(
       id,
       surveyDefId,
       updated,
       created,
       version,
       lockedBy
	 ,true);
	}
    
	public Survey(
       Integer id,
       Integer surveyDefId,
       Timestamp updated,
       Timestamp created,
       Integer version,
       Integer lockedBy	
    , boolean setRelationship) {
       //primary keys
       setId (id);
       //attributes
       setUpdated (updated);
       setCreated (created);
       setVersion (version);
       //parents
       if (setRelationship && lockedBy!=null) {
          this.lockedBy = new Respondent();
          this.lockedBy.setId(lockedBy);
	      setLockedBy_ (lockedBy);
       }
       if (setRelationship && surveyDefId!=null) {
          this.surveyDefId = new SurveyDef();
          this.surveyDefId.setId(surveyDefId);
	      setSurveyDefId_ (surveyDefId);
       }
    }

	public Survey flat() {
	   return new Survey(
          getId(),
          getSurveyDefId_(),
          getUpdated(),
          getCreated(),
          getVersion(),
          getLockedBy_()
       , false
	   );
	}

    public Integer getId() {
        return id;
    }
	
    public void setId (Integer id) {
        this.id =  id;
    }

    public Timestamp getUpdated() {
        return updated;
    }
	
    public void setUpdated (Timestamp updated) {
        this.updated =  updated;
    }

    public Timestamp getCreated() {
        return created;
    }
	
    public void setCreated (Timestamp created) {
        this.created =  created;
    }

    public Integer getVersion() {
        return version;
    }
	
    public void setVersion (Integer version) {
        this.version =  version;
    }

    public Respondent getLockedBy () {
    	return lockedBy;
    }
	
    public void setLockedBy (Respondent lockedBy) {
    	this.lockedBy = lockedBy;
    }

    public Integer getLockedBy_() {
        return lockedBy_;
    }
	
    public void setLockedBy_ (Integer lockedBy) {
        this.lockedBy_ =  lockedBy;
    }
	
    public SurveyDef getSurveyDefId () {
    	return surveyDefId;
    }
	
    public void setSurveyDefId (SurveyDef surveyDefId) {
    	this.surveyDefId = surveyDefId;
    }

    public Integer getSurveyDefId_() {
        return surveyDefId_;
    }
	
    public void setSurveyDefId_ (Integer surveyDefId) {
        this.surveyDefId_ =  surveyDefId;
    }

    public Set<Section> getSectionSurveyViaSurveyId() {
        if (sectionSurveyViaSurveyId == null){
            sectionSurveyViaSurveyId = new HashSet<Section>();
        }
        return sectionSurveyViaSurveyId;
    }

    public void setSectionSurveyViaSurveyId (Set<Section> sectionSurveyViaSurveyId) {
        this.sectionSurveyViaSurveyId = sectionSurveyViaSurveyId;
    }	
    
    public void addSectionSurveyViaSurveyId (Section element) {
    	    getSectionSurveyViaSurveyId().add(element);
    }

    public Set<SurveyLang> getSurveyLangSurveyViaSurveyId() {
        if (surveyLangSurveyViaSurveyId == null){
            surveyLangSurveyViaSurveyId = new HashSet<SurveyLang>();
        }
        return surveyLangSurveyViaSurveyId;
    }

    public void setSurveyLangSurveyViaSurveyId (Set<SurveyLang> surveyLangSurveyViaSurveyId) {
        this.surveyLangSurveyViaSurveyId = surveyLangSurveyViaSurveyId;
    }	
    
    public void addSurveyLangSurveyViaSurveyId (SurveyLang element) {
    	    getSurveyLangSurveyViaSurveyId().add(element);
    }

    public Set<SurveyRespondent> getSurveyRespondentSurveyViaSurveyId() {
        if (surveyRespondentSurveyViaSurveyId == null){
            surveyRespondentSurveyViaSurveyId = new HashSet<SurveyRespondent>();
        }
        return surveyRespondentSurveyViaSurveyId;
    }

    public void setSurveyRespondentSurveyViaSurveyId (Set<SurveyRespondent> surveyRespondentSurveyViaSurveyId) {
        this.surveyRespondentSurveyViaSurveyId = surveyRespondentSurveyViaSurveyId;
    }	
    
    public void addSurveyRespondentSurveyViaSurveyId (SurveyRespondent element) {
    	    getSurveyRespondentSurveyViaSurveyId().add(element);
    }

    @PrePersist
    public void prePersist_ () {
        if (version==null) version=__DEFAULT_VERSION;
    }

    @PreUpdate
    public void preUpdate_ () {
        if (version==null) version=__DEFAULT_VERSION;
    }
}
