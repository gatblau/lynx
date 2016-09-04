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
    @JoinColumn(name="fact_id", referencedColumnName = "id" , nullable=false , unique=false , insertable=true, updatable=true) 
    private Fact factId;  

    @Column(name="fact_id"  , nullable=false , unique=true, insertable=false, updatable=false)
    private Integer factId_;

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
       Integer factId) {
	 this(
       id,
       value,
       optionDefId,
       factId
	 ,true);
	}
    
	public Option(
       Integer id,
       String value,
       Integer optionDefId,
       Integer factId	
    , boolean setRelationship) {
       setId (id);
       setValue (value);
       if (setRelationship && factId!=null) {
          this.factId = new Fact();
          this.factId.setId(factId);
	      setFactId_ (factId);
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
          getFactId_()
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

    public Fact getFactId () {
    	return factId;
    }
	
    public void setFactId (Fact factId) {
    	this.factId = factId;
    }

    public Integer getFactId_() {
        return factId_;
    }
	
    public void setFactId_ (Integer factId) {
        this.factId_ =  factId;
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
