package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity (name="ValueType")
@Table (name="\"value_type\"")
@NamedQueries ({
	 @NamedQuery(name="ValueType.findAll", query="SELECT a FROM ValueType a")
	,@NamedQuery(name="ValueType.findByName", query="SELECT a FROM ValueType a WHERE a.name = :name")
	,@NamedQuery(name="ValueType.findByNameContaining", query="SELECT a FROM ValueType a WHERE a.name like :name")
})
public class ValueType implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL = "ValueType.findAll";
    public static final String FIND_BY_NAME = "ValueType.findByName";
    public static final String FIND_BY_NAME_CONTAINING ="ValueType.findByNameContaining";
	
    @Id @Column(name="id" ) 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name"  , length=45 , nullable=false , unique=false)
    private String name; 

    @OneToMany (targetEntity=model.ValueDef.class, fetch=FetchType.LAZY, mappedBy="valueTypeId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <ValueDef> valueDefValueTypeViaValueTypeId = new HashSet<ValueDef>(); 

    public ValueType() {
    }

    public ValueType(
       Integer id,
       String name) {
	 this(
       id,
       name
	 ,true);
	}
    
	public ValueType(
       Integer id,
       String name	
    , boolean setRelationship) {
       setId (id);
       setName (name);
    }

	public ValueType flat() {
	   return new ValueType(
          getId(),
          getName()
       , false
	   );
	}

    public Integer getId() {
        return id;
    }
	
    public void setId (Integer id) {
        this.id =  id;
    }

    public String getName() {
        return name;
    }
	
    public void setName (String name) {
        this.name =  name;
    }

    public Set<ValueDef> getValueDefValueTypeViaValueTypeId() {
        if (valueDefValueTypeViaValueTypeId == null){
            valueDefValueTypeViaValueTypeId = new HashSet<ValueDef>();
        }
        return valueDefValueTypeViaValueTypeId;
    }

    public void setValueDefValueTypeViaValueTypeId (Set<ValueDef> valueDefValueTypeViaValueTypeId) {
        this.valueDefValueTypeViaValueTypeId = valueDefValueTypeViaValueTypeId;
    }	
    
    public void addValueDefValueTypeViaValueTypeId (ValueDef element) {
    	    getValueDefValueTypeViaValueTypeId().add(element);
    }
}
