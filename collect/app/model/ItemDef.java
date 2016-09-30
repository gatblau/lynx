package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity (name="ItemDef")
@Table (name="\"item_def\"")
@NamedQueries ({
	 @NamedQuery(name="ItemDef.findAll", query="SELECT a FROM ItemDef a")
	,@NamedQuery(name="ItemDef.findByRequired", query="SELECT a FROM ItemDef a WHERE a.required = :required")
	,@NamedQuery(name="ItemDef.findByRegex", query="SELECT a FROM ItemDef a WHERE a.regex = :regex")
	,@NamedQuery(name="ItemDef.findByRegexContaining", query="SELECT a FROM ItemDef a WHERE a.regex like :regex")
	,@NamedQuery(name="ItemDef.findByMin", query="SELECT a FROM ItemDef a WHERE a.min = :min")
	,@NamedQuery(name="ItemDef.findByMinContaining", query="SELECT a FROM ItemDef a WHERE a.min like :min")
	,@NamedQuery(name="ItemDef.findByMax", query="SELECT a FROM ItemDef a WHERE a.max = :max")
	,@NamedQuery(name="ItemDef.findByMaxContaining", query="SELECT a FROM ItemDef a WHERE a.max like :max")
})
public class ItemDef implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL = "ItemDef.findAll";
    public static final String FIND_BY_REQUIRED = "ItemDef.findByRequired";
    public static final String FIND_BY_REGEX = "ItemDef.findByRegex";
    public static final String FIND_BY_REGEX_CONTAINING ="ItemDef.findByRegexContaining";
    public static final String FIND_BY_MIN = "ItemDef.findByMin";
    public static final String FIND_BY_MIN_CONTAINING ="ItemDef.findByMinContaining";
    public static final String FIND_BY_MAX = "ItemDef.findByMax";
    public static final String FIND_BY_MAX_CONTAINING ="ItemDef.findByMaxContaining";
	
    @Id @Column(name="id" ) 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="required"   , nullable=false , unique=false)
    private Boolean required; 

    @Column(name="regex"  , length=200 , nullable=true , unique=false)
    private String regex; 

    @Column(name="min"  , length=100 , nullable=true , unique=false)
    private String min; 

    @Column(name="max"  , length=100 , nullable=true , unique=false)
    private String max;

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="item_type_id", referencedColumnName = "id" , nullable=false , unique=false , insertable=true, updatable=true)
    private ItemType itemTypeId;

    @Column(name="item_type_id"  , nullable=false , unique=true, insertable=false, updatable=false)
    private Integer itemTypeId_;

    @ManyToOne (fetch=FetchType.LAZY )
    @JoinColumn(name="resource_layout_id", referencedColumnName = "id" , nullable=true , unique=true  , insertable=true, updatable=true) 
    private ResourceLayout resourceLayoutId;  

    @Column(name="resource_layout_id"  , nullable=true , unique=true, insertable=false, updatable=false)
    private Integer resourceLayoutId_;

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="section_def_id", referencedColumnName = "id" , nullable=false , unique=true  , insertable=true, updatable=true) 
    private SectionDef sectionDefId;  

    @Column(name="section_def_id"  , nullable=false , unique=true, insertable=false, updatable=false)
    private Integer sectionDefId_;

    @OneToMany (targetEntity=Item.class, fetch=FetchType.LAZY, mappedBy="itemDefId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <Item> itemItemDefViaItemDefId = new HashSet<Item>();

    @OneToMany (targetEntity=ItemDefLang.class, fetch=FetchType.LAZY, mappedBy="itemDefId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <ItemDefLang> itemDefLangItemDefViaItemDefId = new HashSet<ItemDefLang>();

    @OneToMany (targetEntity=OptionDef.class, fetch=FetchType.LAZY, mappedBy="itemDefId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <OptionDef> optionDefItemDefViaItemDefId = new HashSet<OptionDef>();

    @OneToMany (targetEntity=Resource.class, fetch=FetchType.LAZY, mappedBy="itemDefId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <Resource> resourceItemDefViaItemDefId = new HashSet<Resource>();

    public ItemDef() {
    }

    public ItemDef(
       Integer id,
       Integer sectionDefId,
       Integer itemTypeId,
       Boolean required,
       String regex,
       String min,
       String max,
       Integer resourceLayoutId) {
	 this(
       id,
       sectionDefId,
       itemTypeId,
       required,
       regex,
       min,
       max,
       resourceLayoutId
	 ,true);
	}
    
	public ItemDef(
       Integer id,
       Integer sectionDefId,
       Integer itemTypeId,
       Boolean required,
       String regex,
       String min,
       String max,
       Integer resourceLayoutId	
    , boolean setRelationship) {
       setId (id);
       setRequired (required);
       setRegex (regex);
       setMin (min);
       setMax (max);
       if (setRelationship && itemTypeId!=null) {
          this.itemTypeId = new ItemType();
          this.itemTypeId.setId(itemTypeId);
	      setItemTypeId_ (itemTypeId);
       }
       if (setRelationship && resourceLayoutId!=null) {
          this.resourceLayoutId = new ResourceLayout();
          this.resourceLayoutId.setId(resourceLayoutId);
	      setResourceLayoutId_ (resourceLayoutId);
       }
       if (setRelationship && sectionDefId!=null) {
          this.sectionDefId = new SectionDef();
          this.sectionDefId.setId(sectionDefId);
	      setSectionDefId_ (sectionDefId);
       }
    }

	public ItemDef flat() {
	   return new ItemDef(
          getId(),
          getSectionDefId_(),
          getItemTypeId_(),
          getRequired(),
          getRegex(),
          getMin(),
          getMax(),
          getResourceLayoutId_()
       , false
	   );
	}

    public Integer getId() {
        return id;
    }
	
    public void setId (Integer id) {
        this.id =  id;
    }

    public Boolean getRequired() {
        return required;
    }
	
    public void setRequired (Boolean required) {
        this.required =  required;
    }

    public String getRegex() {
        return regex;
    }
	
    public void setRegex (String regex) {
        this.regex =  regex;
    }

    public String getMin() {
        return min;
    }
	
    public void setMin (String min) {
        this.min =  min;
    }

    public String getMax() {
        return max;
    }
	
    public void setMax (String max) {
        this.max =  max;
    }

    public ItemType getItemTypeId () {
    	return itemTypeId;
    }
	
    public void setItemTypeId (ItemType itemTypeId) {
    	this.itemTypeId = itemTypeId;
    }

    public Integer getItemTypeId_() {
        return itemTypeId_;
    }
	
    public void setItemTypeId_ (Integer itemTypeId) {
        this.itemTypeId_ =  itemTypeId;
    }
	
    public ResourceLayout getResourceLayoutId () {
    	return resourceLayoutId;
    }
	
    public void setResourceLayoutId (ResourceLayout resourceLayoutId) {
    	this.resourceLayoutId = resourceLayoutId;
    }

    public Integer getResourceLayoutId_() {
        return resourceLayoutId_;
    }
	
    public void setResourceLayoutId_ (Integer resourceLayoutId) {
        this.resourceLayoutId_ =  resourceLayoutId;
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

    public Set<Item> getItemItemDefViaItemDefId() {
        if (itemItemDefViaItemDefId == null){
            itemItemDefViaItemDefId = new HashSet<Item>();
        }
        return itemItemDefViaItemDefId;
    }

    public void setItemItemDefViaItemDefId (Set<Item> itemItemDefViaItemDefId) {
        this.itemItemDefViaItemDefId = itemItemDefViaItemDefId;
    }	
    
    public void addItemItemDefViaItemDefId (Item element) {
    	    getItemItemDefViaItemDefId().add(element);
    }

    public Set<ItemDefLang> getItemDefLangItemDefViaItemDefId() {
        if (itemDefLangItemDefViaItemDefId == null){
            itemDefLangItemDefViaItemDefId = new HashSet<ItemDefLang>();
        }
        return itemDefLangItemDefViaItemDefId;
    }

    public void setItemDefLangItemDefViaItemDefId (Set<ItemDefLang> itemDefLangItemDefViaItemDefId) {
        this.itemDefLangItemDefViaItemDefId = itemDefLangItemDefViaItemDefId;
    }	
    
    public void addItemDefLangItemDefViaItemDefId (ItemDefLang element) {
    	    getItemDefLangItemDefViaItemDefId().add(element);
    }

    public Set<OptionDef> getOptionDefItemDefViaItemDefId() {
        if (optionDefItemDefViaItemDefId == null){
            optionDefItemDefViaItemDefId = new HashSet<OptionDef>();
        }
        return optionDefItemDefViaItemDefId;
    }

    public void setOptionDefItemDefViaItemDefId (Set<OptionDef> optionDefItemDefViaItemDefId) {
        this.optionDefItemDefViaItemDefId = optionDefItemDefViaItemDefId;
    }	
    
    public void addOptionDefItemDefViaItemDefId (OptionDef element) {
    	    getOptionDefItemDefViaItemDefId().add(element);
    }

    public Set<Resource> getResourceItemDefViaItemDefId() {
        if (resourceItemDefViaItemDefId == null){
            resourceItemDefViaItemDefId = new HashSet<Resource>();
        }
        return resourceItemDefViaItemDefId;
    }

    public void setResourceItemDefViaItemDefId (Set<Resource> resourceItemDefViaItemDefId) {
        this.resourceItemDefViaItemDefId = resourceItemDefViaItemDefId;
    }	
    
    public void addResourceItemDefViaItemDefId (Resource element) {
    	    getResourceItemDefViaItemDefId().add(element);
    }

    @javax.persistence.PrePersist
    public void prePersist_ () {
    }

    @javax.persistence.PreUpdate
    public void preUpdate_ () {
    }

}
