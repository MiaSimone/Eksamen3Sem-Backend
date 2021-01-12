
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fetchers.CharacterFetcher;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author miade
 */
@Path("potter")
public class CharacterResource {
    
    
    @Context
    private UriInfo context;
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static ExecutorService es = Executors.newCachedThreadPool();
    private static String cachedResponse;
    
    
    @GET
    @Path("characters")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCharacters() throws Exception {
        String result = CharacterFetcher
                .responseFromExternalServerParrallel(es, GSON, "http://hp-api.herokuapp.com/api/characters");
        cachedResponse = result;
        return result;
    }
    
    @GET
    @Path("students")
    @Produces(MediaType.APPLICATION_JSON)
    public String getStudents() throws Exception {
        String result = CharacterFetcher
                .responseFromExternalServerParrallel(es, GSON, "http://hp-api.herokuapp.com/api/characters/students");
        cachedResponse = result;
        return result;
    }
    
    @GET
    @Path("staff")
    @Produces(MediaType.APPLICATION_JSON)
    public String getStaff() throws Exception {
        String result = CharacterFetcher
                .responseFromExternalServerParrallel(es, GSON, "http://hp-api.herokuapp.com/api/characters/staff");
        cachedResponse = result;
        return result;
    }
    
    
    @GET
    @Path("students/house/{house}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getStaff(@PathParam("house") String house) throws Exception{
        String result = CharacterFetcher
                .responseFromExternalServerParrallel(es, GSON, 
                        "http://hp-api.herokuapp.com/api/characters/house/" + house);
        cachedResponse = result;
        return result;
    }
    
}
