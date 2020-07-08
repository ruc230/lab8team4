/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.*;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.*;

/**
 * REST Web Service
 *
 * @author Ryan Carey
 */
@Path("generic/bets")
public class Bets {

    @Context
    private UriInfo context;
    private ArrayList<String> userBets;
    private ArrayList<String> bets;
    private int betID = 0;
    
    /**
     * Creates a new instance of GenericResource
     */
    public Bets() {
    }

    /**
     * Retrieves representation of an instance of services.GenericResource
     * @return an instance of java.lang.String
     */
    @QueryParam("type")
    String betType;
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String getBets() {
        //TODO return proper representation object
        return "<html lang=\"en\"><body><h1>" + bets.toString() + "</body></h1></html>";
    }

    @Path("/{:username}")
    @GET
    @Produces("text/html")
    public String getUserBets(@PathParam("username") String username) {
        bets.forEach((String bet) -> {
            if (bet.contains(username)) {
                userBets.add(bet);
            }
        });
        return userBets.toString();
    }
    
    /**
     * PUT method for updating or creating an instance of GenericResource
     * @param content representation for the resource
     */
    @Path("new")
    @POST
    @Consumes(MediaType.TEXT_HTML)
    public void placeBet(String content) {
        bets.add(betID + ": " + content);
    }
    
    @Path("update")
    @PUT
    @Consumes(MediaType.TEXT_HTML)
    public void changeBet(String content, @QueryParam("betID") String betID) {
        bets.forEach(bet -> {
            if(bet.startswith(betID)) {
                bet = content;
            }
        });
    }
    
    @Path("delete")
    @DELETE
    public void deleteBet(@QueryParam("bedID") String betID) {
        bets.forEach(bet -> {
            if(bet.startswith(betID)){
                bets.remove(bet);
            }
        });
    }
     
}
