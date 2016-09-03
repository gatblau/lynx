package model;

//MP-MANAGED-ADDED-AREA-BEGINNING @import@
//MP-MANAGED-ADDED-AREA-ENDING @import@
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * <p>Title: Section</p>
 *
 * <p>Description: Domain Object describing a Section entity</p>
 *
 */
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

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @m2m-factDefFactViaId-section@
    @ManyToMany
    @JoinTable(name="FACT", 
        joinColumns=@JoinColumn(name="section_id"), 
        inverseJoinColumns=@JoinColumn(name="fact_def_id") 
    )
    private Set <FactDef> factDefFactViaId = new HashSet <FactDef> ();

//MP-MANAGED-UPDATABLE-ENDING
    /**
    * Default constructor
    */
    public Section() {
    }

	/**
	* All field constructor 
	*/
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
       //primary keys
       setId (id);
       //attributes
       //parents
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
	

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @factViaSectionById-getter-section@
//MP-MANAGED-UPDATABLE-ENDING

    public Set<FactDef> getFactDefFactViaId() {
        if (factDefFactViaId == null){
            factDefFactViaId = new HashSet<FactDef>();
        }
        return factDefFactViaId;
    }

    public void setFactDefFactViaId (Set<FactDef> factDefFactViaId) {
        this.factDefFactViaId = factDefFactViaId;
    }
    	
    public void addFactDefFactViaId (FactDef element) {
        getFactDefFactViaId().add(element);
    }
	


//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@

}
