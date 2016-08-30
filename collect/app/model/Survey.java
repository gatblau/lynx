package model;

//MP-MANAGED-ADDED-AREA-BEGINNING @import@
//MP-MANAGED-ADDED-AREA-ENDING @import@
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * <p>Title: Survey</p>
 *
 * <p>Description: Domain Object describing a Survey entity</p>
 *
 */
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

//MP-MANAGED-ADDED-AREA-BEGINNING @updated-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @updated-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-updated@
    @Column(name="updated"   , nullable=true , unique=false)
    private Timestamp updated; 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @created-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @created-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-created@
    @Column(name="created"   , nullable=false , unique=false)
    private Timestamp created; 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @version-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @version-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-version@
    @Column(name="version"   , nullable=false , unique=false)
    private Integer version; 
//MP-MANAGED-UPDATABLE-ENDING

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="survey_def_id", referencedColumnName = "id" , nullable=false , unique=false , insertable=true, updatable=true) 
    private SurveyDef surveyDefId;  

    @Column(name="survey_def_id"  , nullable=false , unique=true, insertable=false, updatable=false)
    private Integer surveyDefId_;

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @sectionSurveyViaSurveyId-field-survey@
    @OneToMany (targetEntity=model.Section.class, fetch=FetchType.LAZY, mappedBy="surveyId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <Section> sectionSurveyViaSurveyId = new HashSet<Section>(); 

//MP-MANAGED-UPDATABLE-ENDING
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @surveyRespondentSurveyViaSurveyId-field-survey@
    @OneToMany (targetEntity=model.SurveyRespondent.class, fetch=FetchType.LAZY, mappedBy="surveyRespondentId__.surveyId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <SurveyRespondent> surveyRespondentSurveyViaSurveyId = new HashSet<SurveyRespondent>(); 

//MP-MANAGED-UPDATABLE-ENDING
    /**
    * Default constructor
    */
    public Survey() {
    }

	/**
	* All field constructor 
	*/
    public Survey(
       Integer id,
       Integer surveyDefId,
       Timestamp updated,
       Timestamp created,
       Integer version) {
	 this(
       id,
       surveyDefId,
       updated,
       created,
       version
	 ,true);
	}
    
	public Survey(
       Integer id,
       Integer surveyDefId,
       Timestamp updated,
       Timestamp created,
       Integer version	
    , boolean setRelationship) {
       //primary keys
       setId (id);
       //attributes
       setUpdated (updated);
       setCreated (created);
       setVersion (version);
       //parents
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
          getVersion()
       , false
	   );
	}

    public Integer getId() {
        return id;
    }
	
    public void setId (Integer id) {
        this.id =  id;
    }
    
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-updated@
    public Timestamp getUpdated() {
        return updated;
    }
	
    public void setUpdated (Timestamp updated) {
        this.updated =  updated;
    }
	
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-created@
    public Timestamp getCreated() {
        return created;
    }
	
    public void setCreated (Timestamp created) {
        this.created =  created;
    }
	
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-version@
    public Integer getVersion() {
        return version;
    }
	
    public void setVersion (Integer version) {
        this.version =  version;
    }
	
//MP-MANAGED-UPDATABLE-ENDING


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
	

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @sectionSurveyViaSurveyId-getter-survey@
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
    
//MP-MANAGED-UPDATABLE-ENDING
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @surveyRespondentSurveyViaSurveyId-getter-survey@
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
    
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @prepersist-survey@
    @javax.persistence.PrePersist
    public void prePersist_ () {
        if (version==null) version=__DEFAULT_VERSION;
    }
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @preupdate-survey@
    @javax.persistence.PreUpdate
    public void preUpdate_ () {
        if (version==null) version=__DEFAULT_VERSION;
    }
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@

}
