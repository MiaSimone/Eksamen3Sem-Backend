
package dto;

import entities.Characters;
import java.util.ArrayList;

/**
 *
 * @author miade
 */
public class CharacterDTO {
    
    
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
    private String house;
    private String dateOfBirth;
    private String ancestry;
    private String eyeColour;
    private String hairColour;
    private String patronus;
    private boolean hogwartsStudent;
    private boolean hogwartsStaff;
    private String actor;
    private boolean alive;
    private String image;

    //private ArrayList<Characters> characterList;
    
    public CharacterDTO() {
    }

    public CharacterDTO(Characters c) {
        //this.characterList = new ArrayList();
        //characterList.add(c);
        
        this.name = c.getName();
        this.house = c.getHouse();
        this.dateOfBirth = c.getDateOfBirth();
        this.ancestry = c.getAncestry();
        this.eyeColour = c.getEyeColour();
        this.hairColour = c.getHairColour();
        this.patronus = c.getPatronus();
        this.hogwartsStudent = c.isHogwartsStudent();
        this.hogwartsStaff = c.isHogwartsStaff();
        this.actor = c.getActor();
        this.alive = c.isAlive();
        this.image = c.getImage();

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    
    
    /*

    public ArrayList<Characters> getCharacterList() {
        return characterList;
    }

    public void setCharacterList(ArrayList<Characters> characterList) {
        this.characterList = characterList;
    }
    
    
    */
    
}
