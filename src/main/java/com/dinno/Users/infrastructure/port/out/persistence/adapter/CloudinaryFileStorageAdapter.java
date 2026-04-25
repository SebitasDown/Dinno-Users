package com.dinno.Users.infrastructure.port.out.persistence.adapter;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.dinno.Users.domain.port.out.FileStorageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CloudinaryFileStorageAdapter implements FileStorageRepository {

    private final Cloudinary cloudinary;

    @Override
    public Mono<Map<String, Object>> upload(FilePart filePart) {
        return DataBufferUtils.join(filePart.content())
                .flatMap(dataBuffer -> {
                    byte[] bytes = new byte[dataBuffer.readableByteCount()];
                    dataBuffer.read(bytes);
                    DataBufferUtils.release(dataBuffer);
                    
                    return Mono.fromCallable(() -> {
                        try {
                            @SuppressWarnings("unchecked")
                            Map<String, Object> uploadResult = (Map<String, Object>) cloudinary.uploader().upload(bytes, ObjectUtils.emptyMap());
                            return uploadResult;
                        } catch (IOException e) {
                            throw new RuntimeException("Failed to upload image to Cloudinary", e);
                        }
                    }).subscribeOn(Schedulers.boundedElastic());
                });
    }

    @Override
    public Mono<Void> delete(String publicId) {
        return Mono.fromCallable(() -> {
            try {
                cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
                return null;
            } catch (IOException e) {
                throw new RuntimeException("Failed to delete image from Cloudinary", e);
            }
        }).subscribeOn(Schedulers.boundedElastic()).then();
    }
}
