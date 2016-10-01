package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity (name="OptionDef")
@Table (name="\"option_def\"")
@NamedQueries ({
	 @NamedQuery(name="OptionDef.findAll", query="SELECT a FROM OptionDef a")
})
public class OptionDef implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL = "OptionDef.findAll";
	
    @Id @Column(name="id" ) 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="item_def_id", referencedColumnName = "id" , nullable=false , unique=false , insertable=true, updatable=true)
    private ItemDef itemDefId;

    @Column(name="item_def_id"  , nullable=false , unique=true, insertable=false, updatable=false)
    private Integer itemDefId_;

    @OneToMany (targetEntity=model.Option.class, fetch=FetchType.LAZY, mappedBy="optionDefId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <Option> optionOptionDefViaOptionDefId = new HashSet<Option>(); 

    @OneToMany (targetEntity=model.OptionDefLang.class, fetch=FetchType.LAZY, mappedBy="optionDefId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <OptionDefLang> optionDefLangOptionDefViaOptionDefId = new HashSet<OptionDefLang>(); 

    public OptionDef() {
    }

    public OptionDef(
       Integer id,
       Integer itemDefId) {
	 this(
       id,
       itemDefId
	 ,true);
	}
    
	public OptionDef(
       Integer id,
       Integer itemDefId
    , boolean setRelationship) {
       setId (id);
       if (setRelationship && itemDefId!=null) {
          this.itemDefId = new ItemDef();
          this.itemDefId.setId(itemDefId);
       }
    }

	public OptionDef flat() {
	   return new OptionDef(
          getId(),
          getItemDefId_()
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

    public Set<Option> getOptionOptionDefViaOptionDefId() {
        if (optionOptionDefViaOptionDefId == null){
            optionOptionDefViaOptionDefId = new HashSet<Option>();
        }
        return optionOptionDefViaOptionDefId;
    }

    public void setOptionOptionDefViaOptionDefId (Set<Option> optionOptionDefViaOptionDefId) {
        this.optionOptionDefViaOptionDefId = optionOptionDefViaOptionDefId;
    }	
    
    public void addOptionOptionDefViaOptionDefId (Option element) {
    	    getOptionOptionDefViaOptionDefId().add(element);
    }

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
}
