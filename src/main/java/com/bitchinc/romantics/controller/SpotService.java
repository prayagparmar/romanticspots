package com.bitchinc.romantics.controller;

import com.bitchinc.romantics.pojo.Spot;
import com.bitchinc.romantics.pojo.SpotBuilder;
import com.bitchinc.romantics.pojo.SpotJSON;
import com.bitchinc.romantics.service.SpotRepository;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.geo.Box;
import org.springframework.data.mongodb.core.geo.Circle;
import org.springframework.data.mongodb.core.geo.Point;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/spot")
public class SpotService {
    @Autowired
    SpotRepository repo;
    @Autowired
    ImageService imageService;
    //StandardServletMultipartResolver standardServletMultipartResolver;

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET,
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
        return getJsonSpots(repo.findByPositionWithin(new Box(new Point(lat1, lon1),
                new Point(lat2, lon2))));
    }

    @RequestMapping(value = "/", params = {"long", "lat", "rad"},
            method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    ArrayList<SpotJSON> getWithinCircle(@RequestParam("long") Double centerLon,
                                        @RequestParam("lat") Double centerLat,
                                        @RequestParam("rad") Double radius
    ) throws Exception {

        return getJsonSpots(repo.findByPositionWithin(new Circle(centerLat, centerLon, radius)));

    }

    @RequestMapping(value = "/", method = RequestMethod.POST,//consumes = "application/json")
            consumes = {"multipart/mixed", "multipart/form-data"})
    public
    @ResponseBody
    String add(@RequestPart(value = "file", required = false) MultipartFile file,
               @RequestParam(value = "latitude") Double latitude,
               @RequestParam(value = "longitude") Double longitude,
               @RequestParam(value = "description") String description,
               @RequestParam(value = "secluded", required = false) Boolean secluded,
               @RequestParam(value = "rating", required = false) Double rating)
            throws Exception {
        GridFSFile uploadedFile = null;
        GridFSDBFile gridFSDBFile = null;
        if (file != null) {
            uploadedFile = imageService.uploadPhoto(file);
            gridFSDBFile = imageService.getPhoto("test3.png");
            System.out.println(gridFSDBFile);
            System.out.println(gridFSDBFile.getId());
            gridFSDBFile.writeTo("/Users/shashankdass/Desktop/newTest.png");


        }
        return repo.save(getSpot(latitude, longitude, description, secluded, rating, gridFSDBFile)).getId();
    }
//    @RequestMapping(value = "/",
//            method = RequestMethod.PUT,
//            consumes = "application/json")
//    public
//    @ResponseBody
//    String update(@RequestBody SpotJSON spotJSON) throws Exception {
//        Spot spot = getSpot(spotJSON);
//        Spot spotInRepo = repo.findOne(spot.getId());
//        String description = spot.getDescription().equals("") ? spotInRepo.getDescription() : spot.getDescription();
//        double[] position = spot.getPosition() == spotInRepo.getPosition() ? spotInRepo.getPosition() : spot.getPosition();
//        double ratings = spot.getRating() == spotInRepo.getRating() ? spotInRepo.getRating() : spot.getRating();
//        boolean secluded = spot.isSecluded() == spotInRepo.isSecluded() ? spotInRepo.isSecluded() : spot.isSecluded();
//        byte[] image = spot.getImageFile() == spotInRepo.getImageFile() ? spotInRepo.getImageFile() : spot.getImageFile();
//        Spot updatedSpot = new Spot(spotInRepo.getId(), description, position, ratings, secluded, image  );
//        return repo.save(updatedSpot).getId();
//    }

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
        SpotJSON jsonSpot = new SpotJSON(spot.getId(), spot.getPosition()[0], spot.getPosition()[1],
                spot.getDescription(), spot.getRating(), spot.isSecluded(), spot.getImageFile());
        return jsonSpot;
    }

//    private Spot getSpot(SpotJSON jsonSpot, Object imageId) {
//        double[] position = {jsonSpot.getLatitude(), jsonSpot.getLongitude()};
//        Object imId = null;
//        Boolean secluded = false;
//        double ratings = 0.0;
//      //  double ratings = jsonSpot.getRating() != 0 ? jsonSpot.getRating() : 0;
//        //double
//        imId = !imageId.equals("")?imageId:"";
//        secluded = jsonSpot.isSecluded()?true:false;
//        ratings = jsonSpot.getRating() != 0.0?jsonSpot.getRating():0.0;
//        return new
//                SpotBuilder(jsonSpot.getDescription(), position).
//                addImageFile(imId).
//                addRatings(ratings).
//                addSecluded(secluded).
//                build();
//    }

    private Spot getSpot(Double latitude, Double longitude, String description, Boolean secluded,
                         Double rating, GridFSDBFile gridFSDBFile) {
        double[] position = {latitude, longitude};
        GridFSDBFile imageFile = null;
        Boolean sec = false;
        double ratings = 0.0;
        //  double ratings = jsonSpot.getRating() != 0 ? jsonSpot.getRating() : 0;
        //double
        imageFile = !(gridFSDBFile==null) ? gridFSDBFile : null;
        sec = secluded == null ? false : secluded;
        ratings = rating != 0.0 ? rating : 0.0;
        return new
                SpotBuilder().addDescription(description).
                addPosition(position).
                addImageFile(imageFile).
                addRatings(ratings).
                addSecluded(sec).
                build();
    }

    private Spot getSpot(SpotJSON jsonSpot) {
        double[] position = {jsonSpot.getLatitude(), jsonSpot.getLongitude()};
        //  double ratings = jsonSpot.getRating() != 0 ? jsonSpot.getRating() : 0;
        //double

        return new Spot(jsonSpot.getId(), jsonSpot.getDescription(), position);
    }


}