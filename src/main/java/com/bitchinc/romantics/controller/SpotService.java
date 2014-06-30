package com.bitchinc.romantics.controller;

import com.bitchinc.romantics.pojo.Spot;
import com.bitchinc.romantics.pojo.SpotJSON;
import com.bitchinc.romantics.service.SpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.geo.Box;
import org.springframework.data.mongodb.core.geo.Circle;
import org.springframework.data.mongodb.core.geo.Point;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/spot")
public class SpotService {
    @Autowired
    SpotRepository repo;

    @RequestMapping(value = "/", method = RequestMethod.GET,
            produces = "application/json")
    public
    @ResponseBody
    List<SpotJSON> getAll() throws Exception {
        return getJsonSpots(repo.findAll());
    }

    @RequestMapping(value = "/", params = {"id"}, method = RequestMethod.GET,
            produces = "application/json")
    public
    @ResponseBody
    SpotJSON getById(@RequestParam("id") String id) throws Exception {
        return getJsonSpot(repo.findOne(id));
    }

    @RequestMapping(value = "/", params = {"long1", "lat1", "long2", "lat2"},
            method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    ArrayList<SpotJSON> getByBounds(@RequestParam("long1") Double lon1,
                                    @RequestParam("lat1") Double lat1,
                                    @RequestParam("long2") Double lon2,
                                    @RequestParam("lat2") Double lat2) throws Exception {
        /**
         > box = [[40.73083, -73.99756], [40.741404,  -73.988135]]
         > db.places.find({"loc" : {"$within" : {"$box" : box}}})
         **/
        return getJsonSpots(repo.findByPositionWithin(new Box(new Point(lon1, lat1),
                new Point(lon2, lat2))));
    }

    @RequestMapping(value = "/", params = {"long", "lat", "rad"},
            method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    ArrayList<SpotJSON> getWithinCircle(@RequestParam("long") Double centerLon,
                                        @RequestParam("lat") Double centerLat,
                                        @RequestParam("rad") Double radius
    ) throws Exception {

        return getJsonSpots(repo.findByPositionWithin(new Circle(centerLon, centerLat, radius)));

    }

    @RequestMapping(value = "/add", method = RequestMethod.POST,
            consumes = "application/json")
    public
    @ResponseBody
    String add(@RequestBody SpotJSON spotJSON) throws Exception {
        return repo.save(getSpot(spotJSON)).getId();
    }

    private ArrayList<SpotJSON> getJsonSpots(List<Spot> spots) {
        ArrayList<SpotJSON> JSONSpots = new ArrayList<SpotJSON>();
        for (Spot spot : spots) {
            System.out.println(spot.toString());
            SpotJSON jsonSpot = getJsonSpot(spot);
            JSONSpots.add(jsonSpot);
        }

        return JSONSpots;

    }

    private SpotJSON getJsonSpot(Spot spot) {
        SpotJSON jsonSpot = new SpotJSON(spot.getId(), spot.getDescription(), spot.getPosition()[0], spot.getPosition()[1]);
        return jsonSpot;
    }

    private Spot getSpot(SpotJSON jsonSpot) {
        double[] position = {jsonSpot.getLatitude(), jsonSpot.getLongitude()};

        return new Spot(jsonSpot.getId(), jsonSpot.getDescription(), position);
    }

}