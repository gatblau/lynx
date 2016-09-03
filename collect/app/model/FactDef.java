package model;

//MP-MANAGED-ADDED-AREA-BEGINNING @import@
//MP-MANAGED-ADDED-AREA-ENDING @import@
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * <p>Title: FactDef</p>
 *
 * <p>Description: Domain Object describing a FactDef entity</p>
 *
 */
@Entity (name="FactDef")
@Table (name="\"fact_def\"")
@NamedQueries ({
	 @NamedQuery(name="FactDef.findAll", query="SELECT a FROM FactDef a")
})

public class FactDef implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL = "FactDef.findAll";
	
    @Id @Column(name="id" ) 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="section_def_id", referencedColumnName = "id" , nullable=false , unique=false , insertable=true, updatable=true) 
    private SectionDef sectionDefId;  

    @Column(name="section_def_id"  , nullable=false , unique=true, insertable=false, updatable=false)
    private Integer sectionDefId_;

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @factDefLangFactDefViaFactDefId-field-fact_def@
    @OneToMany (targetEntity=model.FactDefLang.class, fetch=FetchType.LAZY, mappedBy="factDefId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <FactDefLang> factDefLangFactDefViaFactDefId = new HashSet<FactDefLang>(); 

//MP-MANAGED-UPDATABLE-ENDING
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @optionDefFactDefViaFactDefId-field-fact_def@
    @OneToMany (targetEntity=model.OptionDef.class, fetch=FetchType.LAZY, mappedBy="factDefId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <OptionDef> optionDefFactDefViaFactDefId = new HashSet<OptionDef>(); 

//MP-MANAGED-UPDATABLE-ENDING
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @m2m-sectionFactViaId-fact_def@
    @ManyToMany
    @JoinTable(name="FACT", 
        joinColumns=@JoinColumn(name="fact_def_id"), 
        inverseJoinColumns=@JoinColumn(name="section_id") 
    )
    private Set <Section> sectionFactViaId = new HashSet <Section> ();

//MP-MANAGED-UPDATABLE-ENDING
    /**
    * Default constructor
    */
    public FactDef() {
    }

	/**
	* All field constructor 
	*/
    public FactDef(
       Integer id,
       Integer sectionDefId) {
	 this(
       id,
       sectionDefId
	 ,true);
	}
    
	public FactDef(
       Integer id,
       Integer sectionDefId	
    , boolean setRelationship) {
       //primary keys
       setId (id);
       //attributes
       //parents
       if (setRelationship && sectionDefId!=null) {
          this.sectionDefId = new SectionDef();
          this.sectionDefId.setId(sectionDefId);
       }
    }

	public FactDef flat() {
	   return new FactDef(
          getId(),
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
	

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @factViaFactDefById-getter-fact_def@
//MP-MANAGED-UPDATABLE-ENDING
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @factDefLangFactDefViaFactDefId-getter-fact_def@
    public Set<FactDefLang> getFactDefLangFactDefViaFactDefId() {
        if (factDefLangFactDefViaFactDefId == null){
            factDefLangFactDefViaFactDefId = new HashSet<FactDefLang>();
        }
        return factDefLangFactDefViaFactDefId;
    }

    public void setFactDefLangFactDefViaFactDefId (Set<FactDefLang> factDefLangFactDefViaFactDefId) {
        this.factDefLangFactDefViaFactDefId = factDefLangFactDefViaFactDefId;
    }	
    
    public void addFactDefLangFactDefViaFactDefId (FactDefLang element) {
    	    getFactDefLangFactDefViaFactDefId().add(element);
    }
    
//MP-MANAGED-UPDATABLE-ENDING
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @optionDefFactDefViaFactDefId-getter-fact_def@
    public Set<OptionDef> getOptionDefFactDefViaFactDefId() {
        if (optionDefFactDefViaFactDefId == null){
            optionDefFactDefViaFactDefId = new HashSet<OptionDef>();
        }
        return optionDefFactDefViaFactDefId;
    }

    public void setOptionDefFactDefViaFactDefId (Set<OptionDef> optionDefFactDefViaFactDefId) {
        this.optionDefFactDefViaFactDefId = optionDefFactDefViaFactDefId;
    }	
    
    public void addOptionDefFactDefViaFactDefId (OptionDef element) {
    	    getOptionDefFactDefViaFactDefId().add(element);
    }
    
//MP-MANAGED-UPDATABLE-ENDING

    public Set<Section> getSectionFactViaId() {
        if (sectionFactViaId == null){
            sectionFactViaId = new HashSet<Section>();
        }
        return sectionFactViaId;
    }

    public void setSectionFactViaId (Set<Section> sectionFactViaId) {
        this.sectionFactViaId = sectionFactViaId;
    }
    	
    public void addSectionFactViaId (Section element) {
        getSectionFactViaId().add(element);
    }
	


//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@

}
