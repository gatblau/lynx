package model;

//MP-MANAGED-ADDED-AREA-BEGINNING @import@
//MP-MANAGED-ADDED-AREA-ENDING @import@
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * <p>Title: OptionDef</p>
 *
 * <p>Description: Domain Object describing a OptionDef entity</p>
 *
 */
@Entity (name="OptionDef")
@Table (name="\"option_def\"")
@NamedQueries ({
	 @NamedQuery(name="OptionDef.findAll", query="SELECT a FROM OptionDef a")
	,@NamedQuery(name="OptionDef.findByName", query="SELECT a FROM OptionDef a WHERE a.name = :name")
	,@NamedQuery(name="OptionDef.findByNameContaining", query="SELECT a FROM OptionDef a WHERE a.name like :name")

})

public class OptionDef implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL = "OptionDef.findAll";
    public static final String FIND_BY_NAME = "OptionDef.findByName";
    public static final String FIND_BY_NAME_CONTAINING ="OptionDef.findByNameContaining";
	
    @Id @Column(name="id" ) 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

//MP-MANAGED-ADDED-AREA-BEGINNING @name-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @name-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-name@
    @Column(name="name"  , length=45 , nullable=false , unique=false)
    private String name; 
//MP-MANAGED-UPDATABLE-ENDING

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="fact_def_id", referencedColumnName = "id" , nullable=false , unique=false , insertable=true, updatable=true) 
    private FactDef factDefId;  

    @Column(name="fact_def_id"  , nullable=false , unique=true, insertable=false, updatable=false)
    private Integer factDefId_;

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @optionOptionDefViaFactOptionDefId-field-option_def@
    @OneToMany (targetEntity=model.Option.class, fetch=FetchType.LAZY, mappedBy="factOptionDefId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <Option> optionOptionDefViaFactOptionDefId = new HashSet<Option>(); 

//MP-MANAGED-UPDATABLE-ENDING
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @optionDefLangOptionDefViaOptionDefId-field-option_def@
    @OneToMany (targetEntity=model.OptionDefLang.class, fetch=FetchType.LAZY, mappedBy="optionDefId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <OptionDefLang> optionDefLangOptionDefViaOptionDefId = new HashSet<OptionDefLang>(); 

//MP-MANAGED-UPDATABLE-ENDING
    /**
    * Default constructor
    */
    public OptionDef() {
    }

	/**
	* All field constructor 
	*/
    public OptionDef(
       Integer id,
       String name,
       Integer factDefId) {
	 this(
       id,
       name,
       factDefId
	 ,true);
	}
    
	public OptionDef(
       Integer id,
       String name,
       Integer factDefId	
    , boolean setRelationship) {
       //primary keys
       setId (id);
       //attributes
       setName (name);
       //parents
       if (setRelationship && factDefId!=null) {
          this.factDefId = new FactDef();
          this.factDefId.setId(factDefId);
	      setFactDefId_ (factDefId);
       }
    }

	public OptionDef flat() {
	   return new OptionDef(
          getId(),
          getName(),
          getFactDefId_()
       , false
	   );
	}

    public Integer getId() {
        return id;
    }
	
    public void setId (Integer id) {
        this.id =  id;
    }
    
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-name@
    public String getName() {
        return name;
    }
	
    public void setName (String name) {
        this.name =  name;
    }
	
//MP-MANAGED-UPDATABLE-ENDING


    public FactDef getFactDefId () {
    	return factDefId;
    }
	
    public void setFactDefId (FactDef factDefId) {
    	this.factDefId = factDefId;
    }

    public Integer getFactDefId_() {
        return factDefId_;
    }
	
    public void setFactDefId_ (Integer factDefId) {
        this.factDefId_ =  factDefId;
    }
	

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @optionOptionDefViaFactOptionDefId-getter-option_def@
    public Set<Option> getOptionOptionDefViaFactOptionDefId() {
        if (optionOptionDefViaFactOptionDefId == null){
            optionOptionDefViaFactOptionDefId = new HashSet<Option>();
        }
        return optionOptionDefViaFactOptionDefId;
    }

    public void setOptionOptionDefViaFactOptionDefId (Set<Option> optionOptionDefViaFactOptionDefId) {
        this.optionOptionDefViaFactOptionDefId = optionOptionDefViaFactOptionDefId;
    }	
    
    public void addOptionOptionDefViaFactOptionDefId (Option element) {
    	    getOptionOptionDefViaFactOptionDefId().add(element);
    }
    
//MP-MANAGED-UPDATABLE-ENDING
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @optionDefLangOptionDefViaOptionDefId-getter-option_def@
    public Set<OptionDefLang> getOptionDefLangOptionDefViaOptionDefId() {
        if (optionDefLangOptionDefViaOptionDefId == null){
            optionDefLangOptionDefViaOptionDefId = new HashSet<OptionDefLang>();
        }
        return optionDefLangOptionDefViaOptionDefId;
    }

    public void setOptionDefLangOptionDefViaOptionDefId (Set<OptionDefLang> optionDefLangOptionDefViaOptionDefId) {
        this.optionDefLangOptionDefViaOptionDefId = optionDefLangOptionDefViaOptionDefId;
    }	
    
    public void addOptionDefLangOptionDefViaOptionDefId (OptionDefLang element) {
    	    getOptionDefLangOptionDefViaOptionDefId().add(element);
    }
    
//MP-MANAGED-UPDATABLE-ENDING



//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@

}
