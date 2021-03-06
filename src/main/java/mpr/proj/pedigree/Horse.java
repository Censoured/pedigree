package mpr.proj.pedigree;

public class Horse {

    private long id;
    private String name;
    private Sex sex;
    private DateOfBirth dob;
    private Color color;
    private Horse sire;
    private Horse dam;
    private Breeder breeder;
    private int yearonly;

    public Horse(long id, String name, Sex sex, DateOfBirth dob, Color color, Horse sire, Horse dam, Breeder breeder) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.dob = dob;
        this.color = color;
        this.sire = sire;
        this.dam = dam;
        this.breeder = breeder;
        
    }
    
    public Horse(long id, String name, Sex sex, DateOfBirth dob, int yearonly, Color color, Horse sire, Horse dam, Breeder breeder) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.dob = dob;
        this.color = color;
        this.sire = sire;
        this.dam = dam;
        this.breeder = breeder;
        this.yearonly = yearonly;
        
    }
    public Horse()	{
    	
    }

   

	public void setName(String name) {
    	this.name = name;
    }
    
    public void setID(long id){
    	this.id = id;
    }
    
    public long getID()	{
    	return id;
    }

    public String getName() {
    	return name;
    }
    
    public int getYearOnly()	{
    	return yearonly;
    }

    public void setSex(Sex sex)	{
    	this.sex = sex;
    }
    
    public Sex getSex()	{
    	return sex;
    }
    
    public void setDob(DateOfBirth dob)	{
    	this.dob = dob;
    }
    
    public DateOfBirth getDob()	{
    	return dob;
    }
    
    public void setColor(Color color){
    	this.color = color;
    }
    
    public Color getColor()	{
    	return color;
    }
    
    public void setSire(Horse sire)	{
    	this.sire = sire;
    }
    
    public Horse getSire()	{
    	return sire;
    }
    
    public void setDam(Horse dam)	{
    	this.dam = dam;
    }
    
    public Horse getDam()	{
    	return dam;
    }
    
    public void setBreeder(Breeder breeder){
    	this.breeder = breeder;
    }
    
    public Breeder getBreeder()	{
    	return breeder;
    }
    
    @Override
    public String toString() {
            return String.format("%4d %15s %8s %10s %8s %15s %15s %15s",
                            this.id,
                            this.name,
                            this.sex.toString(),
                            this.dob.toString(),
                            this.color.getSname(),
                            this.sire != null ? this.sire.getName() : "Nieznane",
                            this.dam != null ? this.dam.getName() : "Nieznane",
                            this.breeder != null ? this.breeder.getName() : "Nieznane");
    }
}
