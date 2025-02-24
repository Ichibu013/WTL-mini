package com.miniproject.wtlmini.service;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

@Service
public class ImageService {

    private final GridFSBucket gridFSBucket;

    @Autowired
    public ImageService(MongoClient mongoClient) {
        MongoDatabase db = mongoClient.getDatabase("WTL");
        this.gridFSBucket = GridFSBuckets.create(db);
    }

    public String uploadeImage(MultipartFile file) throws IOException {
        GridFSUploadOptions options = new GridFSUploadOptions();
        try (InputStream inputStream = file.getInputStream()) {
            Object id = gridFSBucket.uploadFromStream(Objects.requireNonNull(file.getOriginalFilename()), inputStream, options);
            return id.toString();
        }
    }

    public byte[] getImage(String id) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        gridFSBucket.downloadToStream(new ObjectId(id), outputStream);
        return outputStream.toByteArray();
    }
}
