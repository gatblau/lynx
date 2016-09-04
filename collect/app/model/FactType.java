package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity (name="FactType")
@Table (name="\"fact_type\"")
@NamedQueries ({
	 @NamedQuery(name="FactType.findAll", query="SELECT a FROM FactType a")
	,@NamedQuery(name="FactType.findByValue", query="SELECT a FROM FactType a WHERE a.value = :value")
	,@NamedQuery(name="FactType.findByValueContaining", query="SELECT a FROM FactType a WHERE a.value like :value")

})
public class FactType implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL = "FactType.findAll";
    public static final String FIND_BY_VALUE = "FactType.findByValue";
    public static final String FIND_BY_VALUE_CONTAINING ="FactType.findByValueContaining";
	
    @Id @Column(name="id" ) 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="value"  , length=45 , nullable=false , unique=false)
    private String value; 

    @OneToMany (targetEntity=model.FactDef.class, fetch=FetchType.LAZY, mappedBy="factTypeId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <FactDef> factDefFactTypeViaFactTypeId = new HashSet<FactDef>(); 

    public FactType() {
    }

    public FactType(
       Integer id,
       String value) {
	 this(
       id,
       value
	 ,true);
	}
    
	public FactType(
       Integer id,
       String value	
    , boolean setRelationship) {
       setId (id);
       setValue (value);
    }

	public FactType flat() {
	   return new FactType(
          getId(),
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

    public String getValue() {
        return value;
    }
	
    public void setValue (String value) {
        this.value =  value;
    }

    public Set<FactDef> getFactDefFactTypeViaFactTypeId() {
        if (factDefFactTypeViaFactTypeId == null){
            factDefFactTypeViaFactTypeId = new HashSet<FactDef>();
        }
        return factDefFactTypeViaFactTypeId;
    }

    public void setFactDefFactTypeViaFactTypeId (Set<FactDef> factDefFactTypeViaFactTypeId) {
        this.factDefFactTypeViaFactTypeId = factDefFactTypeViaFactTypeId;
    }	
    
    public void addFactDefFactTypeViaFactTypeId (FactDef element) {
    	    getFactDefFactTypeViaFactTypeId().add(element);
    }
}
