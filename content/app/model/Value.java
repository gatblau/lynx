package model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity (name="Value")
@Table (name="\"value\"")
@NamedQueries ({
    @NamedQuery(name="Value.findAll", query="SELECT a FROM Value a")
    ,@NamedQuery(name="Value.findByShortText", query="SELECT a FROM Value a WHERE a.shortText = :shortText")
    ,@NamedQuery(name="Value.findByShortTextContaining", query="SELECT a FROM Value a WHERE a.shortText like :shortText")
    ,@NamedQuery(name="Value.findByLongText", query="SELECT a FROM Value a WHERE a.longText = :longText")
    ,@NamedQuery(name="Value.findByLongTextContaining", query="SELECT a FROM Value a WHERE a.longText like :longText")
    ,@NamedQuery(name="Value.findByDate", query="SELECT a FROM Value a WHERE a.date = :date")
    ,@NamedQuery(name="Value.findByInteger", query="SELECT a FROM Value a WHERE a.integer = :integer")
    ,@NamedQuery(name="Value.findByDecimal", query="SELECT a FROM Value a WHERE a.decimal = :decimal")
    ,@NamedQuery(name="Value.findByFlag", query="SELECT a FROM Value a WHERE a.flag = :flag")
})
public class Value implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL = "Value.findAll";
    public static final String FIND_BY_SHORTTEXT = "Value.findByShortText";
    public static final String FIND_BY_SHORTTEXT_CONTAINING ="Value.findByShortTextContaining";
    public static final String FIND_BY_LONGTEXT = "Value.findByLongText";
    public static final String FIND_BY_LONGTEXT_CONTAINING ="Value.findByLongTextContaining";
    public static final String FIND_BY_DATE = "Value.findByDate";
    public static final String FIND_BY_INTEGER = "Value.findByInteger";
    public static final String FIND_BY_DECIMAL = "Value.findByDecimal";
    public static final String FIND_BY_FLAG = "Value.findByFlag";

    @Id @Column(name="id" )
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="short_text"  , length=150 , nullable=true , unique=false)
    private String shortText;

    @Column(name="long_text"   , nullable=true , unique=false)
    private String longText;

    @Column(name="date"   , nullable=true , unique=false)
    private Timestamp date;

    @Column(name="integer"   , nullable=true , unique=false)
    private Integer integer;

    @Column(name="decimal"   , nullable=true , unique=false)
    private java.math.BigDecimal decimal;

    @Column(name="flag"   , nullable=true , unique=false)
    private Boolean flag;

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
            String shortText,
            String longText,
            Timestamp date,
            Integer integer,
            java.math.BigDecimal decimal,
            Boolean flag,
            Integer valueDefId,
            Integer itemId) {
        this(
            id,
            shortText,
            longText,
            date,
            integer,
            decimal,
            flag,
            valueDefId,
            itemId
            ,true);
    }

    public Value(
            Integer id,
            String shortText,
            String longText,
            Timestamp date,
            Integer integer,
            java.math.BigDecimal decimal,
            Boolean flag,
            Integer valueDefId,
            Integer itemId
            , boolean setRelationship) {
        setId (id);
        setShortText (shortText);
        setLongText (longText);
        setDate (date);
        setInteger (integer);
        setDecimal (decimal);
        setFlag (flag);
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
            getShortText(),
            getLongText(),
            getDate(),
            getInteger(),
            getDecimal(),
            getFlag(),
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

    public String getShortText() {
        return shortText;
    }

    public void setShortText (String shortText) {
        this.shortText =  shortText;
    }

    public String getLongText() {
        return longText;
    }

    public void setLongText (String longText) {
        this.longText =  longText;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate (Timestamp date) {
        this.date =  date;
    }

    public Integer getInteger() {
        return integer;
    }

    public void setInteger (Integer integer) {
        this.integer =  integer;
    }

    public java.math.BigDecimal getDecimal() {
        return decimal;
    }

    public void setDecimal (java.math.BigDecimal decimal) {
        this.decimal =  decimal;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag (Boolean flag) {
        this.flag =  flag;
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