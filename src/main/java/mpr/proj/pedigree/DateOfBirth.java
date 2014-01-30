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
    @SuppressWarnings("deprecation")
	public String getDate() {
        if (yearOnly) {
            return Integer.toString(date.getYear());
        } else {
            return date.toString();
        }
    }
    @SuppressWarnings("deprecation")
	public int getYear()	{
    	return date.getYear();
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
