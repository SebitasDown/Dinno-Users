package com.dinno.Users.domain.model;

import com.dinno.Users.domain.exception.FileSizeExceededException;
import com.dinno.Users.domain.exception.FileTooSmallException;
import com.dinno.Users.domain.exception.InvalidFileTypeException;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Mono;

import java.util.List;

public class FileValidator {

    private static final List<String> ALLOWED_TYPES = List.of(
            MediaType.IMAGE_JPEG_VALUE,
            MediaType.IMAGE_PNG_VALUE,
            "image/webp"
    );

    private static final long MAX_FILE_SIZE = 5 * 1024 * 1024; // 5MB
    private static final long MIN_FILE_SIZE = 10 * 1024;      // 10KB

    public static Mono<FilePart> validate(FilePart filePart) {
        return Mono.just(filePart)
                .flatMap(file -> {
                    String contentType = file.headers().getContentType() != null 
                            ? file.headers().getContentType().toString() 
                            : "";
                    
                    if (!ALLOWED_TYPES.contains(contentType)) {
                        return Mono.error(new InvalidFileTypeException("Tipo de archivo no permitido: " + contentType));
                    }
                    return Mono.just(file);
                })
                .flatMap(file -> file.content()
                        .map(dataBuffer -> (long) dataBuffer.readableByteCount())
                        .reduce(0L, Long::sum)
                        .flatMap(size -> {
                            if (size > MAX_FILE_SIZE) {
                                return Mono.error(new FileSizeExceededException("El archivo excede el tamaño máximo permitido de 5MB"));
                            }
                            if (size < MIN_FILE_SIZE) {
                                return Mono.error(new FileTooSmallException("El archivo es demasiado pequeño (mínimo 10KB)"));
                            }
                            return Mono.just(file);
                        })
                );
    }
}
