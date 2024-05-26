package qrcodeapi.Router;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import qrcodeapi.DTO.QRCodeParams;
import qrcodeapi.Services.QRGenerator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.Buffer;

@RestController
public class MainRouter {

    @GetMapping("/api/health")
    public ResponseEntity<String> getHealth() {
        ResponseEntity<String> resp = new ResponseEntity<>(HttpStatusCode.valueOf(200));
        return resp;
    }

    @GetMapping("/api/qrcode")
    public ResponseEntity<?> getQRCode(
            @Validated QRCodeParams params) {

        if(!params.isContentsValid())
            return ResponseEntity.status(400)
                    .body("{\"error\": \"Contents cannot be null or blank\"}");

        if(!params.isSizeValid())
            return ResponseEntity.status(400)
                    .body("{\"error\": \"Image size must be between "
                            + QRCodeParams.MIN_SIZE + " and " + QRCodeParams.MAX_SIZE + " pixels\"}");

        if(!params.isCorrectionValid())
            return ResponseEntity.status(400)
                    .body("{\"error\": \"Permitted error correction levels are L, M, Q, H\"}");

        if(!params.isTypeValid())
                return ResponseEntity.status(400)
                        .body("{\"error\": \"Only png, jpeg and gif image types are supported\"}");

        BufferedImage img = QRGenerator.generate(params.getContents(), params.getSize(), params.getCorrection().toUpperCase());


        return ResponseEntity
                    .ok()
                    .contentType(MediaType.valueOf("image/" + params.getType()))
                    .body(img);
    }
}
