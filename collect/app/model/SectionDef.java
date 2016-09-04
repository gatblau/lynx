package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity (name="SectionDef")
@Table (name="\"section_def\"")
@NamedQueries ({
	 @NamedQuery(name="SectionDef.findAll", query="SELECT a FROM SectionDef a")
	,@NamedQuery(name="SectionDef.findByDynamic", query="SELECT a FROM SectionDef a WHERE a.dynamic = :dynamic")
})
public class SectionDef implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL = "SectionDef.findAll";
    public static final String FIND_BY_DYNAMIC = "SectionDef.findByDynamic";
	
    @Id @Column(name="id" ) 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="dynamic"   , nullable=false , unique=false)
    private Boolean dynamic;

    @ManyToOne (fetch=FetchType.LAZY )
    @JoinColumn(name="parent_section_def_id", referencedColumnName = "id" , nullable=true , unique=false , insertable=true, updatable=true) 
    private SectionDef parentSectionDefId;  

    @Column(name="parent_section_def_id"  , nullable=true , unique=true, insertable=false, updatable=false)
    private Integer parentSectionDefId_;

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="survey_def_id", referencedColumnName = "id" , nullable=false , unique=true  , insertable=true, updatable=true) 
    private SurveyDef surveyDefId;  

    @Column(name="survey_def_id"  , nullable=false , unique=true, insertable=false, updatable=false)
    private Integer surveyDefId_;

    @OneToMany (targetEntity=model.FactDef.class, fetch=FetchType.LAZY, mappedBy="sectionDefId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <FactDef> factDefSectionDefViaSectionDefId = new HashSet<FactDef>(); 

    @OneToMany (targetEntity=model.Section.class, fetch=FetchType.LAZY, mappedBy="sectionDefId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <Section> sectionSectionDefViaSectionDefId = new HashSet<Section>(); 

    @OneToMany (targetEntity=model.SectionDef.class, fetch=FetchType.LAZY, mappedBy="parentSectionDefId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <SectionDef> sectionDefSectionDefViaParentSectionDefId = new HashSet<SectionDef>(); 

    @OneToMany (targetEntity=model.SectionDefLang.class, fetch=FetchType.LAZY, mappedBy="sectionDefId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <SectionDefLang> sectionDefLangSectionDefViaSectionDefId = new HashSet<SectionDefLang>(); 

    public SectionDef() {
    }

    public SectionDef(
       Integer id,
       Boolean dynamic,
       Integer surveyDefId,
       Integer parentSectionDefId) {
	 this(
       id,
       dynamic,
       surveyDefId,
       parentSectionDefId
	 ,true);
	}
    
	public SectionDef(
       Integer id,
       Boolean dynamic,
       Integer surveyDefId,
       Integer parentSectionDefId	
    , boolean setRelationship) {
       setId (id);
       setDynamic (dynamic);
       if (setRelationship && parentSectionDefId!=null) {
          this.parentSectionDefId = new SectionDef();
          this.parentSectionDefId.setId(parentSectionDefId);
	      setParentSectionDefId_ (parentSectionDefId);
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
          getDynamic(),
          getSurveyDefId_(),
          getParentSectionDefId_()
       , false
	   );
	}

    public Integer getId() {
        return id;
    }
	
    public void setId (Integer id) {
        this.id =  id;
    }

    public Boolean getDynamic() {
        return dynamic;
    }
	
    public void setDynamic (Boolean dynamic) {
        this.dynamic =  dynamic;
    }

    public SectionDef getParentSectionDefId () {
    	return parentSectionDefId;
    }
	
    public void setParentSectionDefId (SectionDef parentSectionDefId) {
    	this.parentSectionDefId = parentSectionDefId;
    }

    public Integer getParentSectionDefId_() {
        return parentSectionDefId_;
    }
	
    public void setParentSectionDefId_ (Integer parentSectionDefId) {
        this.parentSectionDefId_ =  parentSectionDefId;
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

    public Set<SectionDef> getSectionDefSectionDefViaParentSectionDefId() {
        if (sectionDefSectionDefViaParentSectionDefId == null){
            sectionDefSectionDefViaParentSectionDefId = new HashSet<SectionDef>();
        }
        return sectionDefSectionDefViaParentSectionDefId;
    }

    public void setSectionDefSectionDefViaParentSectionDefId (Set<SectionDef> sectionDefSectionDefViaParentSectionDefId) {
        this.sectionDefSectionDefViaParentSectionDefId = sectionDefSectionDefViaParentSectionDefId;
    }	
    
    public void addSectionDefSectionDefViaParentSectionDefId (SectionDef element) {
    	    getSectionDefSectionDefViaParentSectionDefId().add(element);
    }

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

    @PrePersist
    public void prePersist_ () {
    }

    @PreUpdate
    public void preUpdate_ () {
    }
}
