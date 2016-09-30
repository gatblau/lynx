package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity (name="Item")
@Table (name="\"item\"")
@NamedQueries ({
	 @NamedQuery(name="Item.findAll", query="SELECT a FROM Item a")
})
public class Item implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL = "Item.findAll";
	
    @Id @Column(name="id" ) 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="item_def_id", referencedColumnName = "id" , nullable=false , unique=false , insertable=true, updatable=true)
    private ItemDef itemDefId;

    @Column(name="item_def_id"  , nullable=false , unique=true, insertable=false, updatable=false)
    private Integer itemDefId_;

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="section_id", referencedColumnName = "id" , nullable=false , unique=true  , insertable=true, updatable=true) 
    private Section sectionId;  

    @Column(name="section_id"  , nullable=false , unique=true, insertable=false, updatable=false)
    private Integer sectionId_;

    @OneToMany (targetEntity=model.Option.class, fetch=FetchType.LAZY, mappedBy="itemId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <Option> optionItemViaItemId = new HashSet<Option>();

    public Item() {
    }

    public Item(
       Integer id,
       Integer itemDefId,
       Integer sectionId) {
	 this(
       id,
       itemDefId,
       sectionId
	 ,true);
	}
    
	public Item(
       Integer id,
       Integer itemDefId,
       Integer sectionId	
    , boolean setRelationship) {
       setId (id);
       if (setRelationship && itemDefId!=null) {
          this.itemDefId = new ItemDef();
          this.itemDefId.setId(itemDefId);
       }
       if (setRelationship && sectionId!=null) {
          this.sectionId = new Section();
          this.sectionId.setId(sectionId);
       }
    }

	public Item flat() {
	   return new Item(
          getId(),
          getItemDefId_(),
          getSectionId_()
       , false
	   );
	}

    public Integer getId() {
        return id;
    }
	
    public void setId (Integer id) {
        this.id =  id;
    }
    

    public ItemDef getItemDefId () {
    	return itemDefId;
    }
	
    public void setItemDefId (ItemDef itemDefId) {
    	this.itemDefId = itemDefId;
    }

    public Integer getItemDefId_() {
        return itemDefId_;
    }
	
    public void setItemDefId_ (Integer itemDefId) {
        this.itemDefId_ =  itemDefId;
    }
	
    public Section getSectionId () {
    	return sectionId;
    }
	
    public void setSectionId (Section sectionId) {
    	this.sectionId = sectionId;
    }

    public Integer getSectionId_() {
        return sectionId_;
    }
	
    public void setSectionId_ (Integer sectionId) {
        this.sectionId_ =  sectionId;
    }

    public Set<Option> getOptionItemViaItemId() {
        if (optionItemViaItemId == null){
            optionItemViaItemId = new HashSet<Option>();
        }
        return optionItemViaItemId;
    }

    public void setOptionItemViaItemId (Set<Option> optionItemViaItemId) {
        this.optionItemViaItemId = optionItemViaItemId;
    }	
    
    public void addOptionItemViaItemId (Option element) {
    	    getOptionItemViaItemId().add(element);
    }

}
