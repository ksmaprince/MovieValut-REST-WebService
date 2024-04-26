package com.khun.movievault.service;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.models.BlobItem;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AzureStorageService {

    private final BlobServiceClient blobServiceClient;

    public AzureStorageService() {
        blobServiceClient = new BlobServiceClientBuilder()
                .connectionString("DefaultEndpointsProtocol=https;AccountName=khunstorage;AccountKey=fPzP4MFGI4jPlMzQ6wxAZljh8k3k0wdlCW/44vCSlFRK40mJtj9SupYYA4wh+8IjDJGSNVsVuh2++AStni75jw==;EndpointSuffix=core.windows.net")
                .buildClient();
    }

    public String uploadBlob(MultipartFile file) {
        try {
            // Get blob container client (create if not exists)
            String containerName = "movievaultcontainer";
            blobServiceClient.createBlobContainerIfNotExists(containerName);

            // Get blob client
            BlobClient blobClient = blobServiceClient.getBlobContainerClient(containerName).getBlobClient(file.getOriginalFilename());

            // Upload file content
            blobClient.upload(file.getInputStream(), file.getSize(), true);

            return blobClient.getBlobUrl();

        } catch (IOException ex) {
            // Handle upload failure
            ex.printStackTrace();
            return null;
        }
    }

    public byte[] downloadBlob(String fileName) {
        // Get blob container client
        String containerName = "movievaultcontainer";
        BlobClient blobClient = blobServiceClient.getBlobContainerClient(containerName).getBlobClient(fileName);

        // Download blob content
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        blobClient.download(outputStream);

        return outputStream.toByteArray();
    }

    public List<String> listBlobs() {
        List<String> blobNames = new ArrayList<>();
        String containerName = "movievaultcontainer";
        for (BlobItem blob : blobServiceClient.getBlobContainerClient(containerName).listBlobs()) {
            blobNames.add(blob.getName());
        }
        return blobNames;
    }

    public String deleteBlob(String fileName) {
        // Get blob container client
        String containerName = "movievaultcontainer";
        BlobClient blobClient = blobServiceClient.getBlobContainerClient(containerName).getBlobClient(fileName);

        blobClient.delete();

        return "File Deleted Successfully";
    }
}

