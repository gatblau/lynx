/**
	* Copyright (c) minuteproject, minuteproject@gmail.com
	* All rights reserved.
	* 
	* Licensed under the Apache License, Version 2.0 (the "License")
	* you may not use this file except in compliance with the License.
	* You may obtain a copy of the License at
	* 
	* http://www.apache.org/licenses/LICENSE-2.0
	* 
	* Unless required by applicable law or agreed to in writing, software
	* distributed under the License is distributed on an "AS IS" BASIS,
	* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	* See the License for the specific language governing permissions and
	* limitations under the License.
	* 
	* More information on minuteproject:
	* twitter @minuteproject
	* wiki http://minuteproject.wikispaces.com 
	* blog http://minuteproject.blogspot.net
	* 
*/
/**
	* template reference : 
	* - Minuteproject version : 0.9.8
	* - name      : DomainEntityJPA2Annotation
	* - file name : DomainEntityJPA2Annotation.vm
	* - time      : 2016/09/04 AD at 08:23:24 BST
*/
package model;

//MP-MANAGED-ADDED-AREA-BEGINNING @import@
//MP-MANAGED-ADDED-AREA-ENDING @import@
import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

import java.io.Serializable;
import javax.persistence.*;
import model.Survey;
import model.SurveyRespondent;
import model.Country;
import model.Group;
import model.Language;
import model.Role;

/**
 *
 * <p>Title: Respondent</p>
 *
 * <p>Description: Domain Object describing a Respondent entity</p>
 *
 */
@Entity (name="Respondent")
@Table (name="\"respondent\"")
@NamedQueries ({
	 @NamedQuery(name="Respondent.findAll", query="SELECT a FROM Respondent a")
	,@NamedQuery(name="Respondent.findByFirstname", query="SELECT a FROM Respondent a WHERE a.firstname = :firstname")
	,@NamedQuery(name="Respondent.findByFirstnameContaining", query="SELECT a FROM Respondent a WHERE a.firstname like :firstname")
//MP-MANAGED-ADDED-AREA-BEGINNING @custom-queries@
//MP-MANAGED-ADDED-AREA-ENDING @custom-queries@
	,@NamedQuery(name="Respondent.findByLastname", query="SELECT a FROM Respondent a WHERE a.lastname = :lastname")
	,@NamedQuery(name="Respondent.findByLastnameContaining", query="SELECT a FROM Respondent a WHERE a.lastname like :lastname")
//MP-MANAGED-ADDED-AREA-BEGINNING @custom-queries@
//MP-MANAGED-ADDED-AREA-ENDING @custom-queries@
	,@NamedQuery(name="Respondent.findByEmail", query="SELECT a FROM Respondent a WHERE a.email = :email")
	,@NamedQuery(name="Respondent.findByEmailContaining", query="SELECT a FROM Respondent a WHERE a.email like :email")
//MP-MANAGED-ADDED-AREA-BEGINNING @custom-queries@
//MP-MANAGED-ADDED-AREA-ENDING @custom-queries@
	,@NamedQuery(name="Respondent.findByTelephone", query="SELECT a FROM Respondent a WHERE a.telephone = :telephone")
	,@NamedQuery(name="Respondent.findByTelephoneContaining", query="SELECT a FROM Respondent a WHERE a.telephone like :telephone")
//MP-MANAGED-ADDED-AREA-BEGINNING @custom-queries@
//MP-MANAGED-ADDED-AREA-ENDING @custom-queries@
	,@NamedQuery(name="Respondent.findByPwdHash", query="SELECT a FROM Respondent a WHERE a.pwdHash = :pwdHash")
	,@NamedQuery(name="Respondent.findByPwdHashContaining", query="SELECT a FROM Respondent a WHERE a.pwdHash like :pwdHash")
//MP-MANAGED-ADDED-AREA-BEGINNING @custom-queries@
//MP-MANAGED-ADDED-AREA-ENDING @custom-queries@
	,@NamedQuery(name="Respondent.findByEnabled", query="SELECT a FROM Respondent a WHERE a.enabled = :enabled")
//MP-MANAGED-ADDED-AREA-BEGINNING @custom-queries@
//MP-MANAGED-ADDED-AREA-ENDING @custom-queries@
	,@NamedQuery(name="Respondent.findByActivationCode", query="SELECT a FROM Respondent a WHERE a.activationCode = :activationCode")
	,@NamedQuery(name="Respondent.findByActivationCodeContaining", query="SELECT a FROM Respondent a WHERE a.activationCode like :activationCode")
//MP-MANAGED-ADDED-AREA-BEGINNING @custom-queries@
//MP-MANAGED-ADDED-AREA-ENDING @custom-queries@
})
//MP-MANAGED-ADDED-AREA-BEGINNING @custom-annotations@
//MP-MANAGED-ADDED-AREA-ENDING @custom-annotations@
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

