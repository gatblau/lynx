package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity (name="Respondent")
@Table (name="\"respondent\"")
@NamedQueries ({
	 @NamedQuery(name="Respondent.findAll", query="SELECT a FROM Respondent a")
	,@NamedQuery(name="Respondent.findByFirstname", query="SELECT a FROM Respondent a WHERE a.firstname = :firstname")
	,@NamedQuery(name="Respondent.findByFirstnameContaining", query="SELECT a FROM Respondent a WHERE a.firstname like :firstname")
	,@NamedQuery(name="Respondent.findByLastname", query="SELECT a FROM Respondent a WHERE a.lastname = :lastname")
	,@NamedQuery(name="Respondent.findByLastnameContaining", query="SELECT a FROM Respondent a WHERE a.lastname like :lastname")
	,@NamedQuery(name="Respondent.findByEmail", query="SELECT a FROM Respondent a WHERE a.email = :email")
	,@NamedQuery(name="Respondent.findByEmailContaining", query="SELECT a FROM Respondent a WHERE a.email like :email")
	,@NamedQuery(name="Respondent.findByTelephone", query="SELECT a FROM Respondent a WHERE a.telephone = :telephone")
	,@NamedQuery(name="Respondent.findByTelephoneContaining", query="SELECT a FROM Respondent a WHERE a.telephone like :telephone")
	,@NamedQuery(name="Respondent.findByPwdHash", query="SELECT a FROM Respondent a WHERE a.pwdHash = :pwdHash")
	,@NamedQuery(name="Respondent.findByPwdHashContaining", query="SELECT a FROM Respondent a WHERE a.pwdHash like :pwdHash")
	,@NamedQuery(name="Respondent.findByEnabled", query="SELECT a FROM Respondent a WHERE a.enabled = :enabled")
	,@NamedQuery(name="Respondent.findByActivationCode", query="SELECT a FROM Respondent a WHERE a.activationCode = :activationCode")
	,@NamedQuery(name="Respondent.findByActivationCodeContaining", query="SELECT a FROM Respondent a WHERE a.activationCode like :activationCode")
})
public class Respondent implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String FIND_ALL = "Respondent.findAll";
    public static final String FIND_BY_FIRSTNAME = "Respondent.findByFirstname";
    public static final String FIND_BY_FIRSTNAME_CONTAINING ="Respondent.findByFirstnameContaining";
    public static final String FIND_BY_LASTNAME = "Respondent.findByLastname";
    public static final String FIND_BY_LASTNAME_CONTAINING ="Respondent.findByLastnameContaining";
    public static final String FIND_BY_EMAIL = "Respondent.findByEmail";
    public static final String FIND_BY_EMAIL_CONTAINING ="Respondent.findByEmailContaining";
    public static final String FIND_BY_TELEPHONE = "Respondent.findByTelephone";
    public static final String FIND_BY_TELEPHONE_CONTAINING ="Respondent.findByTelephoneContaining";
    public static final String FIND_BY_PWDHASH = "Respondent.findByPwdHash";
    public static final String FIND_BY_PWDHASH_CONTAINING ="Respondent.findByPwdHashContaining";
    public static final String FIND_BY_ENABLED = "Respondent.findByEnabled";
    public static final String FIND_BY_ACTIVATIONCODE = "Respondent.findByActivationCode";
    public static final String FIND_BY_ACTIVATIONCODE_CONTAINING ="Respondent.findByActivationCodeContaining";
	
    @Id @Column(name="id" ) 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="firstname"  , length=45 , nullable=false , unique=false)
    private String firstname;

    @Column(name="lastname"  , length=45 , nullable=false , unique=false)
    private String lastname;

    @Column(name="email"  , length=45 , nullable=false , unique=false)
    private String email; 

    @Column(name="telephone"  , length=45 , nullable=true , unique=false)
    private String telephone; 

    @Column(name="pwd_hash"  , length=70 , nullable=true , unique=false)
    private String pwdHash; 

    @Column(name="enabled"   , nullable=false , unique=false)
    private Boolean enabled; 

    @Column(name="activation_code"  , length=45 , nullable=true , unique=false)
    private String activationCode;

    @ManyToOne (fetch=FetchType.LAZY )
    @JoinColumn(name="country_id", referencedColumnName = "id" , nullable=true , unique=false , insertable=true, updatable=true) 
    private Country countryId;  

    @Column(name="country_id"  , nullable=true , unique=true, insertable=false, updatable=false)
    private Integer countryId_;

    @ManyToOne (fetch=FetchType.LAZY )
    @JoinColumn(name="group_id", referencedColumnName = "id" , nullable=true , unique=true  , insertable=true, updatable=true) 
    private Group groupId;  

    @Column(name="group_id"  , nullable=true , unique=true, insertable=false, updatable=false)
    private Integer groupId_;

    @ManyToOne (fetch=FetchType.LAZY )
    @JoinColumn(name="preferred_language_id", referencedColumnName = "id" , nullable=true , unique=true  , insertable=true, updatable=true) 
    private Language preferredLanguageId;  

    @Column(name="preferred_language_id"  , nullable=true , unique=true, insertable=false, updatable=false)
    private Integer preferredLanguageId_;

    @ManyToOne (fetch=FetchType.LAZY )
    @JoinColumn(name="role_id", referencedColumnName = "id" , nullable=true , unique=true  , insertable=true, updatable=true) 
    private Role roleId;  

    @Column(name="role_id"  , nullable=true , unique=true, insertable=false, updatable=false)
    private Integer roleId_;

    @OneToMany (targetEntity=Survey.class, fetch=FetchType.LAZY, mappedBy="lockedBy", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <Survey> surveyRespondentViaLockedBy = new HashSet<Survey>(); 

    @OneToMany (targetEntity=SurveyAdmin.class, fetch=FetchType.LAZY, mappedBy="respondentId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <SurveyAdmin> surveyAdminRespondentViaRespondentId = new HashSet<SurveyAdmin>(); 

    @OneToMany (targetEntity=SurveyRespondent.class, fetch=FetchType.LAZY, mappedBy="respondentId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <SurveyRespondent> surveyRespondentRespondentViaRespondentId = new HashSet<SurveyRespondent>(); 

    public Respondent() {
    }

    public Respondent(
       Integer id,
       String firstname,
       String lastname,
       String email,
       String telephone,
       String pwdHash,
       Integer roleId,
       Integer groupId,
       Boolean enabled,
       Integer preferredLanguageId,
       Integer countryId,
       String activationCode) {
	 this(
       id,
       firstname,
       lastname,
       email,
       telephone,
       pwdHash,
       roleId,
       groupId,
       enabled,
       preferredLanguageId,
       countryId,
       activationCode
	 ,true);
	}
    
	public Respondent(
       Integer id,
       String firstname,
       String lastname,
       String email,
       String telephone,
       String pwdHash,
       Integer roleId,
       Integer groupId,
       Boolean enabled,
       Integer preferredLanguageId,
       Integer countryId,
       String activationCode	
    , boolean setRelationship) {
       setId (id);
       setFirstname (firstname);
       setLastname (lastname);
       setEmail (email);
       setTelephone (telephone);
       setPwdHash (pwdHash);
       setEnabled (enabled);
       setActivationCode (activationCode);
       if (setRelationship && countryId!=null) {
          this.countryId = new Country();
          this.countryId.setId(countryId);
	      setCountryId_ (countryId);
       }
       if (setRelationship && groupId!=null) {
          this.groupId = new Group();
          this.groupId.setId(groupId);
	      setGroupId_ (groupId);
       }
       if (setRelationship && preferredLanguageId!=null) {
          this.preferredLanguageId = new Language();
          this.preferredLanguageId.setId(preferredLanguageId);
	      setPreferredLanguageId_ (preferredLanguageId);
       }
       if (setRelationship && roleId!=null) {
          this.roleId = new Role();
          this.roleId.setId(roleId);
	      setRoleId_ (roleId);
       }
    }

	public Respondent flat() {
	   return new Respondent(
          getId(),
          getFirstname(),
          getLastname(),
          getEmail(),
          getTelephone(),
          getPwdHash(),
          getRoleId_(),
          getGroupId_(),
          getEnabled(),
          getPreferredLanguageId_(),
          getCountryId_(),
          getActivationCode()
       , false
	   );
	}

    public Integer getId() {
        return id;
    }
	
    public void setId (Integer id) {
        this.id =  id;
    }

    public String getFirstname() {
        return firstname;
    }
	
    public void setFirstname (String firstname) {
        this.firstname =  firstname;
    }

    public String getLastname() {
        return lastname;
    }
	
    public void setLastname (String lastname) {
        this.lastname =  lastname;
    }

    public String getEmail() {
        return email;
    }
	
    public void setEmail (String email) {
        this.email =  email;
    }

    public String getTelephone() {
        return telephone;
    }
	
    public void setTelephone (String telephone) {
        this.telephone =  telephone;
    }

    public String getPwdHash() {
        return pwdHash;
    }
	
    public void setPwdHash (String pwdHash) {
        this.pwdHash =  pwdHash;
    }

    public Boolean getEnabled() {
        return enabled;
    }
	
    public void setEnabled (Boolean enabled) {
        this.enabled =  enabled;
    }

    public String getActivationCode() {
        return activationCode;
    }
	
    public void setActivationCode (String activationCode) {
        this.activationCode =  activationCode;
    }

    public Country getCountryId () {
    	return countryId;
    }
	
    public void setCountryId (Country countryId) {
    	this.countryId = countryId;
    }

    public Integer getCountryId_() {
        return countryId_;
    }
	
    public void setCountryId_ (Integer countryId) {
        this.countryId_ =  countryId;
    }
	
    public Group getGroupId () {
    	return groupId;
    }
	
    public void setGroupId (Group groupId) {
    	this.groupId = groupId;
    }

    public Integer getGroupId_() {
        return groupId_;
    }
	
    public void setGroupId_ (Integer groupId) {
        this.groupId_ =  groupId;
    }
	
    public Language getPreferredLanguageId () {
    	return preferredLanguageId;
    }
	
    public void setPreferredLanguageId (Language preferredLanguageId) {
    	this.preferredLanguageId = preferredLanguageId;
    }

    public Integer getPreferredLanguageId_() {
        return preferredLanguageId_;
    }
	
    public void setPreferredLanguageId_ (Integer preferredLanguageId) {
        this.preferredLanguageId_ =  preferredLanguageId;
    }
	
    public Role getRoleId () {
    	return roleId;
    }
	
    public void setRoleId (Role roleId) {
    	this.roleId = roleId;
    }

    public Integer getRoleId_() {
        return roleId_;
    }
	
    public void setRoleId_ (Integer roleId) {
        this.roleId_ =  roleId;
    }

    public Set<Survey> getSurveyRespondentViaLockedBy() {
        if (surveyRespondentViaLockedBy == null){
            surveyRespondentViaLockedBy = new HashSet<Survey>();
        }
        return surveyRespondentViaLockedBy;
    }

    public void setSurveyRespondentViaLockedBy (Set<Survey> surveyRespondentViaLockedBy) {
        this.surveyRespondentViaLockedBy = surveyRespondentViaLockedBy;
    }	
    
    public void addSurveyRespondentViaLockedBy (Survey element) {
    	    getSurveyRespondentViaLockedBy().add(element);
    }

    public Set<SurveyAdmin> getSurveyAdminRespondentViaRespondentId() {
        if (surveyAdminRespondentViaRespondentId == null){
            surveyAdminRespondentViaRespondentId = new HashSet<SurveyAdmin>();
        }
        return surveyAdminRespondentViaRespondentId;
    }

    public void setSurveyAdminRespondentViaRespondentId (Set<SurveyAdmin> surveyAdminRespondentViaRespondentId) {
        this.surveyAdminRespondentViaRespondentId = surveyAdminRespondentViaRespondentId;
    }	
    
    public void addSurveyAdminRespondentViaRespondentId (SurveyAdmin element) {
    	    getSurveyAdminRespondentViaRespondentId().add(element);
    }

    public Set<SurveyRespondent> getSurveyRespondentRespondentViaRespondentId() {
        if (surveyRespondentRespondentViaRespondentId == null){
            surveyRespondentRespondentViaRespondentId = new HashSet<SurveyRespondent>();
        }
        return surveyRespondentRespondentViaRespondentId;
    }

    public void setSurveyRespondentRespondentViaRespondentId (Set<SurveyRespondent> surveyRespondentRespondentViaRespondentId) {
        this.surveyRespondentRespondentViaRespondentId = surveyRespondentRespondentViaRespondentId;
    }	
    
    public void addSurveyRespondentRespondentViaRespondentId (SurveyRespondent element) {
    	    getSurveyRespondentRespondentViaRespondentId().add(element);
    }

    @PrePersist
    public void prePersist_ () {
    }

    @PreUpdate
    public void preUpdate_ () {
    }
}
