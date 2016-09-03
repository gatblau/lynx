package model;

//MP-MANAGED-ADDED-AREA-BEGINNING @import@
//MP-MANAGED-ADDED-AREA-ENDING @import@
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * <p>Title: SurveyDef</p>
 *
 * <p>Description: Domain Object describing a SurveyDef entity</p>
 *
 */
@Entity (name="SurveyDef")
@Table (name="\"survey_def\"")
@NamedQueries ({
	 @NamedQuery(name="SurveyDef.findAll", query="SELECT a FROM SurveyDef a")
})

public class SurveyDef implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL = "SurveyDef.findAll";
	
    @Id @Column(name="id" ) 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @sectionDefSurveyDefViaSurveyDefId-field-survey_def@
    @OneToMany (targetEntity=model.SectionDef.class, fetch=FetchType.LAZY, mappedBy="surveyDefId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <SectionDef> sectionDefSurveyDefViaSurveyDefId = new HashSet<SectionDef>(); 

//MP-MANAGED-UPDATABLE-ENDING
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @surveySurveyDefViaSurveyDefId-field-survey_def@
    @OneToMany (targetEntity=model.Survey.class, fetch=FetchType.LAZY, mappedBy="surveyDefId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <Survey> surveySurveyDefViaSurveyDefId = new HashSet<Survey>(); 

//MP-MANAGED-UPDATABLE-ENDING
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @surveyDefLangSurveyDefViaSurveyDefId-field-survey_def@
    @OneToMany (targetEntity=model.SurveyDefLang.class, fetch=FetchType.LAZY, mappedBy="surveyDefId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <SurveyDefLang> surveyDefLangSurveyDefViaSurveyDefId = new HashSet<SurveyDefLang>(); 

//MP-MANAGED-UPDATABLE-ENDING
    /**
    * Default constructor
    */
    public SurveyDef() {
    }

	/**
	* All field constructor 
	*/
    public SurveyDef(
       Integer id) {
	 this(
       id
	 ,true);
	}
    
	public SurveyDef(
       Integer id	
    , boolean setRelationship) {
       //primary keys
       setId (id);
       //attributes
       //parents
    }

	public SurveyDef flat() {
	   return new SurveyDef(
          getId()
       , false
	   );
	}

    public Integer getId() {
        return id;
    }
	
    public void setId (Integer id) {
        this.id =  id;
    }
    


//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @sectionDefSurveyDefViaSurveyDefId-getter-survey_def@
    public Set<SectionDef> getSectionDefSurveyDefViaSurveyDefId() {
        if (sectionDefSurveyDefViaSurveyDefId == null){
            sectionDefSurveyDefViaSurveyDefId = new HashSet<SectionDef>();
        }
        return sectionDefSurveyDefViaSurveyDefId;
    }

    public void setSectionDefSurveyDefViaSurveyDefId (Set<SectionDef> sectionDefSurveyDefViaSurveyDefId) {
        this.sectionDefSurveyDefViaSurveyDefId = sectionDefSurveyDefViaSurveyDefId;
    }	
    
    public void addSectionDefSurveyDefViaSurveyDefId (SectionDef element) {
    	    getSectionDefSurveyDefViaSurveyDefId().add(element);
    }
    
//MP-MANAGED-UPDATABLE-ENDING
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @surveySurveyDefViaSurveyDefId-getter-survey_def@
    public Set<Survey> getSurveySurveyDefViaSurveyDefId() {
        if (surveySurveyDefViaSurveyDefId == null){
            surveySurveyDefViaSurveyDefId = new HashSet<Survey>();
        }
        return surveySurveyDefViaSurveyDefId;
    }

    public void setSurveySurveyDefViaSurveyDefId (Set<Survey> surveySurveyDefViaSurveyDefId) {
        this.surveySurveyDefViaSurveyDefId = surveySurveyDefViaSurveyDefId;
    }	
    
    public void addSurveySurveyDefViaSurveyDefId (Survey element) {
    	    getSurveySurveyDefViaSurveyDefId().add(element);
    }
    
//MP-MANAGED-UPDATABLE-ENDING
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @surveyDefLangSurveyDefViaSurveyDefId-getter-survey_def@
    public Set<SurveyDefLang> getSurveyDefLangSurveyDefViaSurveyDefId() {
        if (surveyDefLangSurveyDefViaSurveyDefId == null){
            surveyDefLangSurveyDefViaSurveyDefId = new HashSet<SurveyDefLang>();
        }
        return surveyDefLangSurveyDefViaSurveyDefId;
    }

    public void setSurveyDefLangSurveyDefViaSurveyDefId (Set<SurveyDefLang> surveyDefLangSurveyDefViaSurveyDefId) {
        this.surveyDefLangSurveyDefViaSurveyDefId = surveyDefLangSurveyDefViaSurveyDefId;
    }	
    
    public void addSurveyDefLangSurveyDefViaSurveyDefId (SurveyDefLang element) {
    	    getSurveyDefLangSurveyDefViaSurveyDefId().add(element);
    }
    
//MP-MANAGED-UPDATABLE-ENDING



//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@

}
