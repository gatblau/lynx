package model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity (name="Value")
@Table (name="\"value\"")
@NamedQueries ({
    @NamedQuery(name="Value.findAll", query="SELECT a FROM Value a")
    ,@NamedQuery(name="Value.findByShortTextVal", query="SELECT a FROM Value a WHERE a.shortTextVal = :shortTextVal")
    ,@NamedQuery(name="Value.findByShortTextValContaining", query="SELECT a FROM Value a WHERE a.shortTextVal like :shortTextVal")
    ,@NamedQuery(name="Value.findByLongTextVal", query="SELECT a FROM Value a WHERE a.longTextVal = :longTextVal")
    ,@NamedQuery(name="Value.findByLongTextValContaining", query="SELECT a FROM Value a WHERE a.longTextVal like :longTextVal")
    ,@NamedQuery(name="Value.findByDateVal", query="SELECT a FROM Value a WHERE a.dateVal = :dateVal")
    ,@NamedQuery(name="Value.findByIntVal", query="SELECT a FROM Value a WHERE a.intVal = :intVal")
    ,@NamedQuery(name="Value.findByDecVal", query="SELECT a FROM Value a WHERE a.decVal = :decVal")
    ,@NamedQuery(name="Value.findByBoolVal", query="SELECT a FROM Value a WHERE a.boolVal = :boolVal")
})
public class Value implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL = "Value.findAll";
    public static final String FIND_BY_SHORTTEXTVAL = "Value.findByShortTextVal";
    public static final String FIND_BY_SHORTTEXTVAL_CONTAINING ="Value.findByShortTextValContaining";
    public static final String FIND_BY_LONGTEXTVAL = "Value.findByLongTextVal";
    public static final String FIND_BY_LONGTEXTVAL_CONTAINING ="Value.findByLongTextValContaining";
    public static final String FIND_BY_DATEVAL = "Value.findByDateVal";
    public static final String FIND_BY_INTVAL = "Value.findByIntVal";
    public static final String FIND_BY_DECVAL = "Value.findByDecVal";
    public static final String FIND_BY_BOOLVAL = "Value.findByBoolVal";

    @Id @Column(name="id" )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="short_text_val"  , length=150 , nullable=true , unique=false)
    private String shortTextVal;

    @Column(name="long_text_val"   , nullable=true , unique=false)
    private String longTextVal;

    @Column(name="date_val"   , nullable=true , unique=false)
    private Timestamp dateVal;

    @Column(name="int_val"   , nullable=true , unique=false)
    private Integer intVal;

    @Column(name="dec_val"   , nullable=true , unique=false)
    private java.math.BigDecimal decVal;

    @Column(name="bool_val"   , nullable=true , unique=false)
    private Boolean boolVal;

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="value_def_id", referencedColumnName = "id" , nullable=false , unique=false , insertable=true, updatable=true)
    private ValueDef valueDefId;

    @Column(name="value_def_id"  , nullable=false , unique=true, insertable=false, updatable=false)
    private Integer valueDefId_;

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="item_id", referencedColumnName = "id" , nullable=false , unique=true  , insertable=true, updatable=true)
    private Item itemId;

    @Column(name="item_id"  , nullable=false , unique=true, insertable=false, updatable=false)
    private Integer itemId_;

    public Value() {
    }

    public Value(
            Integer id,
            String shortTextVal,
            String longTextVal,
            Timestamp dateVal,
            Integer intVal,
            java.math.BigDecimal decVal,
            Boolean boolVal,
            Integer valueDefId,
            Integer itemId) {
        this(
                id,
                shortTextVal,
                longTextVal,
                dateVal,
                intVal,
                decVal,
                boolVal,
                valueDefId,
                itemId
                ,true);
    }

    public Value(
            Integer id,
            String shortTextVal,
            String longTextVal,
            Timestamp dateVal,
            Integer intVal,
            java.math.BigDecimal decVal,
            Boolean boolVal,
            Integer valueDefId,
            Integer itemId
            , boolean setRelationship) {
        setId (id);
        setShortTextVal (shortTextVal);
        setLongTextVal (longTextVal);
        setDateVal (dateVal);
        setIntVal (intVal);
        setDecVal (decVal);
        setBoolVal (boolVal);
        if (setRelationship && valueDefId!=null) {
            this.valueDefId = new ValueDef();
            this.valueDefId.setId(valueDefId);
            setValueDefId_ (valueDefId);
        }
        if (setRelationship && itemId!=null) {
            this.itemId = new Item();
            this.itemId.setId(itemId);
            setItemId_ (itemId);
        }
    }

    public Value flat() {
        return new Value(
                getId(),
                getShortTextVal(),
                getLongTextVal(),
                getDateVal(),
                getIntVal(),
                getDecVal(),
                getBoolVal(),
                getValueDefId_(),
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

    public String getShortTextVal() {
        return shortTextVal;
    }

    public void setShortTextVal (String shortTextVal) {
        this.shortTextVal =  shortTextVal;
    }

    public String getLongTextVal() {
        return longTextVal;
    }

    public void setLongTextVal (String longTextVal) {
        this.longTextVal =  longTextVal;
    }

    public Timestamp getDateVal() {
        return dateVal;
    }

    public void setDateVal (Timestamp dateVal) {
        this.dateVal =  dateVal;
    }

    public Integer getIntVal() {
        return intVal;
    }

    public void setIntVal (Integer intVal) {
        this.intVal =  intVal;
    }

    public java.math.BigDecimal getDecVal() {
        return decVal;
    }

    public void setDecVal (java.math.BigDecimal decVal) {
        this.decVal =  decVal;
    }

    public Boolean getBoolVal() {
        return boolVal;
    }

    public void setBoolVal (Boolean boolVal) {
        this.boolVal =  boolVal;
    }

    public ValueDef getValueDefId () {
        return valueDefId;
    }

    public void setValueDefId (ValueDef valueDefId) {
        this.valueDefId = valueDefId;
    }

    public Integer getValueDefId_() {
        return valueDefId_;
    }

    public void setValueDefId_ (Integer valueDefId) {
        this.valueDefId_ =  valueDefId;
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
}