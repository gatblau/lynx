package model;

//MP-MANAGED-ADDED-AREA-BEGINNING @import@
//MP-MANAGED-ADDED-AREA-ENDING @import@
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * <p>Title: SectionDef</p>
 *
 * <p>Description: Domain Object describing a SectionDef entity</p>
 *
 */
@Entity (name="SectionDef")
@Table (name="\"section_def\"")
@NamedQueries ({
	 @NamedQuery(name="SectionDef.findAll", query="SELECT a FROM SectionDef a")
	,@NamedQuery(name="SectionDef.findByStatic_Name", query="SELECT a FROM SectionDef a WHERE a.static_Name = :static_Name")

})

public class SectionDef implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL = "SectionDef.findAll";
    public static final String FIND_BY_STATIC_NAME = "SectionDef.findByStatic_Name";
	
    @Id @Column(name="id" ) 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

//MP-MANAGED-ADDED-AREA-BEGINNING @static-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @static-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-static@
    @Column(name="static"   , nullable=true , unique=false)
    private Boolean static_Name; 
//MP-MANAGED-UPDATABLE-ENDING

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="section_def_id", referencedColumnName = "id" , nullable=false , unique=false , insertable=true, updatable=true) 
    private SectionDef sectionDefId;  

    @Column(name="section_def_id"  , nullable=false , unique=true, insertable=false, updatable=false)
    private Integer sectionDefId_;

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="survey_def_id", referencedColumnName = "id" , nullable=false , unique=true  , insertable=true, updatable=true) 
    private SurveyDef surveyDefId;  

    @Column(name="survey_def_id"  , nullable=false , unique=true, insertable=false, updatable=false)
    private Integer surveyDefId_;

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @factDefSectionDefViaSectionDefId-field-section_def@
    @OneToMany (targetEntity=model.FactDef.class, fetch=FetchType.LAZY, mappedBy="sectionDefId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <FactDef> factDefSectionDefViaSectionDefId = new HashSet<FactDef>(); 

//MP-MANAGED-UPDATABLE-ENDING
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @sectionSectionDefViaSectionDefId-field-section_def@
    @OneToMany (targetEntity=model.Section.class, fetch=FetchType.LAZY, mappedBy="sectionDefId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <Section> sectionSectionDefViaSectionDefId = new HashSet<Section>(); 

//MP-MANAGED-UPDATABLE-ENDING
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @sectionDefSectionDefViaSectionDefId-field-section_def@
    @OneToMany (targetEntity=model.SectionDef.class, fetch=FetchType.LAZY, mappedBy="sectionDefId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <SectionDef> sectionDefSectionDefViaSectionDefId = new HashSet<SectionDef>(); 

//MP-MANAGED-UPDATABLE-ENDING
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @sectionDefLangSectionDefViaSectionDefId-field-section_def@
    @OneToMany (targetEntity=model.SectionDefLang.class, fetch=FetchType.LAZY, mappedBy="sectionDefId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <SectionDefLang> sectionDefLangSectionDefViaSectionDefId = new HashSet<SectionDefLang>(); 

//MP-MANAGED-UPDATABLE-ENDING
    /**
    * Default constructor
    */
    public SectionDef() {
    }

	/**
	* All field constructor 
	*/
    public SectionDef(
       Integer id,
       Boolean static_Name,
       Integer surveyDefId,
       Integer sectionDefId) {
	 this(
       id,
       static_Name,
       surveyDefId,
       sectionDefId
	 ,true);
	}
    
	public SectionDef(
       Integer id,
       Boolean static_Name,
       Integer surveyDefId,
       Integer sectionDefId	
    , boolean setRelationship) {
       //primary keys
       setId (id);
       //attributes
       setStatic_Name (static_Name);
       //parents
       if (setRelationship && sectionDefId!=null) {
          this.sectionDefId = new SectionDef();
          this.sectionDefId.setId(sectionDefId);
	      setSectionDefId_ (sectionDefId);
       }
       if (setRelationship && surveyDefId!=null) {
          this.surveyDefId = new SurveyDef();
          this.surveyDefId.setId(surveyDefId);
	      setSurveyDefId_ (surveyDefId);
       }
    }

	public SectionDef flat() {
	   return new SectionDef(
          getId(),
          getStatic_Name(),
          getSurveyDefId_(),
          getSectionDefId_()
       , false
	   );
	}

    public Integer getId() {
        return id;
    }
	
    public void setId (Integer id) {
        this.id =  id;
    }
    
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-static@
    public Boolean getStatic_Name() {
        return static_Name;
    }
	
    public void setStatic_Name (Boolean static_Name) {
        this.static_Name =  static_Name;
    }
	
//MP-MANAGED-UPDATABLE-ENDING


    public SectionDef getSectionDefId () {
    	return sectionDefId;
    }
	
    public void setSectionDefId (SectionDef sectionDefId) {
    	this.sectionDefId = sectionDefId;
    }

    public Integer getSectionDefId_() {
        return sectionDefId_;
    }
	
    public void setSectionDefId_ (Integer sectionDefId) {
        this.sectionDefId_ =  sectionDefId;
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
	

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @factDefSectionDefViaSectionDefId-getter-section_def@
    public Set<FactDef> getFactDefSectionDefViaSectionDefId() {
        if (factDefSectionDefViaSectionDefId == null){
            factDefSectionDefViaSectionDefId = new HashSet<FactDef>();
        }
        return factDefSectionDefViaSectionDefId;
    }

    public void setFactDefSectionDefViaSectionDefId (Set<FactDef> factDefSectionDefViaSectionDefId) {
        this.factDefSectionDefViaSectionDefId = factDefSectionDefViaSectionDefId;
    }	
    
    public void addFactDefSectionDefViaSectionDefId (FactDef element) {
    	    getFactDefSectionDefViaSectionDefId().add(element);
    }
    
//MP-MANAGED-UPDATABLE-ENDING
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @sectionSectionDefViaSectionDefId-getter-section_def@
    public Set<Section> getSectionSectionDefViaSectionDefId() {
        if (sectionSectionDefViaSectionDefId == null){
            sectionSectionDefViaSectionDefId = new HashSet<Section>();
        }
        return sectionSectionDefViaSectionDefId;
    }

    public void setSectionSectionDefViaSectionDefId (Set<Section> sectionSectionDefViaSectionDefId) {
        this.sectionSectionDefViaSectionDefId = sectionSectionDefViaSectionDefId;
    }	
    
    public void addSectionSectionDefViaSectionDefId (Section element) {
    	    getSectionSectionDefViaSectionDefId().add(element);
    }
    
//MP-MANAGED-UPDATABLE-ENDING
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @sectionDefSectionDefViaSectionDefId-getter-section_def@
    public Set<SectionDef> getSectionDefSectionDefViaSectionDefId() {
        if (sectionDefSectionDefViaSectionDefId == null){
            sectionDefSectionDefViaSectionDefId = new HashSet<SectionDef>();
        }
        return sectionDefSectionDefViaSectionDefId;
    }

    public void setSectionDefSectionDefViaSectionDefId (Set<SectionDef> sectionDefSectionDefViaSectionDefId) {
        this.sectionDefSectionDefViaSectionDefId = sectionDefSectionDefViaSectionDefId;
    }	
    
    public void addSectionDefSectionDefViaSectionDefId (SectionDef element) {
    	    getSectionDefSectionDefViaSectionDefId().add(element);
    }
    
//MP-MANAGED-UPDATABLE-ENDING
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @sectionDefLangSectionDefViaSectionDefId-getter-section_def@
    public Set<SectionDefLang> getSectionDefLangSectionDefViaSectionDefId() {
        if (sectionDefLangSectionDefViaSectionDefId == null){
            sectionDefLangSectionDefViaSectionDefId = new HashSet<SectionDefLang>();
        }
        return sectionDefLangSectionDefViaSectionDefId;
    }

    public void setSectionDefLangSectionDefViaSectionDefId (Set<SectionDefLang> sectionDefLangSectionDefViaSectionDefId) {
        this.sectionDefLangSectionDefViaSectionDefId = sectionDefLangSectionDefViaSectionDefId;
    }	
    
    public void addSectionDefLangSectionDefViaSectionDefId (SectionDefLang element) {
    	    getSectionDefLangSectionDefViaSectionDefId().add(element);
    }
    
//MP-MANAGED-UPDATABLE-ENDING



//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@

}
