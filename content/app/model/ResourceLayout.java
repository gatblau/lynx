package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity (name="ResourceLayout")
@Table (name="\"resource_layout\"")
@NamedQueries ({
	 @NamedQuery(name="ResourceLayout.findAll", query="SELECT a FROM ResourceLayout a")
	,@NamedQuery(name="ResourceLayout.findByValue", query="SELECT a FROM ResourceLayout a WHERE a.value = :value")
	,@NamedQuery(name="ResourceLayout.findByValueContaining", query="SELECT a FROM ResourceLayout a WHERE a.value like :value")
})
public class ResourceLayout implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL = "ResourceLayout.findAll";
    public static final String FIND_BY_VALUE = "ResourceLayout.findByValue";
    public static final String FIND_BY_VALUE_CONTAINING ="ResourceLayout.findByValueContaining";
	
    @Id @Column(name="id" ) 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="value"  , length=45 , nullable=false , unique=false)
    private String value; 

    @OneToMany (targetEntity=ItemDef.class, fetch=FetchType.LAZY, mappedBy="resourceLayoutId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <ItemDef> itemDefResourceLayoutViaResourceLayoutId = new HashSet<ItemDef>();

    public ResourceLayout() {
    }

    public ResourceLayout(
       Integer id,
       String value) {
	 this(
       id,
       value
	 ,true);
	}
    
	public ResourceLayout(
       Integer id,
       String value	
    , boolean setRelationship) {
       setId (id);
       setValue (value);
    }

	public ResourceLayout flat() {
	   return new ResourceLayout(
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

    public Set<ItemDef> getItemDefResourceLayoutViaResourceLayoutId() {
        if (itemDefResourceLayoutViaResourceLayoutId == null){
            itemDefResourceLayoutViaResourceLayoutId = new HashSet<ItemDef>();
        }
        return itemDefResourceLayoutViaResourceLayoutId;
    }

    public void setItemDefResourceLayoutViaResourceLayoutId (Set<ItemDef> itemDefResourceLayoutViaResourceLayoutId) {
        this.itemDefResourceLayoutViaResourceLayoutId = itemDefResourceLayoutViaResourceLayoutId;
    }	
    
    public void addItemDefResourceLayoutViaResourceLayoutId (ItemDef element) {
    	    getItemDefResourceLayoutViaResourceLayoutId().add(element);
    }
}
