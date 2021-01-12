
package dto;

import entities.Characters;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author miade
 */
public class StandartDTO {
    
    private String time;
    
    private List<CharacterDTO> characterDTOList = new ArrayList();

    public StandartDTO(List<CharacterDTO> cList, String time) {
        this.time = time;
        
        this.characterDTOList = cList;
        
    }

    public StandartDTO() {
    }
    
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public List<CharacterDTO> getCharacterDTOList() {
        return characterDTOList;
    }

    public void setCharacterDTOList(List<CharacterDTO> characterDTOList) {
        this.characterDTOList = characterDTOList;
    }

   
    
}
