package mpr.proj.pedigree;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class DateOfBirth {
    private Date date;
    private Boolean yearOnly;
    
    public DateOfBirth(Date dob, boolean yo)	{
    	this.date = dob;
    	this.yearOnly = yo;
    }

    public void setDate(Date date, Boolean yearOnly) {
        this.date = date;
        this.yearOnly = yearOnly;
    }
    public String getDate() {
        if (yearOnly) {
            return "2013";
        } else {
            return "2013-12-14";
        }
    }
    
    @Override
    public String toString() {
	    if(yearOnly) {
	        return new SimpleDateFormat("yyyy").format(date);
	    } else {
	        return new SimpleDateFormat("yyyy-MM-dd").format(date);
	    }
    }
}
