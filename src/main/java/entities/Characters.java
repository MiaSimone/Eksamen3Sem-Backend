
package entities;

/**
 *
 * @author miade
 */
public class Characters {
    
    /*
    {"name":"Harry Potter",
    "species":"human",
    "gender":"male",'
    "house":"Gryffindor",
    "dateOfBirth":"31-07-1980",
    "yearOfBirth":1980,
    "ancestry":"half-blood",
    "eyeColour":"green",
    "hairColour":"black",
    "wand":{
            "wood":"holly",
            "core":"phoenix feather",
            "length":11
    },
    "patronus":"stag",
    "hogwartsStudent":true,
    "hogwartsStaff":false,
    "actor":"Daniel Radcliffe",
    "alive":true,
    "image":"http://hp-api.herokuapp.com/images/harry.jpg"}
    */
    
    private String name;
    private String species;
    private String gender;
    private String house;
    private String dateOfBirth;
    private int yearOfBirth;
    private String ancestry;
    private String eyeColour;
    private String hairColour;
    private String patronus;
    private boolean hogwartsStudent;
    private boolean hogwartsStaff;
    private String actor;
    private boolean alive;
    private String image;
    
    private Wand wand;

    public Characters(String name, String species, String gender, String house, 
            String dateOfBirth, int yearOfBirth, String ancestry, String eyeColour, 
            String hairColour, String patronus, boolean hogwartsStudent, 
            boolean hogwartsStaff, String actor, boolean alive, String image, Wand wand) {
        this.name = name;
        this.species = species;
        this.gender = gender;
        this.house = house;
        this.dateOfBirth = dateOfBirth;
        this.yearOfBirth = yearOfBirth;
        this.ancestry = ancestry;
        this.eyeColour = eyeColour;
        this.hairColour = hairColour;
        this.patronus = patronus;
        this.hogwartsStudent = hogwartsStudent;
        this.hogwartsStaff = hogwartsStaff;
        this.actor = actor;
        this.alive = alive;
        this.image = image;
        
        this.wand = wand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getAncestry() {
        return ancestry;
    }

    public void setAncestry(String ancestry) {
        this.ancestry = ancestry;
    }

    public String getEyeColour() {
        return eyeColour;
    }

    public void setEyeColour(String eyeColour) {
        this.eyeColour = eyeColour;
    }

    public String getHairColour() {
        return hairColour;
    }

    public void setHairColour(String hairColour) {
        this.hairColour = hairColour;
    }

    public String getPatronus() {
        return patronus;
    }

    public void setPatronus(String patronus) {
        this.patronus = patronus;
    }

    public boolean isHogwartsStudent() {
        return hogwartsStudent;
    }

    public void setHogwartsStudent(boolean hogwartsStudent) {
        this.hogwartsStudent = hogwartsStudent;
    }

    public boolean isHogwartsStaff() {
        return hogwartsStaff;
    }

    public void setHogwartsStaff(boolean hogwartsStaff) {
        this.hogwartsStaff = hogwartsStaff;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Wand getWand() {
        return wand;
    }

    public void setWand(Wand wand) {
        this.wand = wand;
    }
    
    
    
    
    
    
    
}
