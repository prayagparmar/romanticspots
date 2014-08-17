package com.bitchinc.romantics.controller;

import com.bitchinc.romantics.config.MVCConfig;
import com.bitchinc.romantics.service.ImageRepository;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import com.mongodb.gridfs.GridFSInputFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by shashankdass on 7/24/14.
 */
@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;
    //@Autowired
    //GridFsTemplate gridFsTemplate;
    @Autowired
    GridFsOperations operations;

    public GridFSFile uploadPhoto(MultipartFile multipartFile) throws IOException {
        GridFSFile file = operations.store(multipartFile.getInputStream(), multipartFile.getOriginalFilename());
        return file;
    }

    public GridFSDBFile getPhoto(String fileName) throws IOException {
       return operations.findOne(
                new Query().addCriteria(Criteria.where("filename").is(fileName)));

        //return file.getId();
    }
}