//MP-MANAGED-ADDED-AREA-BEGINNING @firstname-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @firstname-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-firstname@
    @Column(name="firstname"  , length=45 , nullable=false , unique=false)
    private String firstname; 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @lastname-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @lastname-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-lastname@
    @Column(name="lastname"  , length=45 , nullable=false , unique=false)
    private String lastname; 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @email-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @email-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-email@
    @Column(name="email"  , length=45 , nullable=false , unique=false)
    private String email; 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @telephone-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @telephone-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-telephone@
    @Column(name="telephone"  , length=45 , nullable=true , unique=false)
    private String telephone; 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @pwd_hash-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @pwd_hash-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-pwd_hash@
    @Column(name="pwd_hash"  , length=100 , nullable=true , unique=false)
    private String pwdHash; 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @enabled-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @enabled-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-enabled@
    @Column(name="enabled"   , nullable=false , unique=false)
    private Boolean enabled; 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @activation_code-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @activation_code-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-activation_code@
    @Column(name="activation_code"  , length=45 , nullable=true , unique=false)
    private String activationCode; 
//MP-MANAGED-UPDATABLE-ENDING

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="country_id", referencedColumnName = "id" , nullable=false , unique=false , insertable=true, updatable=true) 
    private Country countryId;  

    @Column(name="country_id"  , nullable=false , unique=true, insertable=false, updatable=false)
    private Integer countryId_;

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="group_id", referencedColumnName = "id" , nullable=false , unique=true  , insertable=true, updatable=true) 
    private Group groupId;  

    @Column(name="group_id"  , nullable=false , unique=true, insertable=false, updatable=false)
    private Integer groupId_;

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="preferred_language_id", referencedColumnName = "id" , nullable=false , unique=true  , insertable=true, updatable=true) 
    private Language preferredLanguageId;  

    @Column(name="preferred_language_id"  , nullable=false , unique=true, insertable=false, updatable=false)
    private Integer preferredLanguageId_;

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="role_id", referencedColumnName = "id" , nullable=false , unique=true  , insertable=true, updatable=true) 
    private Role roleId;  

    @Column(name="role_id"  , nullable=false , unique=true, insertable=false, updatable=false)
    private Integer roleId_;

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @surveyRespondentViaLockedBy-field-respondent@
    @OneToMany (targetEntity=model.Survey.class, fetch=FetchType.LAZY, mappedBy="lockedBy", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <Survey> surveyRespondentViaLockedBy = new HashSet<Survey>(); 

//MP-MANAGED-UPDATABLE-ENDING
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @surveyRespondentRespondentViaRespondentId-field-respondent@
    @OneToMany (targetEntity=model.SurveyRespondent.class, fetch=FetchType.LAZY, mappedBy="respondentId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <SurveyRespondent> surveyRespondentRespondentViaRespondentId = new HashSet<SurveyRespondent>(); 

//MP-MANAGED-UPDATABLE-ENDING
    /**
    * Default constructor
    */
    public Respondent() {
    }

	/**
	* All field constructor 
	*/
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
       //primary keys
       setId (id);
       //attributes
       setFirstname (firstname);
       setLastname (lastname);
       setEmail (email);
       setTelephone (telephone);
       setPwdHash (pwdHash);
       setEnabled (enabled);
       setActivationCode (activationCode);
       //parents
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
    
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-firstname@
    public String getFirstname() {
        return firstname;
    }
	
    public void setFirstname (String firstname) {
        this.firstname =  firstname;
    }
	
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-lastname@
    public String getLastname() {
        return lastname;
    }
	
    public void setLastname (String lastname) {
        this.lastname =  lastname;
    }
	
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-email@
    public String getEmail() {
        return email;
    }
	
    public void setEmail (String email) {
        this.email =  email;
    }
	
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-telephone@
    public String getTelephone() {
        return telephone;
    }
	
    public void setTelephone (String telephone) {
        this.telephone =  telephone;
    }
	
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-pwd_hash@
    public String getPwdHash() {
        return pwdHash;
    }
	
    public void setPwdHash (String pwdHash) {
        this.pwdHash =  pwdHash;
    }
	
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-enabled@
    public Boolean getEnabled() {
        return enabled;
    }
	
    public void setEnabled (Boolean enabled) {
        this.enabled =  enabled;
    }
	
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-activation_code@
    public String getActivationCode() {
        return activationCode;
    }
	
    public void setActivationCode (String activationCode) {
        this.activationCode =  activationCode;
    }
	
//MP-MANAGED-UPDATABLE-ENDING


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
	

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @surveyRespondentViaLockedBy-getter-respondent@
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
    
//MP-MANAGED-UPDATABLE-ENDING
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @surveyRespondentRespondentViaRespondentId-getter-respondent@
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
    
//MP-MANAGED-UPDATABLE-ENDING



//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@

}
