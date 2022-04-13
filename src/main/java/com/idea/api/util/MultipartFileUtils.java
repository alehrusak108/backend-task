package com.idea.api.util;

import com.idea.api.exception.MultipartFileIOException;
import java.io.IOException;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@UtilityClass
public class MultipartFileUtils {

    public static byte[] retrieveMultipartFileBytes(MultipartFile file) {
        try {
            return file.getBytes();
        } catch (IOException e) {
            log.error("Exception occurred while retrieving bytes of a Multipart File.", e);
            throw new MultipartFileIOException(ExceptionUtils.getRootCauseMessage(e));
        }
    }
}
