package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity (name="ItemType")
@Table (name="\"item_type\"")
@NamedQueries ({
	 @NamedQuery(name="ItemType.findAll", query="SELECT a FROM ItemType a")
	,@NamedQuery(name="ItemType.findByValue", query="SELECT a FROM ItemType a WHERE a.value = :value")
	,@NamedQuery(name="ItemType.findByValueContaining", query="SELECT a FROM ItemType a WHERE a.value like :value")

})
public class ItemType implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL = "ItemType.findAll";
    public static final String FIND_BY_VALUE = "ItemType.findByValue";
    public static final String FIND_BY_VALUE_CONTAINING ="ItemType.findByValueContaining";
	
    @Id @Column(name="id" ) 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="value"  , length=45 , nullable=false , unique=false)
    private String value; 

    @OneToMany (targetEntity=model.ItemDef.class, fetch=FetchType.LAZY, mappedBy="itemTypeId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <ItemDef> itemDefItemTypeViaItemTypeId = new HashSet<ItemDef>();

    public ItemType() {
    }

    public ItemType(
       Integer id,
       String value) {
	 this(
       id,
       value
	 ,true);
	}
    
	public ItemType(
       Integer id,
       String value	
    , boolean setRelationship) {
       setId (id);
       setValue (value);
    }

	public ItemType flat() {
	   return new ItemType(
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

    public Set<ItemDef> getItemDefItemTypeViaItemTypeId() {
        if (itemDefItemTypeViaItemTypeId == null){
            itemDefItemTypeViaItemTypeId = new HashSet<ItemDef>();
        }
        return itemDefItemTypeViaItemTypeId;
    }

    public void setItemDefItemTypeViaItemTypeId (Set<ItemDef> itemDefItemTypeViaItemTypeId) {
        this.itemDefItemTypeViaItemTypeId = itemDefItemTypeViaItemTypeId;
    }	
    
    public void addItemDefItemTypeViaItemTypeId (ItemDef element) {
    	    getItemDefItemTypeViaItemTypeId().add(element);
    }
}
