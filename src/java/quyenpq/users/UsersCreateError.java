/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quyenpq.users;

import java.io.Serializable;

/**
 *
 * @author Goby
 */
public class UsersCreateError implements Serializable{
    protected String usernameLengthError;
    protected String passwordLengthError;
    protected String fullnameLengthError;
    protected String confirmPasswordNotMatch;
    protected String usernameIsExisted;

    public UsersCreateError() {
    }

    public String getUsernameLengthError() {
        return usernameLengthError;
    }

    public String getPasswordLengthError() {
        return passwordLengthError;
    }

    public String getFullnameLengthError() {
        return fullnameLengthError;
    }

    public String getConfirmPasswordNotMatch() {
        return confirmPasswordNotMatch;
    }

    public String getUsernameIsExisted() {
        return usernameIsExisted;
    }

    public void setUsernameLengthError(String usernameLengthError) {
        this.usernameLengthError = usernameLengthError;
    }

    public void setPasswordLengthError(String passwordLengthError) {
        this.passwordLengthError = passwordLengthError;
    }

    public void setFullnameLengthError(String fullnameLengthError) {
        this.fullnameLengthError = fullnameLengthError;
    }

    public void setConfirmPasswordNotMatch(String confirmPasswordNotMatch) {
        this.confirmPasswordNotMatch = confirmPasswordNotMatch;
    }

    public void setUsernameIsExisted(String usernameIsExisted) {
        this.usernameIsExisted = usernameIsExisted;
    }
    
    
    
}
