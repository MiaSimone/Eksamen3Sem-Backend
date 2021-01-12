
package fetchers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import dto.CharacterDTO;
import dto.StandartDTO;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import utils.HttpUtils;

/**
 *
 * @author miade
 */
public class CharacterFetcher {
    
    public static String responseFromExternalServerParrallel(ExecutorService threadPool, 
            final Gson gson, String FACT_SERVER) throws Exception{
        long start = System.nanoTime();
        
        Callable<List<CharacterDTO>> charTask = new Callable<List<CharacterDTO>>(){
            @Override
            public List<CharacterDTO> call() throws Exception {
                String charString = HttpUtils.fetchData(FACT_SERVER);
                
                Type collectionType = new TypeToken<List<CharacterDTO>>(){}.getType();
                List<CharacterDTO> lcs = (List<CharacterDTO>) new Gson()
                    .fromJson( charString , collectionType);
                
                return lcs;
            }
            
        };
        Future<List<CharacterDTO>> futureChar = threadPool.submit(charTask);
        
        List<CharacterDTO> charDTOList = futureChar.get(2, TimeUnit.SECONDS);
        
        long end = System.nanoTime(); 
        String time = "Time Parallel: " + ((end-start)/1_000_000) + " ms.";
        
        StandartDTO sDTO = new StandartDTO(charDTOList, time);
        
        String standartJSON = gson.toJson(sDTO);
          
        return standartJSON;
    }    
    
    
}
