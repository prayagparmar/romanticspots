package com.bitchinc.romantics;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.geo.Box;
import org.springframework.data.mongodb.core.geo.Circle;
import org.springframework.data.mongodb.core.geo.Point;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("deprecation")
@Controller
@RequestMapping("/spot")
//@RepositoryRestResource(collectionResourceRel = "spot", path = "spot")

public class SpotService {
 
	@Autowired
	SpotRepository repo;
 
    public static final Double KILOMETER = 111.0d;
 
 
    @RequestMapping(value = "/get", method = RequestMethod.GET,
            produces = "application/json")
    public
    @ResponseBody
    List<Spot> getAll() throws Exception {
        return repo.findAll();
    }
 
    @RequestMapping(value = "/get", params={"long1","lat1","long2","lat2"},
            method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    List<Spot> getByBounds(@RequestParam("long1") Double lon1,
    		@RequestParam("lat1") Double lat1,
    		@RequestParam("long2") Double lon2,
    		@RequestParam("lat2") Double lat2) throws Exception {
        /**
         > box = [[40.73083, -73.99756], [40.741404,  -73.988135]]
         > db.places.find({"loc" : {"$within" : {"$box" : box}}})
         **/
        return repo.findByPositionWithin(new Box(new Point(lon1, lat1),
                new Point(lon2, lat2)));
    }
    @RequestMapping(value = "/get",params={"long","lat","rad"},
            method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    List<Spot> getWithinCircle(@RequestParam("long") Double centerLon,
                            @RequestParam("lat") Double centerLat,
                            @RequestParam("rad") Double radius
                            ) throws Exception {
        
        return repo.findByPositionWithin(new Circle(centerLon,centerLat,radius));
        
    }
 
    @RequestMapping(value = "/add", method = RequestMethod.POST,
            consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void add(@RequestBody Spot track) throws Exception {
        repo.save(track);
    }

}