package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity (name="Section")
@Table (name="\"section\"")
@NamedQueries ({
	 @NamedQuery(name="Section.findAll", query="SELECT a FROM Section a")
})
public class Section implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL = "Section.findAll";
	
    @Id @Column(name="id" ) 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="section_def_id", referencedColumnName = "id" , nullable=false , unique=false , insertable=true, updatable=true) 
    private SectionDef sectionDefId;  

    @Column(name="section_def_id"  , nullable=false , unique=true, insertable=false, updatable=false)
    private Integer sectionDefId_;

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="survey_id", referencedColumnName = "id" , nullable=false , unique=true  , insertable=true, updatable=true) 
    private Survey surveyId;  

    @Column(name="survey_id"  , nullable=false , unique=true, insertable=false, updatable=false)
    private Integer surveyId_;

    @OneToMany (targetEntity=model.Fact.class, fetch=FetchType.LAZY, mappedBy="sectionId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <Fact> factSectionViaSectionId = new HashSet<Fact>(); 

    public Section() {
    }

    public Section(
       Integer id,
       Integer sectionDefId,
       Integer surveyId) {
	 this(
       id,
       sectionDefId,
       surveyId
	 ,true);
	}
    
	public Section(
       Integer id,
       Integer sectionDefId,
       Integer surveyId	
    , boolean setRelationship) {
       setId (id);
       if (setRelationship && sectionDefId!=null) {
          this.sectionDefId = new SectionDef();
          this.sectionDefId.setId(sectionDefId);
       }
       if (setRelationship && surveyId!=null) {
          this.surveyId = new Survey();
          this.surveyId.setId(surveyId);
       }
    }

	public Section flat() {
	   return new Section(
          getId(),
          getSectionDefId_(),
          getSurveyId_()
       , false
	   );
	}

    public Integer getId() {
        return id;
    }
	
    public void setId (Integer id) {
        this.id =  id;
    }
    

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

    public Set<Fact> getFactSectionViaSectionId() {
        if (factSectionViaSectionId == null){
            factSectionViaSectionId = new HashSet<Fact>();
        }
        return factSectionViaSectionId;
    }

    public void setFactSectionViaSectionId (Set<Fact> factSectionViaSectionId) {
        this.factSectionViaSectionId = factSectionViaSectionId;
    }	
    
    public void addFactSectionViaSectionId (Fact element) {
    	    getFactSectionViaSectionId().add(element);
    }
}
