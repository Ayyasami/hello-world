/* $Id$ */ 
package com.adventnet.antispam.api;

public class FormsSPAMConstants 
{
    public static final int JUNK_FORM		= 0;
    public static final int SPAM_FORM		= 1;
    public static final int POSSIBLE_SPAM_FORM	= 2;
    public static final int UNKNOWN_FORM	= 3;
    public static final int NOTSPAM_FORM	= 4;

    public static final String[] FORMS_TYPE_STRING=new String[] {"JUNK","SPAM", "POSSIBLE SPAM", "UNKNOWN", "NOT SPAM"};//No I18N

}// FormsResultConstants
