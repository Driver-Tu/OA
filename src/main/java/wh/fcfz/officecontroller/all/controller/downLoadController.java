package wh.fcfz.officecontroller.all.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/file")
public class downLoadController {
    private static final String UPLOAD_DIR = "uploads";
    private final Map<String, Map<Integer, String>> chunks = new ConcurrentHashMap<>();

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFileChunk(
            @RequestBody MultipartFile file,
            @RequestBody String chunkHashs,
            @RequestBody Integer chunk) {
        try {
            String fileName = chunkHashs.split("-")[0];
            String chunkIndex = chunkHashs.split("-")[1];
            File chunkDir = new File(UPLOAD_DIR, fileName);
            if (!chunkDir.exists()) {
                chunkDir.mkdirs();
            }
            File chunkFile = new File(chunkDir, chunkIndex);
            file.transferTo(chunkFile);
            if (!chunks.containsKey(fileName)) {
                chunks.put(fileName, new ConcurrentHashMap<>());
            }
            chunks.get(fileName).put(chunk, chunkFile.getAbsolutePath());

            return ResponseEntity.ok().body("Chunk uploaded successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading chunk");
        }
    }

    @PostMapping("/merge")
    public ResponseEntity<?> mergeFile(@RequestBody Map<String, Object> payload) {
        String fileHash = (String) payload.get("fileHash");
        String fileName = (String) payload.get("fileName");
        Integer size = (Integer) payload.get("size");

        if (!chunks.containsKey(fileHash)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File not found");
        }

        try {
            File outputFile = new File(UPLOAD_DIR, fileName);
            FileOutputStream fos = new FileOutputStream(outputFile);
            for (int i = 0; i < chunks.get(fileHash).size(); i++) {
                String chunkPath = chunks.get(fileHash).get(i);
                File chunkFile = new File(chunkPath);
                FileInputStream fis = new FileInputStream(chunkFile);
                fos.getChannel().transferFrom(fis.getChannel(), 0, fis.getChannel().size());
                fis.close();
            }
            fos.close();

            return ResponseEntity.ok().body("File merged successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error merging file");
        }
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyFile(@RequestBody Map<String, String> payload) {
        String fileHash = payload.get("fileHash");
        String fileName = payload.get("fileName");

        File file = new File(UPLOAD_DIR, fileName);
        try {
            String calculatedHash = calculateFileHash(file);
            if (calculatedHash.equals(fileHash)) {
                return ResponseEntity.ok().body(Map.of("message", "File verified successfully", "isValid", true));
            } else {
                return ResponseEntity.ok().body(Map.of("message", "File verification failed", "isValid", false));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error verifying file");
        }
    }

    private String calculateFileHash(File file) throws Exception {
        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] buffer = new byte[1024];
            int read;
            MessageDigest md = MessageDigest.getInstance("MD5");
            while ((read = fis.read(buffer)) != -1) {
                md.update(buffer, 0, read);
            }
            byte[] digest = md.digest();
            BigInteger no = new BigInteger(1, digest);
            return no.toString(16);
        }
}
}
