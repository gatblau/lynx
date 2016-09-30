package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity (name="Option")
@Table (name="\"option\"")
@NamedQueries ({
	 @NamedQuery(name="Option.findAll", query="SELECT a FROM Option a")
	,@NamedQuery(name="Option.findByValue", query="SELECT a FROM Option a WHERE a.value = :value")
	,@NamedQuery(name="Option.findByValueContaining", query="SELECT a FROM Option a WHERE a.value like :value")
})
public class Option implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL = "Option.findAll";
    public static final String FIND_BY_VALUE = "Option.findByValue";
    public static final String FIND_BY_VALUE_CONTAINING ="Option.findByValueContaining";
	
    @Id @Column(name="id" ) 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="value"  , length=150 , nullable=false , unique=false)
    private String value;

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="item_id", referencedColumnName = "id" , nullable=false , unique=false , insertable=true, updatable=true)
    private Item itemId;

    @Column(name="item_id"  , nullable=false , unique=true, insertable=false, updatable=false)
    private Integer itemId_;

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="option_def_id", referencedColumnName = "id" , nullable=false , unique=true  , insertable=true, updatable=true) 
    private OptionDef optionDefId;  

    @Column(name="option_def_id"  , nullable=false , unique=true, insertable=false, updatable=false)
    private Integer optionDefId_;

    public Option() {
    }

    public Option(
       Integer id,
       String value,
       Integer optionDefId,
       Integer itemId) {
	 this(
       id,
       value,
       optionDefId,
       itemId
	 ,true);
	}
    
	public Option(
       Integer id,
       String value,
       Integer optionDefId,
       Integer itemId
    , boolean setRelationship) {
       setId (id);
       setValue (value);
       if (setRelationship && itemId!=null) {
          this.itemId = new Item();
          this.itemId.setId(itemId);
	      setItemId_ (itemId);
       }
       if (setRelationship && optionDefId!=null) {
          this.optionDefId = new OptionDef();
          this.optionDefId.setId(optionDefId);
	      setOptionDefId_ (optionDefId);
       }
    }

	public Option flat() {
	   return new Option(
          getId(),
          getValue(),
          getOptionDefId_(),
          getItemId_()
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

    public Item getItemId () {
    	return itemId;
    }
	
    public void setItemId (Item itemId) {
    	this.itemId = itemId;
    }

    public Integer getItemId_() {
        return itemId_;
    }
	
    public void setItemId_ (Integer itemId) {
        this.itemId_ =  itemId;
    }
	
    public OptionDef getOptionDefId () {
    	return optionDefId;
    }
	
    public void setOptionDefId (OptionDef optionDefId) {
    	this.optionDefId = optionDefId;
    }

    public Integer getOptionDefId_() {
        return optionDefId_;
    }
	
    public void setOptionDefId_ (Integer optionDefId) {
        this.optionDefId_ =  optionDefId;
    }

}
