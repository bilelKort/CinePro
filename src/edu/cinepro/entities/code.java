/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cinepro.entities;


/**
 *
 * @author MOEµNESS
 */
public final class code {
    private static code instance;

    private String codeConfirmation;
    
    

    public code(String codeC) {
        this.codeConfirmation=codeC;
    }

    

    public static code getInstace() {
        return instance;
    }
    
    public static code getInstace(String codeC) {
        if(instance == null) {
            instance = new code(codeC);
        }
        return instance;
    }

    public String getCodeConfirmation() {
        return codeConfirmation;
    }

    public void setCodeConfirmation(String codeConfirmation) {
        this.codeConfirmation = codeConfirmation;
    }

    

    public void cleanUserSession() {
        codeConfirmation = "";// or null
        
    }

    @Override
    public String toString() {
        return "code:" + codeConfirmation;
    }
}
