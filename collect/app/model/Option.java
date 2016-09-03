package model;

//MP-MANAGED-ADDED-AREA-BEGINNING @import@
//MP-MANAGED-ADDED-AREA-ENDING @import@
import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * <p>Title: Option</p>
 *
 * <p>Description: Domain Object describing a Option entity</p>
 *
 */
@Entity (name="Option")
@Table (name="\"option\"")
@NamedQueries ({
	 @NamedQuery(name="Option.findAll", query="SELECT a FROM Option a")
	,@NamedQuery(name="Option.findByFactId", query="SELECT a FROM Option a WHERE a.factId = :factId")

	,@NamedQuery(name="Option.findByValue", query="SELECT a FROM Option a WHERE a.value = :value")
	,@NamedQuery(name="Option.findByValueContaining", query="SELECT a FROM Option a WHERE a.value like :value")

})

public class Option implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL = "Option.findAll";
    public static final String FIND_BY_FACTID = "Option.findByFactId";
    public static final String FIND_BY_VALUE = "Option.findByValue";
    public static final String FIND_BY_VALUE_CONTAINING ="Option.findByValueContaining";
	
    @Id @Column(name="id" ) 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//MP-MANAGED-ADDED-AREA-BEGINNING @fact_id-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @fact_id-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-fact_id@
    @Column(name="fact_id"   , nullable=false , unique=false)
    private Integer factId; 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @value-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @value-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-value@
    @Column(name="value"  , length=150 , nullable=false , unique=false)
    private String value; 
//MP-MANAGED-UPDATABLE-ENDING

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="fact_option_def_id", referencedColumnName = "id" , nullable=false , unique=false , insertable=true, updatable=true) 
    private OptionDef factOptionDefId;  

    @Column(name="fact_option_def_id"  , nullable=false , unique=true, insertable=false, updatable=false)
    private Integer factOptionDefId_;

    /**
    * Default constructor
    */
    public Option() {
    }

	/**
	* All field constructor 
	*/
    public Option(
       Integer id,
       Integer factId,
       Integer factOptionDefId,
       String value) {
	 this(
       id,
       factId,
       factOptionDefId,
       value
	 ,true);
	}
    
	public Option(
       Integer id,
       Integer factId,
       Integer factOptionDefId,
       String value	
    , boolean setRelationship) {
       //primary keys
       setId (id);
       //attributes
       setFactId (factId);
       setValue (value);
       //parents
       if (setRelationship && factOptionDefId!=null) {
          this.factOptionDefId = new OptionDef();
          this.factOptionDefId.setId(factOptionDefId);
	      setFactOptionDefId_ (factOptionDefId);
       }
    }

	public Option flat() {
	   return new Option(
          getId(),
          getFactId(),
          getFactOptionDefId_(),
          getValue()
       , false
	   );
	}

    public Integer getId() {
        return id;
    }
	
    public void setId (Integer id) {
        this.id =  id;
    }
    
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-fact_id@
    public Integer getFactId() {
        return factId;
    }
	
    public void setFactId (Integer factId) {
        this.factId =  factId;
    }
	
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-value@
    public String getValue() {
        return value;
    }
	
    public void setValue (String value) {
        this.value =  value;
    }
	
//MP-MANAGED-UPDATABLE-ENDING


    public OptionDef getFactOptionDefId () {
    	return factOptionDefId;
    }
	
    public void setFactOptionDefId (OptionDef factOptionDefId) {
    	this.factOptionDefId = factOptionDefId;
    }

    public Integer getFactOptionDefId_() {
        return factOptionDefId_;
    }
	
    public void setFactOptionDefId_ (Integer factOptionDefId) {
        this.factOptionDefId_ =  factOptionDefId;
    }
	




//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@

}
